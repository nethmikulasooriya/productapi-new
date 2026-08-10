package com.example.productapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, String> {
}
