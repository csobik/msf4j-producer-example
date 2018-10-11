package com.mallgroup.producer.repositories;

import java.util.ArrayList;
import java.util.List;

import com.mallgroup.producer.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {

    private List<Product> products = new ArrayList<Product>();

    public ProductRepository() {
        products.add(new Product("Book", 4.2f));
        products.add(new Product("Pencil", 2.99f));
    }

    public void create(Product product) {
        products.add(product);
    }

    public void remove(Product product) {
        products.remove(product);
    }

    public Product find(int id) {
        return products.get(id);
    }

    public List<Product> findAll() {
        return products;
    }

    public void update(int id, Product product) {
        products.set(id, product);
    }
}