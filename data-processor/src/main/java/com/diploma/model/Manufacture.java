package com.diploma.model;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.ZonedDateTime;

@Document(indexName = "manufacture_index")
public class Manufacture {
    @Id
    private Long id;
    private Long productId;
    private String name;
    private String brand;
    private String model;
    private Integer quantity;
    private String status;
    private String productionTime;
    private Long locationId;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime productionEndTime;


    public Manufacture() {
    }

    public Manufacture(Long id, Long productId, String name, String brand, String model, Integer quantity, String status, String productionTime, Long locationId, ZonedDateTime productionEndTime) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.quantity = quantity;
        this.status = status;
        this.productionTime = productionTime;
        this.locationId = locationId;
        this.productionEndTime = productionEndTime;
    }

    public ZonedDateTime getProductionEndTime() {
        return productionEndTime;
    }

    public Manufacture setProductionEndTime(ZonedDateTime productionEndTime) {
        this.productionEndTime = productionEndTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Manufacture setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public Manufacture setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Manufacture setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Manufacture setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Manufacture setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Manufacture setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Manufacture setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getProductionTime() {
        return productionTime;
    }

    public Manufacture setProductionTime(String productionTime) {
        this.productionTime = productionTime;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public Manufacture setLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }
}