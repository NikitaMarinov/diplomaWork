package com.diploma.model;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Document(indexName = "order_index")
public class Order {
    @Id
    private Long id;
    private Long productId;
    private String name;
    private String brand;
    private String model;
    private Long pricePerObject;
    private Long locationId;
    private Long price;
    private Integer quantity;
    @Field(type = FieldType.Date)
    private LocalDate orderDate;
    private String customerName;
    private String status;

    public Order() {
    }

    public Order(Long id, Long productId, String name, String brand, String model, Long pricePerObject, Long locationId, Long price, Integer quantity, LocalDate orderDate, String customerName, String status) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.pricePerObject = pricePerObject;
        this.locationId = locationId;
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

    public Long getProductId() {
        return productId;
    }

    public Order setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Order setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Order setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Order setModel(String model) {
        this.model = model;
        return this;
    }

    public Long getPricePerObject() {
        return pricePerObject;
    }

    public Order setPricePerObject(Long pricePerObject) {
        this.pricePerObject = pricePerObject;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public Order setLocationId(Long locationId) {
        this.locationId = locationId;
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

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }
}
