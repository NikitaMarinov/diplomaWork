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
    private LocalDateTime productionEndTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Long migration_id;
    private String productionTime;

    public Order() {
    }

    public Order(Long id, Product product, Integer quantity, LocalDateTime productionEndTime, OrderStatus status, Long migration_id, String productionTime) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.productionEndTime = productionEndTime;
        this.status = status;
        this.migration_id = migration_id;
        this.productionTime = productionTime;
    }

    public String getProductionTime() {
        return productionTime;
    }

    public Order setProductionTime(String productionTime) {
        this.productionTime = productionTime;
        return this;
    }

    public LocalDateTime getProductionEndTime() {
        return productionEndTime;
    }

    public Order setProductionEndTime(LocalDateTime productionEndTime) {
        this.productionEndTime = productionEndTime;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", productionEndTime=" + productionEndTime +
                ", status=" + status +
                ", migration_id=" + migration_id +
                ", productionTime='" + productionTime + '\'' +
                '}';
    }
}