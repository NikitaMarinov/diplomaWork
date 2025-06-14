package com.diploma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "manufacture")
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String manufacturingTime;
    @ManyToOne
    @JoinColumn(name = "product_id")
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
