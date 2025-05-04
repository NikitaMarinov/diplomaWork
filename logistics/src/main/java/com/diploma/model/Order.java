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
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;
    private Integer quantity;
    private String customerName;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime deliveryTime;
    private String deliveryDuration;
    private Long migrationId;

    public Order() {
    }

    public Order(Long id, Product product, Location location, Transport transport, Integer quantity, String customerName, OrderStatus status, LocalDateTime deliveryTime, String deliveryDuration, Long migrationId) {
        this.id = id;
        this.product = product;
        this.location = location;
        this.transport = transport;
        this.quantity = quantity;
        this.customerName = customerName;
        this.status = status;
        this.deliveryTime = deliveryTime;
        this.deliveryDuration = deliveryDuration;
        this.migrationId = migrationId;
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

    public Location getLocation() {
        return location;
    }

    public Order setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Transport getTransport() {
        return transport;
    }

    public Order setTransport(Transport transport) {
        this.transport = transport;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Order setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Order setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public Order setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public String getDeliveryDuration() {
        return deliveryDuration;
    }

    public Order setDeliveryDuration(String deliveryDuration) {
        this.deliveryDuration = deliveryDuration;
        return this;
    }

    public Long getMigrationId() {
        return migrationId;
    }

    public Order setMigrationId(Long migrationId) {
        this.migrationId = migrationId;
        return this;
    }
}