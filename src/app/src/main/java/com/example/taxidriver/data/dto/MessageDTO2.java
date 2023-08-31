package com.example.taxidriver.data.dto;

public class MessageDTO2 {
    private String message;
    private Long rideId;
    private String type;



    public MessageDTO2(Long receiverId, String message, Long rideId, String type) {
        this.message = message;
        this.rideId = rideId;
        this.type = type;
    }

    public MessageDTO2() {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
