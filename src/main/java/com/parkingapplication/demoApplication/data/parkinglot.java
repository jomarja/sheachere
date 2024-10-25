package com.parkingapplication.demoApplication.data;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class parkinglot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lotID")  // Explicitly specify the column name
    private Long lotID;

    private String location;
    private int capacity;

    // No-argument constructor

    // Getters and Setters
    public Long getLotID() {
        return lotID;
    }

    public void setLotID(Long lotID) {
        this.lotID = lotID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
