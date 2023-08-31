package com.example.taxidriver.data.dto;


import com.example.taxidriver.domain.model.Driver;

public class DriverDTO {

    private Long id;
    private String email;

    public DriverDTO(Driver driver) {
        this.id = Long.valueOf(driver.getId());
        this.email = driver.getEmail();
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
