package com.example.taxidriver.data.dto;


public class PendingRideResponseDTO {
    private long id;
    private double time;
    private long driverId;
    private long rideId;




    public PendingRideResponseDTO() {
        this.id = id;
        this.time = time;
        this.driverId = driverId;
        this.rideId = rideId;
    }

    public PendingRideResponseDTO(long id, double time, long driverId, long rideId) {
        this.id = id;
        this.time = time;
        this.driverId = driverId;
        this.rideId = rideId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getRideId() {
        return rideId;
    }

    public void setRideId(long rideId) {
        this.rideId = rideId;
    }
}
