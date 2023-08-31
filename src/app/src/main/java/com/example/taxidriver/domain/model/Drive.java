package com.example.taxidriver.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Drive {

    private String id;

    private Integer cost; //
    private Integer mileage; //
    private DrivingStatus status;

    private LocalDateTime startTime; //
    private LocalDateTime endTime; //
    private Duration estimatedTime;


    private Driver driver;
    private VehicleType vehicleType;



    private List<Message> messages; // referenca ka inboxu
    private Route route; // putanju
    private Review review; // ocena i komentar
    private Passenger passenger; //profili putnika
    private List<RejectionLetter> rejectionLetters;


    private boolean panicFlag;
    private boolean babyFlag;
    private boolean petFlag;
    private boolean splitFlag;



    public Drive(String id, Integer cost, Integer mileage, LocalDateTime startTime, LocalDateTime endTime, List<Message> messages, Review reviews, Passenger passengers) {
        this.id = id;
        this.cost = cost;
        this.mileage = mileage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.messages = messages;
        this.review = reviews;
        this.passenger = passengers;
    }

    public Drive(String id, LocalDateTime beginTime, LocalDateTime endTime, Integer cost, Integer mileage, Duration estimatedTime, boolean panicButton, boolean babyDroven, boolean petDroven, boolean splitFare, DrivingStatus drivingStatus, List<Message> messages, Route route, Review review, Passenger passenger, List<RejectionLetter> rejectionLetters, VehicleType vehicleType, Driver driver) {
        this.id = id;
        this.startTime = beginTime;
        this.endTime = endTime;
        this.cost = cost;
        this.mileage = mileage;
        this.estimatedTime = estimatedTime;
        this.panicFlag = panicButton;
        this.babyFlag = babyDroven;
        this.petFlag = petDroven;
        this.splitFlag = splitFare;
        this.status = drivingStatus;
        this.messages = messages;
        this.route = route;
        this.review = review;
        this.passenger = passenger;
        this.rejectionLetters = rejectionLetters;
        this.vehicleType = vehicleType;
        this.driver = driver;
    }

    public Drive(String id, LocalDateTime beginTime, LocalDateTime endTime, Integer cost, Integer mileage, Route route) {
        this.id = id;
        this.startTime = beginTime;
        this.endTime = endTime;
        this.cost = cost;
        this.mileage = mileage;
        this.route = route;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isPanicFlag() {
        return panicFlag;
    }

    public void setPanicFlag(boolean panicFlag) {
        this.panicFlag = panicFlag;
    }

    public boolean isBabyFlag() {
        return babyFlag;
    }

    public void setBabyFlag(boolean babyFlag) {
        this.babyFlag = babyFlag;
    }

    public boolean isPetFlag() {
        return petFlag;
    }

    public void setPetFlag(boolean petFlag) {
        this.petFlag = petFlag;
    }

    public boolean isSplitFlag() {
        return splitFlag;
    }

    public void setSplitFlag(boolean splitFlag) {
        this.splitFlag = splitFlag;
    }

    public DrivingStatus getStatus() {
        return status;
    }

    public void setStatus(DrivingStatus status) {
        this.status = status;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Route getRoutes() {
        return route;
    }

    public void setRoutes(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public List<RejectionLetter> getRejectionLetters() {
        return rejectionLetters;
    }

    public void setRejectionLetters(List<RejectionLetter> rejectionLetters) {
        this.rejectionLetters = rejectionLetters;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
