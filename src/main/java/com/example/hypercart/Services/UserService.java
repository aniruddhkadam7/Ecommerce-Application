package com.example.hypercart.Services;

import com.example.hypercart.Model.Role;
import com.example.hypercart.Model.User;
import com.example.hypercart.Repo.RoleRepo;
import com.example.hypercart.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Email validation regex pattern
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        System.out.println("Loading user: " + user.getUsername() + ", Authorities: " + authorities);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    public void save(User user) throws IllegalArgumentException {
        // Check for duplicate username
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username '" + user.getUsername() + "' is already taken.");
        }

        // Check for duplicate email
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email '" + user.getEmail() + "' is already registered.");
        }

        // Validate email format
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + user.getEmail());
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String selectedRole = user.getRole();
        Set<Role> roles = new HashSet<>();

        if (selectedRole != null && !selectedRole.isEmpty()) {
            String roleName = "ROLE_" + selectedRole.toUpperCase();
            Role roleEntity = roleRepo.findByName(roleName);

            if (roleEntity == null) {
                roleEntity = new Role(roleName);
                roleRepo.save(roleEntity);
            }

            roles.add(roleEntity);
            user.setRoles(roles);
            user.setRole(roleName);
        } else {
            String defaultRoleName = "ROLE_BUYER";
            Role buyerRole = roleRepo.findByName(defaultRoleName);
            if (buyerRole == null) {
                buyerRole = new Role(defaultRoleName);
                roleRepo.save(buyerRole);
            }

            roles.add(buyerRole);
            user.setRoles(roles);
            user.setRole(defaultRoleName);
        }

        userRepo.save(user);
    }

    // Add method to check email existence (assuming UserRepo has this method)
    public boolean emailExists(String email) {
        return userRepo.findByEmail(email).isPresent();
    }
}