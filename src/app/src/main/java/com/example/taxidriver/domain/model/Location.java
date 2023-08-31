package com.example.taxidriver.domain.model;

import androidx.annotation.NonNull;

import java.util.List;

public class Location {

    private String id;
    private Double longitude;
    private Double latitude;
    private String address;

    private List<Route> routesStartHere;
    private List<Route> routesEndHere;

    private List<Route> favouriteRoutesStartHere;
    private List<Route> favouriteRoutesEndHere;


    public Location(String id, Integer longitude, Integer latitude, List<Route> routesStartHere, List<Route> routesEndHere, List<Route> favouriteRoutesStartHere, List<Route> favouriteRoutesEndHere) {
        this.id = id;
        this.longitude = Double.valueOf(longitude);
        this.latitude = Double.valueOf(latitude);
        this.routesStartHere = routesStartHere;
        this.routesEndHere = routesEndHere;
        this.favouriteRoutesStartHere = favouriteRoutesStartHere;
        this.favouriteRoutesEndHere = favouriteRoutesEndHere;
    }

    public Location(String id, Integer longitude, Integer latitude) {
        this.id = id;
        this.longitude = Double.valueOf(longitude);
        this.latitude = Double.valueOf(latitude);
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Route> getRoutesStartHere() {
        return routesStartHere;
    }

    public void setRoutesStartHere(List<Route> routesStartHere) {
        this.routesStartHere = routesStartHere;
    }

    public List<Route> getRoutesEndHere() {
        return routesEndHere;
    }

    public void setRoutesEndHere(List<Route> routesEndHere) {
        this.routesEndHere = routesEndHere;
    }

    public List<Route> getFavouriteRoutesStartHere() {
        return favouriteRoutesStartHere;
    }

    public void setFavouriteRoutesStartHere(List<Route> favouriteRoutesStartHere) {
        this.favouriteRoutesStartHere = favouriteRoutesStartHere;
    }

    public List<Route> getFavouriteRoutesEndHere() {
        return favouriteRoutesEndHere;
    }

    public void setFavouriteRoutesEndHere(List<Route> favouriteRoutesEndHere) {
        this.favouriteRoutesEndHere = favouriteRoutesEndHere;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", routesStartHere=" + routesStartHere +
                ", routesEndHere=" + routesEndHere +
                ", favouriteRoutesStartHere=" + favouriteRoutesStartHere +
                ", favouriteRoutesEndHere=" + favouriteRoutesEndHere +
                '}';
    }
}
