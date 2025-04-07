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
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        System.out.println("Loading user: " + user.getUsername() + ", Password: " + user.getPassword() + ", Roles: " + user.getRoles());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .collect(Collectors.toList())
        );
    }

    public void save(User user) {
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Get role selected in the form (BUYER, SELLER, ADMIN)
        String selectedRole = user.getRole(); // comes from the HTML form

        Set<Role> roles = new HashSet<>();

        if (selectedRole != null && !selectedRole.isEmpty()) {
            Role roleEntity = roleRepo.findByName(selectedRole.toUpperCase());

            if (roleEntity == null) {
                roleEntity = new Role(selectedRole.toUpperCase());
                roleRepo.save(roleEntity);
            }

            roles.add(roleEntity);
            user.setRoles(roles);
            user.setRole(selectedRole.toUpperCase()); // Store string version too
        } else {
            // Default to BUYER if no role selected
            Role buyerRole = roleRepo.findByName("BUYER");
            if (buyerRole == null) {
                buyerRole = new Role("BUYER");
                roleRepo.save(buyerRole);
            }

            roles.add(buyerRole);
            user.setRoles(roles);
            user.setRole("BUYER");
        }

        userRepo.save(user);
    }
}