package com.example.taxidriver.data.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageDTO {

    private Long id;
    private Long receiverId;
    private Long senderId;
    private String message;
    private String timeOfSending;
    private Long rideId;
    private String type;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeOfSending() {
        return timeOfSending;
    }

    public void setTimeOfSending(String timeOfSending) {
        this.timeOfSending = timeOfSending;
    }


    //    public String getTimeOfSending() {
//        return timeOfSending.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//    }
//
//    public void setTimeOfSending(LocalDateTime timeOfSending) {
//        this.timeOfSending = timeOfSending;
//    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
