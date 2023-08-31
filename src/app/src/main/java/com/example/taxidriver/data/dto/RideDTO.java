package com.example.taxidriver.data.dto;

import com.example.taxidriver.domain.model.Passenger;
import com.example.taxidriver.domain.model.Ride;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class RideDTO {

    private Long id;
    private String startTime;
    private String endTime;
    private Integer totalCost;
    private DriverDTO driverDTO;
    private List<PassengerDTO2> passengerDTO2 = new ArrayList<>();
    private Integer estimatedTimeInMinutes;
    private String vehicleType;
    private boolean babyTransport;
    private boolean petTransport;
    private RejectionLetterDTO rejection;
    private LocationDTO locations;
    private String status;


    public RideDTO() {
    }

    public RideDTO(Ride ride){
        this.id = ride.getId();
        this.startTime = ride.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.endTime = ride.getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.totalCost = ride.getTotalCost();
        if(ride.getDriver() != null)
            this.driverDTO = new DriverDTO(ride.getDriver());
        for (Passenger passenger: ride.getPassengers()) {
            passengerDTO2.add(new PassengerDTO2(passenger));
        }
        this.estimatedTimeInMinutes = ride.getEstimatedTimeInMinutes();
        this.vehicleType = ride.getVehicleType().getName();
        this.petTransport = ride.isPetTransport();
        this.babyTransport = ride.isBabyTransport();
        if(ride.getRejection() != null)
            this.rejection = new RejectionLetterDTO(ride.getRejection());
        this.locations = new LocationDTO(new LocationDTO1(ride.getDeparture()), new LocationDTO1(ride.getDestination()));
        this.status = ride.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public DriverDTO getDriverDTO() {
        return driverDTO;
    }

    public void setDriverDTO(DriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }

    public List<PassengerDTO2> getPassengerDTO2() {
        return passengerDTO2;
    }

    public void setPassengerDTO2(List<PassengerDTO2> passengerDTO2) {
        this.passengerDTO2 = passengerDTO2;
    }

    public List<PassengerDTO2> getPassengers() {
        return passengerDTO2;
    }

    public void setPassengers(List<PassengerDTO2> passengerDTO2) {
        this.passengerDTO2 = passengerDTO2;
    }

    public Integer getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public void setEstimatedTimeInMinutes(Integer estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
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

    public void setPetTransport(boolean petTransport) {
        this.petTransport = petTransport;
    }

    public RejectionLetterDTO getRejection() {
        return rejection;
    }

    public void setRejection(RejectionLetterDTO rejection) {
        this.rejection = rejection;
    }

    public LocationDTO getLocations() {
        return locations;
    }

    public void setLocations(LocationDTO locations) {
        this.locations = locations;
    }

    public String getStatus() {
        return status;
    }
    public int getMileage(){
        return 3;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ride RideDTOTORide(){
        Ride ride = new Ride();
        ride.setStartTime(LocalDateTime.parse(this.startTime, DateTimeFormatter.ISO_DATE_TIME));
        ride.setEndTime(LocalDateTime.parse(this.endTime, DateTimeFormatter.ISO_DATE_TIME));
        ride.setTotalCost(this.totalCost);
        ride.setEstimatedTimeInMinutes(this.estimatedTimeInMinutes);
        ride.setPetTransport(this.petTransport);
        ride.setBabyTransport(this.babyTransport);
        ride.setStatus(this.status);
        return ride;
    }
}
