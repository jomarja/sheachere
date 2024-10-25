package com.parkingapplication.demoApplication.data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentid")
    private Long paymentID;

    @ManyToOne
    @JoinColumn(name = "reservationid", referencedColumnName = "reservationid")
    private reservation reservation;

    private double amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_timestamp")
    private LocalDateTime paymentTimestamp;

    // Getters and Setters
    public Long getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentTimestamp() {
        return paymentTimestamp;
    }

    public void setPaymentTimestamp(LocalDateTime paymentTimestamp) {
        this.paymentTimestamp = paymentTimestamp;
    }

    public reservation getReservation() {
        return reservation;
    }

    public void setReservation(reservation reservation) {
        this.reservation = reservation;
    }
}
