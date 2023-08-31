package com.example.taxidriver.domain.model;


import com.example.taxidriver.data.dto.RideDTO;

import java.time.LocalDateTime;
import java.util.List;

public class Ride {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer totalCost;
    private Driver driver;
    private List<Passenger> passengers;
    private List<Route> routes;
    private Integer estimatedTimeInMinutes;
    private Review driverReview;
    private Review vehicleReview;
    private String status;
    private boolean panicButton;
    private VehicleType vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private RejectionLetter rejection;
    private Location departure;
    private Location destination;


    public Ride(Long id, LocalDateTime startTime, LocalDateTime endTime, Integer totalCost, Driver driver, List<Passenger> passengers, List<Route> routes, Integer estimatedTimeInMinutes, Review driverReview, Review vehicleReview, String status, boolean panicButton, VehicleType vehicleType, boolean babyTransport, boolean petTransport, RejectionLetter rejection, Location departure, Location destination) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.driver = driver;
        this.passengers = passengers;
        this.routes = routes;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.driverReview = driverReview;
        this.vehicleReview = vehicleReview;
        this.status = status;
        this.panicButton = panicButton;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.rejection = rejection;
        this.departure = departure;
        this.destination = destination;
    }




    public Ride() {
    }

    public Review getDriverReview() {
        return driverReview;
    }

    public void setDriverReview(Review driverReview) {
        this.driverReview = driverReview;
    }


    public Review getVehicleReview() {
        return vehicleReview;
    }

    public void setVehicleReview(Review vehicleReview) {
        this.vehicleReview = vehicleReview;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Integer estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RejectionLetter getRejection() {
        return rejection;
    }

    public void setRejection(RejectionLetter rejection) {
        this.rejection = rejection;
    }

    public boolean isPanicButton() {
        return panicButton;
    }

    public void setPanicButton(boolean panicButton) {
        this.panicButton = panicButton;
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

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
