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

    public Location() {
    }

    public Location(String country, String city, Long id) {
        this.country = country;
        this.city = city;
        this.id = id;
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
