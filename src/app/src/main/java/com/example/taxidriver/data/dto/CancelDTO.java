package com.example.taxidriver.data.dto;

public class CancelDTO {

    private String reason;


    public CancelDTO(String reason) {
        this.reason = reason;
    }


    public CancelDTO() {
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
