
package com.Ecommerce.Shoping.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Ecommerce.Shoping.Entity.Product;
import com.Ecommerce.Shoping.Repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
