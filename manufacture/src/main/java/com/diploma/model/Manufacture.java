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
    String manufacturing_time;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Manufacture() {
    }

    public Manufacture(Long id, String manufacturing_time, Product product) {
        this.id = id;
        this.manufacturing_time = manufacturing_time;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Manufacture setId(Long id) {
        this.id = id;
        return this;
    }

    public String getManufacturing_time() {
        return manufacturing_time;
    }

    public Manufacture setManufacturing_time(String manufacturing_time) {
        this.manufacturing_time = manufacturing_time;
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
