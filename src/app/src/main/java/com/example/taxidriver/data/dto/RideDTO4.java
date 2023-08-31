package com.example.taxidriver.data.dto;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RideDTO4 {

    private String startTime;
    private String endTime;
    private Integer totalCost;
    private String passengerEmail;
    private String startPoint;
    private String endPoint;
    private Integer grade;
    private String comment;
    private LocationDTO3 cordinateStart;
    private LocationDTO3 cordinateEnd;

    public RideDTO4() { }

    public LocationDTO3 getCordinateStart() {
        return cordinateStart;
    }

    public void setCordinateStart(LocationDTO3 cordinateStart) {
        this.cordinateStart = cordinateStart;
    }

    public LocationDTO3 getCordinateEnd() {
        return cordinateEnd;
    }

    public void setCordinateEnd(LocationDTO3 cordinateEnd) {
        this.cordinateEnd = cordinateEnd;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
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

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
