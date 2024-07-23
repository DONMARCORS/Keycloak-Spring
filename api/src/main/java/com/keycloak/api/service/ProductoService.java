package com.keycloak.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keycloak.api.model.Producto;
import com.keycloak.api.repository.ProductoRepository;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    public void createProducto(Producto producto){
        productoRepository.save(producto);
    }

    public void deleteProducto(Long id){
        productoRepository.deleteById(id);
    }

    public List<Producto> findAllProducto(){
        return productoRepository.findAll();
    }

    public Producto findProducto(Long id){
        return productoRepository.findById(id);
    }
}
