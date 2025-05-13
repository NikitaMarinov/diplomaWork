package com.diploma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carType;
    private Long speed;
    private Long loadVolume;

    public Transport() {
    }

    public Transport(Long loadVolume, Long speed, String carType, Long id) {
        this.loadVolume = loadVolume;
        this.speed = speed;
        this.carType = carType;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Transport setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCarType() {
        return carType;
    }

    public Transport setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public Long getSpeed() {
        return speed;
    }

    public Transport setSpeed(Long speed) {
        this.speed = speed;
        return this;
    }

    public Long getLoadVolume() {
        return loadVolume;
    }

    public Transport setLoadVolume(Long loadVolume) {
        this.loadVolume = loadVolume;
        return this;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", carType=" + carType +
                ", speed=" + speed +
                ", loadVolume=" + loadVolume +
                '}';
    }
}
