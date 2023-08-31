package com.example.taxidriver.domain.model;

import com.example.taxidriver.data.dto.DriverDTO;

import java.util.List;

public class Driver extends User{

    String drivingLicense;
    String trafficLicense;
    boolean isActive;
    Vehicle vehicle;
    List<Drive> drives;

    public Driver(String id, String name, String lastname, String email, String profilePicture, String phoneNumber, String adress, String password, List<Message> messagesSent, List<Message> messagesRecived, List<RejectionLetter> rejectionLetters, String driving_license, String traffic_license, boolean is_active, Vehicle vehicle, List<Drive> drives) {
        super(id, name, lastname, email, profilePicture, phoneNumber, adress, password, messagesSent, messagesRecived, rejectionLetters);
        this.drivingLicense = driving_license;
        this.trafficLicense = traffic_license;
        this.isActive = is_active;
        this.vehicle = vehicle;
        this.drives = drives;
    }

    public Driver(DriverDTO driverDTO) {
        super(String.valueOf(driverDTO.getId()), null, null, driverDTO.getEmail(), null, null, null, null, null, null, null);
        this.drivingLicense = null;
        this.trafficLicense = null;
        this.isActive = false;
        this.vehicle = null;
        this.drives = null;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getTrafficLicense() {
        return trafficLicense;
    }

    public void setTrafficLicense(String trafficLicense) {
        this.trafficLicense = trafficLicense;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Drive> getDrives() {
        return drives;
    }

    public void setDrives(List<Drive> drives) {
        this.drives = drives;
    }
}



