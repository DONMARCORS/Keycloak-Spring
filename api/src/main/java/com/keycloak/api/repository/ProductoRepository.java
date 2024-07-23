package com.keycloak.api.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.keycloak.api.mapper.ProductoMapper;
import com.keycloak.api.model.Producto;


@Repository
public class ProductoRepository{

    private JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void save(Producto producto){
        jdbcTemplate.update("INSERT INTO Producto VALUES (?,?,?)",
            producto.getId(), producto.getName(), producto.getPrice());
    }

    public List<Producto> findAll(){
        return jdbcTemplate.query("SELECT * FROM Producto", new ProductoMapper());
    }

    public Producto findById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM Producto WHERE id=?",
            new ProductoMapper(), id);
    }

    @Transactional
    public void deleteById(Long id){
        jdbcTemplate.update("DELETE FROM Producto WHERE id =?", id);
    }

} 
