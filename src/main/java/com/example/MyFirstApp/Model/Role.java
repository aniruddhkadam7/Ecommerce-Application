package com.example.MyFirstApp.Model;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    // Other getters/setters as needed


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}