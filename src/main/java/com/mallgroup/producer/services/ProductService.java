package com.mallgroup.producer.services;

import java.util.List;

import com.mallgroup.producer.domain.Product;
import com.mallgroup.producer.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product find(int id) {
        return productRepository.find(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void create(Product product) {
        productRepository.create(product);
    }
}
