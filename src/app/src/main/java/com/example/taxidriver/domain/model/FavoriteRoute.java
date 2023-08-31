package com.example.taxidriver.domain.model;

public class FavoriteRoute {

    private String id;

    private Passenger passenger;

    private Location startingPoint;
    private Location destination;

    public FavoriteRoute(String id, Passenger passenger, Location startingPoint, Location destination) {
        this.id = id;
        this.passenger = passenger;
        this.startingPoint = startingPoint;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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

    @Override
    public String toString() {
        return "FavoriteRoute{" +
                "id='" + id + '\'' +
                ", passenger=" + passenger +
                ", startingPoint=" + startingPoint +
                ", destination=" + destination +
                '}';
    }
}
