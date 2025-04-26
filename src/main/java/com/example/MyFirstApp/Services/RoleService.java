package com.example.MyFirstApp.Services;

import com.example.MyFirstApp.Model.Role;
import com.example.MyFirstApp.Repo.RoleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;



}
