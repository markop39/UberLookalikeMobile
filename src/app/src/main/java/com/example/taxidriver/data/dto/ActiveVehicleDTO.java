package com.example.taxidriver.data.dto;


public class ActiveVehicleDTO {
    private boolean inRide;
    private Double longitude;
    private Double latitude;


    public ActiveVehicleDTO() { }

    public ActiveVehicleDTO(Boolean isInRide, Double longitude, Double latitude) {
        this.inRide = isInRide;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public boolean isInRide() {
        return inRide;
    }

    public void setInRide(boolean inRidee) {
        inRide = inRidee;
    }


    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
