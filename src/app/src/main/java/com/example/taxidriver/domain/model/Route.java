package com.example.taxidriver.domain.model;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class Route {

    private String id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer mileage;
    private Integer estimatedTime;
    private Integer price;
    private Integer serialNumber;

    private Drive drive;

    private String startPoint;
    private String endPoint;

    public Route(String startPoint, String endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Route(String id, LocalDateTime startTime, LocalDateTime endTime, Integer mileage, Integer estimatedTime, Integer price, Integer serialNumber, Drive drive, String startPoint, String destination) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mileage = mileage;
        this.estimatedTime = estimatedTime;
        this.price = price;
        this.serialNumber = serialNumber;
        this.drive = drive;
        this.startPoint = startPoint;
        this.endPoint = destination;
    }

    public Route(LocalDateTime startTime, LocalDateTime endTime, Integer mileage, Integer price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.mileage = mileage;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @NonNull
    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", mileage=" + mileage +
                ", estimatedTime=" + estimatedTime +
                ", price=" + price +
                ", serialNumber=" + serialNumber +
                ", drive=" + drive +
                ", startPoint=" + startPoint +
                ", destination=" + endPoint +
                '}';
    }
}
