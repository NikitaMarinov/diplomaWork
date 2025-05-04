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
    private Long migrationId;
    private String productionTime;
    private String locationId;

    public Order() {
    }

    public Order(Long id, Product product, Integer quantity, LocalDateTime productionEndTime, OrderStatus status, Long migrationId, String productionTime, String locationId) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.productionEndTime = productionEndTime;
        this.status = status;
        this.migrationId = migrationId;
        this.productionTime = productionTime;
        this.locationId = locationId;
    }

    public String getProductionTime() {
        return productionTime;
    }

    public Order setProductionTime(String productionTime) {
        this.productionTime = productionTime;
        return this;
    }

    public String getLocationId() {
        return locationId;
    }

    public Order setLocationId(String locationId) {
        this.locationId = locationId;
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


    public Long getMigrationId() {
        return migrationId;
    }

    public Order setMigrationId(Long migrationId) {
        this.migrationId = migrationId;
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
                ", migrationId=" + migrationId +
                ", productionTime='" + productionTime + '\'' +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}