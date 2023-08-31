package com.example.taxidriver.data.dto;

public class LocationDTO {
    private LocationDTO1 departure;
    private LocationDTO1 destination;

    public LocationDTO(){
    }

    public LocationDTO(LocationDTO1 locationDep, LocationDTO1 locationDes){
        this.departure = locationDep;
        this.destination = locationDes;
    }

    public LocationDTO1 getDeparture() {
        return departure;
    }

    public void setDeparture(LocationDTO1 departure) {
        this.departure = departure;
    }

    public LocationDTO1 getDestination() {
        return destination;
    }

    public void setDestination(LocationDTO1 destination) {
        this.destination = destination;
    }
}
