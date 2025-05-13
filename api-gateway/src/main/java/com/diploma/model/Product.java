package com.diploma.model;


import java.math.BigDecimal;

public class Product {

    private Long id;
    private String name;
    private String brand;
    private String model;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Product setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Product setModel(String model) {
        this.model = model;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
