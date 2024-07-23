package com.keycloak.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.keycloak.api.model.Producto;

public class ProductoMapper implements RowMapper<Producto> {
    
    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("id"));
        producto.setName(rs.getString("name"));
        producto.setPrice(rs.getFloat("price"));
        return producto;
    }
}
