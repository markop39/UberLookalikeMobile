package com.example.taxidriver.domain.model;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Payment {

    private String id;
    private LocalDateTime dateTime;
    private int totalPrice;

    private Passenger passenger;
    private Drive drive;

    public Payment(String id, LocalDateTime dateTime, int totalPrice, Passenger passenger, Drive drive) {
        this.id = id;
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.passenger = passenger;
        this.drive = drive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }


    @NonNull
    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", totalPrice=" + totalPrice +
                ", passenger=" + passenger +
                ", drive=" + drive +
                '}';
    }
}
