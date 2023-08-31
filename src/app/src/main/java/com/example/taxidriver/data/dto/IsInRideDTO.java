package com.example.taxidriver.data.dto;


public class IsInRideDTO {
    private Boolean inRide;
    private RideDTO rideDTO;

    public IsInRideDTO() {
    }

    public IsInRideDTO(Boolean inRide, RideDTO rideDTO) {
        this.inRide = inRide;
        this.rideDTO = rideDTO;
    }

    public RideDTO getRideDTO() {
        return rideDTO;
    }

    public void setRideDTO(RideDTO rideDTO) {
        this.rideDTO = rideDTO;
    }

    public Boolean getInRide() {
        return inRide;
    }

    public void setInRide(Boolean inRide) {
        this.inRide = inRide;
    }
}
