package com.example.taxidriver.data.dto;


import com.example.taxidriver.domain.model.Location;

public class FavouriteRouteResponseDTO {
    private Long id;
    private Location startingPoint;
    private Location destination;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Location startingPoint) {
        this.startingPoint = startingPoint;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }
}
