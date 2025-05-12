package com.diploma.model;

import com.diploma.avro.OrderStatus;
import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.ZonedDateTime;

@Document(indexName = "sales_index")
public class Sales {
    @Id
    private Long id;
    private Long productId;
    private String name;
    private String brand;
    private String model;
    private Long locationId;
    private String city;
    private String country;
    private Long price;
    private Integer quantity;
    @Field(type = FieldType.Date)
    private ZonedDateTime orderDate;
    private String status;

    public Sales() {
    }

    public Sales(String status, ZonedDateTime orderDate, Integer quantity, Long price, String country, String city, Long locationId, String model, String brand, String name, Long productId, Long id) {
        this.status = status;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.price = price;
        this.country = country;
        this.city = city;
        this.locationId = locationId;
        this.model = model;
        this.brand = brand;
        this.name = name;
        this.productId = productId;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Sales setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public Sales setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Sales setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Sales setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Sales setModel(String model) {
        this.model = model;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public Sales setLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Sales setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Sales setCountry(String country) {
        this.country = country;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public Sales setPrice(Long price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Sales setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public ZonedDateTime getOrderDate() {
        return orderDate;
    }

    public Sales setOrderDate(ZonedDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Sales setStatus(String status) {
        this.status = status;
        return this;
    }
}

