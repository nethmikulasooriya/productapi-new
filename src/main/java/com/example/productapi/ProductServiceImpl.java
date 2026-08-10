package com.example.productapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product update(String id, Product product) {
        Product existing = getById(id);
        existing.setName(product.getName());
        existing.setEmail(product.getEmail());
        existing.setAge(product.getAge());
        return productRepository.save(existing);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
