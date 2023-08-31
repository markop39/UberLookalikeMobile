package com.example.taxidriver.data.dto;

import com.example.taxidriver.domain.model.Location;

public class LocationDTO1 {

    private String address;
    private Double longitude;
    private Double latitude;


    public LocationDTO1(Location location){
        this.address = location.getAddress();
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
