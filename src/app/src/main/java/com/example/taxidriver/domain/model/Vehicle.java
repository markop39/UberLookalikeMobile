package com.example.taxidriver.domain.model;

public class Vehicle {
    String id;
    String model;
    String registrationPlate;
    Integer numberOfSpaces;
    boolean babyDroven;
    boolean petDroven;
    Driver driver;
    VehicleType vehicleType;

    public Vehicle(String id, String model, String registrationPlate, Integer numberOfSpaces, boolean babyDroven, boolean petDroven, Driver driver, VehicleType vehicleType) {
        this.id = id;
        this.model = model;
        this.registrationPlate = registrationPlate;
        this.numberOfSpaces = numberOfSpaces;
        this.babyDroven = babyDroven;
        this.petDroven = petDroven;
        this.driver = driver;
        this.vehicleType = vehicleType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public Integer getNumberOfSpaces() {
        return numberOfSpaces;
    }

    public void setNumberOfSpaces(Integer numberOfSpaces) {
        this.numberOfSpaces = numberOfSpaces;
    }

    public boolean isBabyDroven() {
        return babyDroven;
    }

    public void setBabyDroven(boolean babyDroven) {
        this.babyDroven = babyDroven;
    }

    public boolean isPetDroven() {
        return petDroven;
    }

    public void setPetDroven(boolean petDroven) {
        this.petDroven = petDroven;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
