package com.example.taxidriver.data.dto;

import com.example.taxidriver.domain.model.Passenger;

public class PassengerDTO {
    private Long id;

    private String name;

    private String surname;

    private String password;

    private String profilePicture;

    private String telephoneNumber;

    private String email;

    private String address;

    public PassengerDTO() {
    }

    public PassengerDTO(String name, String surname, String password, String profilePicture, String telephoneNumber, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.profilePicture = profilePicture;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String lastname) {
        this.surname = lastname;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String phoneNumber) {
        this.telephoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String address) {
        this.email = address;
    }

    public Passenger passengerChanging(Passenger passenger){
        if(this.address != null){
            passenger.setAddress(this.getAddress());
        }
        if(this.email != null){
            passenger.setEmail(this.getEmail());
        }
        if(this.profilePicture != null){
            passenger.setProfilePicture(this.getProfilePicture());
        }
        if(this.telephoneNumber != null){
            passenger.setTelephoneNumber(this.getTelephoneNumber());
        }
        if(this.getSurname() != null){
            passenger.setSurname(this.getSurname());
        }
        if(this.getName() != null){
            passenger.setName(this.getName());
        }
        return passenger;
    }

}
