package com.diploma.model.dto;

public class TransportDto {
    private String carType;
    private Long speed;
    private Long loadVolume;

    public TransportDto() {
    }

    public TransportDto(String carType, Long speed, Long loadVolume) {
        this.carType = carType;
        this.speed = speed;
        this.loadVolume = loadVolume;
    }

    public String getCarType() {
        return carType;
    }

    public TransportDto setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public Long getSpeed() {
        return speed;
    }

    public TransportDto setSpeed(Long speed) {
        this.speed = speed;
        return this;
    }

    public Long getLoadVolume() {
        return loadVolume;
    }

    public TransportDto setLoadVolume(Long loadVolume) {
        this.loadVolume = loadVolume;
        return this;
    }
}
