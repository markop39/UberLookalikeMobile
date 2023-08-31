package com.example.taxidriver.data.dto;

import com.example.taxidriver.data.dto.DriverDTO;
import com.example.taxidriver.data.dto.LocationDTO;
import com.example.taxidriver.data.dto.PassengerDTO2;
import com.example.taxidriver.data.dto.RejectionLetterDTO;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class RideDTO1 {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer totalCost;
    private DriverDTO driverDTO;
    private List<PassengerDTO2> passengerDTO2 = new ArrayList<>(); // prazno
    private Integer estimatedTimeInMinutes;
    private String vehicleType;//
    private boolean babyTransport;//
    private boolean petTransport;//
    private RejectionLetterDTO rejection;
    private LocationDTO locations;//
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStartTime() {

        if(startTime != null)
            return startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return null;
    }

    public LocalDateTime getStartTimeDate() {
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

    public void setStatus(String status) {
        this.status = status;
    }
    public int getMileage(){
        return 2;
    }
}
