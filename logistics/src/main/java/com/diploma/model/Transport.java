package com.diploma.model;

import com.diploma.constants.CarType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private CarType type;
    private Long speed;
    private Long loadVolume;

    public Transport() {
    }

    public Transport(Long loadVolume, Long speed, CarType type, Long id) {
        this.loadVolume = loadVolume;
        this.speed = speed;
        this.type = type;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Transport setId(Long id) {
        this.id = id;
        return this;
    }

    public CarType getType() {
        return type;
    }

    public Transport setType(CarType type) {
        this.type = type;
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
                ", type=" + type +
                ", speed=" + speed +
                ", loadVolume=" + loadVolume +
                '}';
    }
}
