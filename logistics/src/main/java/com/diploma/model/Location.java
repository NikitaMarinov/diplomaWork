package com.diploma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String country;
    private Integer distanceToWarehouse;

    public Location() {
    }

    public Location(Long id, String city, String country, Integer distanceToWarehouse) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.distanceToWarehouse = distanceToWarehouse;
    }

    public Integer getDistanceToWarehouse() {
        return distanceToWarehouse;
    }

    public Location setDistanceToWarehouse(Integer distanceToWarehouse) {
        this.distanceToWarehouse = distanceToWarehouse;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Location setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Location setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Location setCountry(String country) {
        this.country = country;
        return this;
    }
}
