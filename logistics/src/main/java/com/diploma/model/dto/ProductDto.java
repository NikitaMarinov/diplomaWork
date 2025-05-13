package com.diploma.model.dto;

import java.math.BigDecimal;

public class ProductDto {
    private String name;
    private String brand;
    private String model;
    private BigDecimal price;

    public ProductDto() {
    }

    public ProductDto(String name, String brand, String model, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public ProductDto setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public ProductDto setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
