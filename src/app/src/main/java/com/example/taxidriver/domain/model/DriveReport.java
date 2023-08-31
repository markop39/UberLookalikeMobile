package com.example.taxidriver.domain.model;

import java.time.LocalDateTime;

public class DriveReport {
    public LocalDateTime date;
    public Integer rides;
    public Integer mileage;
    public Integer spent;

    public DriveReport(LocalDateTime date, Integer rides, Integer mileage, Integer spent) {
        this.date = date;
        this.rides = rides;
        this.mileage = mileage;
        this.spent = spent;
    }

    public DriveReport() {
    }

}
