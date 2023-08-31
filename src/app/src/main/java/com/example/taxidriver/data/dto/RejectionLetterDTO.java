package com.example.taxidriver.data.dto;


import com.example.taxidriver.domain.model.RejectionLetter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class RejectionLetterDTO {
    private String timeOfRejection;
    private String reason;

    public RejectionLetterDTO(RejectionLetter rejectionLetter){
        this.reason = rejectionLetter.getReason();
        this.timeOfRejection = rejectionLetter.getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getTimeOfRejection() {
        return timeOfRejection;
    }

    public void setTimeOfRejection(String timeOfRejection) {
        this.timeOfRejection = timeOfRejection;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
