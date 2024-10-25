package com.parkingapplication.demoApplication.data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationid")
    private Long reservationID;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private user user;

    @ManyToOne
    @JoinColumn(name = "spotid", referencedColumnName = "spotID")
    private parkingspot parkingspot;

    private double price;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;
    // No-argument constructor

    @Column(name = "lot_location") // New column to store the parking lot location
    private String lotLocation;


    public reservation(){

    }

    public reservation(com.parkingapplication.demoApplication.data.user user, com.parkingapplication.demoApplication.data.parkingspot parkingspot, double price, LocalDateTime startTime, LocalDateTime endTime, String lotLocation) {
        this.user = user;
        this.parkingspot = parkingspot;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lotLocation = lotLocation;
    }


    // Getters and Setters
    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public void setParkingspot(parkingspot parkingspot){
        this.parkingspot = parkingspot;
    }

    public com.parkingapplication.demoApplication.data.parkingspot getParkingspot() {
        return parkingspot;
    }

    public String getLotLocation() {
        return lotLocation;
    }

    public void setLotLocation(String lotLocation) {
        this.lotLocation = lotLocation;
    }
}
