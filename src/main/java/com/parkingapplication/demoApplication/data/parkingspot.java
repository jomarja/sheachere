package com.parkingapplication.demoApplication.data;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class parkingspot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spotID")  // Align column name with DB schema
    private Long spotID;

    @ManyToOne
    @JoinColumn(name = "lotID", referencedColumnName = "lotID") // Foreign key relation
    private parkinglot parkinglot;

    private String location;
    private double price;
    private boolean availability;

    @Column(name = "isEVChargingAvailable")
    private boolean isEVChargingAvailable;
    // No-argument constructor


    // Getters and Setters
    public Long getSpotID() {
        return spotID;
    }

    public void setSpotID(Long spotID) {
        this.spotID = spotID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isEVChargingAvailable() {
        return isEVChargingAvailable;
    }

    public void setEVChargingAvailable(boolean EVChargingAvailable) {
        isEVChargingAvailable = EVChargingAvailable;
    }
}
