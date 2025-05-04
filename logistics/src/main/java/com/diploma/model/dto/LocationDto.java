package com.diploma.model.dto;

public class LocationDto {
    private String city;
    private String country;

    public LocationDto() {
    }

    public LocationDto(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public LocationDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public LocationDto setCity(String city) {
        this.city = city;
        return this;
    }
}

