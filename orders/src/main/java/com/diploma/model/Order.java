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

import java.time.LocalDate;

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
    private Long price;
    private Integer quantity;
    private LocalDate orderDate;
    private String customerName;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
    }

    public Order(Long id, Product product, Location location, Long price, Integer quantity, LocalDate orderDate, String customerName, OrderStatus status) {
        this.id = id;
        this.product = product;
        this.location = location;
        this.price = price;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.status = status;
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

    public Location getLocation() {
        return location;
    }

    public Order setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Order setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Order setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Order setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
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


}
