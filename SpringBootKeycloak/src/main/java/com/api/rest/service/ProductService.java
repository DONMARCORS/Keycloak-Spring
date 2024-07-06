package com.api.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.api.rest.model.Product;
import com.api.rest.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Cacheable(cacheNames = {"productsCache"}, key = "#studentId")
    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }

    @CachePut(cacheNames = {"productsCache"}, key = "#id")
    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    @CachePut(cacheNames = {"productsCache"}, key = "#id")
    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "User deleted";
    }


}
