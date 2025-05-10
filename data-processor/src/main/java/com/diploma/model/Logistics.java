package com.diploma.model;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "logistics_index")
public class Logistics {
    @Id
    private Long id;
    private Long productId;
    private String name;
    private String brand;
    private String model;
    private Long locationId;
    private String city;
    private String country;
    private Long distanceToWarehouse;
    private Long transportId;
    private String carType;
    private Long speed;
    private Long loadVolume;
    private Integer quantity;
    private String customerName;
    private String status;
    private String deliveryDuration;

    public Logistics() {
    }

    public Logistics(Long id, Long productId, String name, String brand, String model, Long locationId, String city, String country, Long distanceToWarehouse, Long transportId, String carType, Long speed, Long loadVolume, Integer quantity, String customerName, String status, String deliveryDuration) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.locationId = locationId;
        this.city = city;
        this.country = country;
        this.distanceToWarehouse = distanceToWarehouse;
        this.transportId = transportId;
        this.carType = carType;
        this.speed = speed;
        this.loadVolume = loadVolume;
        this.quantity = quantity;
        this.customerName = customerName;
        this.status = status;
        this.deliveryDuration = deliveryDuration;
    }

    public Long getId() {
        return id;
    }

    public Logistics setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public Logistics setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Logistics setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Logistics setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Logistics setModel(String model) {
        this.model = model;
        return this;
    }

    public Long getLocationId() {
        return locationId;
    }

    public Logistics setLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Logistics setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Logistics setCountry(String country) {
        this.country = country;
        return this;
    }

    public Long getDistanceToWarehouse() {
        return distanceToWarehouse;
    }

    public Logistics setDistanceToWarehouse(Long distanceToWarehouse) {
        this.distanceToWarehouse = distanceToWarehouse;
        return this;
    }

    public Long getTransportId() {
        return transportId;
    }

    public Logistics setTransportId(Long transportId) {
        this.transportId = transportId;
        return this;
    }

    public String getCarType() {
        return carType;
    }

    public Logistics setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public Long getSpeed() {
        return speed;
    }

    public Logistics setSpeed(Long speed) {
        this.speed = speed;
        return this;
    }

    public Long getLoadVolume() {
        return loadVolume;
    }

    public Logistics setLoadVolume(Long loadVolume) {
        this.loadVolume = loadVolume;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Logistics setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Logistics setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Logistics setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDeliveryDuration() {
        return deliveryDuration;
    }

    public Logistics setDeliveryDuration(String deliveryDuration) {
        this.deliveryDuration = deliveryDuration;
        return this;
    }
}
