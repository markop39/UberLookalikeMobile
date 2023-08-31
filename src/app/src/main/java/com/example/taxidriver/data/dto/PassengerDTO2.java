package com.example.taxidriver.data.dto;


import com.example.taxidriver.domain.model.Passenger;

public class PassengerDTO2 {

    private Long id;
    private String email;

    public PassengerDTO2(Passenger passenger) {
        this.id = Long.valueOf(passenger.getId());
        this.email = passenger.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
