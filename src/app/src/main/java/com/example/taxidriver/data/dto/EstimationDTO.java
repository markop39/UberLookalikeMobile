package com.example.taxidriver.data.dto;

public class EstimationDTO {

    private int estimatedTimeInMinutes;
    private int estimatedCost;

    private LocationDTO1 destionation;
    private LocationDTO1 departure;


    public EstimationDTO() {
    }

    public EstimationDTO(int estimatedTimeInMinutes, int estimatedCost) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.estimatedCost = estimatedCost;
    }

    public EstimationDTO(int estimatedTimeInMinutes, int estimatedCost, LocationDTO1 destionation, LocationDTO1 departure) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.estimatedCost = estimatedCost;
        this.destionation = destionation;
        this.departure = departure;
    }

    public void setEstimatedTimeInMinutes(int estimatedTimeInMinutes) {
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
    }

    public void setEstimatedCost(int estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public LocationDTO1 getDestionation() {
        return destionation;
    }

    public void setDestionation(LocationDTO1 destionation) {
        this.destionation = destionation;
    }

    public LocationDTO1 getDeparture() {
        return departure;
    }

    public void setDeparture(LocationDTO1 departure) {
        this.departure = departure;
    }

    public int getEstimatedTimeInMinutes() {
        return estimatedTimeInMinutes;
    }

    public int getEstimatedCost() {
        return estimatedCost;
    }


}
