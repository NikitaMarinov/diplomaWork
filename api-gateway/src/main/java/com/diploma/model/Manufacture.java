package com.diploma.model;


public class Manufacture {


    Long id;
    String manufacturingTime;
    private Product product;

    public Manufacture() {
    }

    public Manufacture(Long id, String manufacturingTime, Product product) {
        this.id = id;
        this.manufacturingTime = manufacturingTime;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Manufacture setId(Long id) {
        this.id = id;
        return this;
    }

    public String getManufacturingTime() {
        return manufacturingTime;
    }

    public Manufacture setManufacturingTime(String manufacturingTime) {
        this.manufacturingTime = manufacturingTime;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Manufacture setProduct(Product product) {
        this.product = product;
        return this;
    }
}
