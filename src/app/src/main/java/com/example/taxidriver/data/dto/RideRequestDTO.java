package com.example.taxidriver.data.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RideRequestDTO {
    private List<PassengerDTO2> passengers = new ArrayList<>();
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private LocationDTO locations;
    private String scheduledTime;
    private Integer estimationTime;
    private Integer estimationPrice;

    public RideRequestDTO() { }


    public RideRequestDTO(String vehicleType, boolean babyTransport, boolean petTransport, LocationDTO locations, String scheduledTime) {
        this.passengers = new ArrayList<>();
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.locations = locations;
        this.scheduledTime = scheduledTime;
    }


    public List<PassengerDTO2> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerDTO2> passengers) {
        this.passengers = passengers;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isBabyTransport() {
        return babyTransport;
    }

    public void setBabyTransport(boolean babyTransport) {
        this.babyTransport = babyTransport;
    }

    public boolean isPetTransport() {
        return petTransport;
    }

    public Integer getEstimationTime() {
        return estimationTime;
    }

    public void setEstimationTime(Integer estimationTime) {
        this.estimationTime = estimationTime;
    }

    public Integer getEstimationPrice() {
        return estimationPrice;
    }

    public void setEstimationPrice(Integer estimationPrice) {
        this.estimationPrice = estimationPrice;
    }

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public LocationDTO getLocations() {
        return locations;
    }

    public void setLocations(LocationDTO locations) {
        this.locations = locations;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
