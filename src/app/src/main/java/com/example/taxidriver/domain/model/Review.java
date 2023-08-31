package com.example.taxidriver.domain.model;

public class Review {

    private String id;
    private Integer grade;
    private String comment;

    private Passenger passenger;
    private Drive drive;

    public Review(String id, Integer grade, String comment, Passenger passenger, Drive drive) {
        this.id = id;
        this.grade = grade;
        this.comment = comment;
        this.passenger = passenger;
        this.drive = drive;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }


}
