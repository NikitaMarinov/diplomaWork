package com.diploma.model;

import com.diploma.constants.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    private LocalDateTime production_end_time;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Long migration_id;
    private String production_time;

    public Order() {
    }

    public Order(String production_time, Long migration_id, OrderStatus status, LocalDateTime production_end_time, Integer quantity, Product product, Long id) {
        this.production_time = production_time;
        this.migration_id = migration_id;
        this.status = status;
        this.production_end_time = production_end_time;
        this.quantity = quantity;
        this.product = product;
        this.id = id;
    }

    public String getProduction_time() {
        return production_time;
    }

    public Order setProduction_time(String production_time) {
        this.production_time = production_time;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Order setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Order setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public LocalDateTime getProduction_end_time() {
        return production_end_time;
    }

    public Order setProduction_end_time(LocalDateTime production_end_time) {
        this.production_end_time = production_end_time;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public Long getMigration_id() {
        return migration_id;
    }

    public Order setMigration_id(Long migration_id) {
        this.migration_id = migration_id;
        return this;
    }
}