package com.example.hypercart.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

/**
 * This is the Product entity class that represents a product in the database.
 * Each field corresponds to a column in the "product" table.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing ID
    private Long id; // Unique identifier for the product
    private String name; // Name of the product
    private double price; // Price of the product
    private String description; // Description of the product
    @Lob // Indicates this field can store large data (like images)
    private byte[] image; // Image of the product stored as raw bytes
    private Integer sales; // Number of times the product has been sold (nullable)

    // Default constructor required by JPA (Java Persistence API)
    public Product() {}

    // Constructor with fields (except ID, which is auto-generated)
    public Product(String name, double price, String description, byte[] image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.sales = 0; // Default sales to 0 for new products
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
    public Integer getSales() { return sales; }
    public void setSales(Integer sales) { this.sales = sales; }
}