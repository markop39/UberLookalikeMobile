package com.example.taxidriver.domain.model;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;

public class RejectionLetter {

    private String id;
    private String reason;
    private LocalDateTime dateTime;

    private User user;
    private Drive drive;

    public RejectionLetter(String id, String reason, LocalDateTime dateTime, User user, Drive drive) {
        this.id = id;
        this.reason = reason;
        this.dateTime = dateTime;
        this.user = user;
        this.drive = drive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }


    @NonNull
    @Override
    public String toString() {
        return "RejectionLetter{" +
                "id='" + id + '\'' +
                ", reasoon='" + reason + '\'' +
                ", dateTime=" + dateTime +
                ", user=" + user +
                ", drive=" + drive +
                '}';
    }
}
