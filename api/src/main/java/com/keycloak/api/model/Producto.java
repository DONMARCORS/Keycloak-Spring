package com.keycloak.api.model;

import org.springframework.data.annotation.Id;

public class Producto {
    @Id
    private Long id;
    private String name;
    private Float price;

    public Producto() {
    }

    public Producto(Long id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
