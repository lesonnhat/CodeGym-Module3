package org.example.productmanagement.service;

import org.example.productmanagement.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private static Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "Iphone", "New", 1200));
        products.put(2, new Product(2, "Samsung", "New", 1050));
        products.put(3, new Product(3, "Xiaomi", "New", 930));
        products.put(4, new Product(4, "Oppo", "New", 950));
        products.put(5, new Product(5, "Sony", "New", 1100));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void delete(int id) {
        products.remove(id);
    }
}