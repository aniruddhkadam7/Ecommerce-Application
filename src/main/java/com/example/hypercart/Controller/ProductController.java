package com.example.hypercart.Controller;

import com.example.hypercart.Model.Product;
import com.example.hypercart.Services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        logger.debug("Fetching all products");
        List<Product> products = productService.getAllProducts();
        logger.debug("Returning {} products", products.size());
        model.addAttribute("products", products); // Add products to model
        model.addAttribute("product", new Product()); // Add empty product for form binding
        return "sellerDashboard"; // Return Thymeleaf template
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public String createProduct(
            @ModelAttribute("product") Product product,
            @RequestParam("image") MultipartFile image,
            Model model) throws IOException {
        logger.debug("Creating product: name={}, price={}, description={}", product.getName(), product.getPrice(), product.getDescription());
        byte[] imageBytes = image.getBytes();
        Product createdProduct = productService.createProduct(product.getName(), product.getPrice(), product.getDescription(), imageBytes);
        model.addAttribute("message", "Product created successfully!");
        return "redirect:/api/product/all"; // Redirect to refresh the page
    }

    @PostMapping(value = "/{id}/update", consumes = "multipart/form-data")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute("product") Product product,
            @RequestParam(value = "image", required = false) MultipartFile image,
            Model model) throws IOException {
        logger.debug("Updating product ID: {}", id);
        product.setId(id);
        byte[] imageBytes = image != null ? image.getBytes() : product.getImage();
        Product updatedProduct = productService.updateProduct(id, product.getName(), product.getPrice(), product.getDescription(), imageBytes);
        model.addAttribute("message", "Product updated successfully!");
        return "redirect:/api/product/all";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id, Model model) {
        logger.debug("Deleting product ID: {}", id);
        productService.deleteProduct(id);
        model.addAttribute("message", "Product deleted successfully!");
        return "redirect:/api/product/all";
    }

    @PostMapping("/{id}/incrementSales")
    public String incrementSales(@PathVariable Long id, Model model) {
        logger.debug("Incrementing sales for product ID: {}", id);
        productService.incrementSales(id);
        model.addAttribute("message", "Sales incremented successfully!");
        return "redirect:/api/product/all";
    }
}