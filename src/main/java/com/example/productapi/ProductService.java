package com.example.productapi;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    List<Product> getAll();
    Product getById(String id);
    Product update(String id, Product product);
    void delete(String id);
}
