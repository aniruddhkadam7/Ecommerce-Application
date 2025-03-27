package com.example.MyFirstApp.Repo;

import com.example.MyFirstApp.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}