package com.keycloak.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycloak.api.model.Producto;
import com.keycloak.api.service.ProductoService;
import com.keycloak.api.service.TransactionService;

@RestController
@RequestMapping("/producto")
@PreAuthorize("hasRole('admin_client_role')")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TransactionService transactionService;
    
    @GetMapping("/all")
    public List<Producto> findAllProducto(){
        if (transactionService.limitReached()) {
            List<Producto> productos = productoService.findAllProducto();
            transactionService.complete();
            //System.out.println("###############################");
            return productos;
        } else {
            System.out.println("Limite de transacciones alcanzado!");
            return null;
        }
    }

    @GetMapping("/{id}")
    public Producto findProducto(@PathVariable Long id){
        if (transactionService.limitReached()) {
            Producto producto = productoService.findProducto(id);
            transactionService.complete();
            return producto;
        } else {
            System.out.println("Limite de transacciones alcanzado!");
            return null;
        }
    }

    @PostMapping("/create")
    public void createProducto(@RequestBody Producto producto) {
        
        if (transactionService.limitReached()) {
            productoService.createProducto(producto);
            transactionService.complete();
        } else {
            System.out.println("Limite de transacciones alcanzado!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducto(@PathVariable Long id){
        if (transactionService.limitReached()) {
            productoService.deleteProducto(id);
            transactionService.complete();
        } else {
            System.out.println("Limite de transacciones alcanzado!");
        }
    }
}
