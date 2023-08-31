package com.example.taxidriver.data.dto;

import com.example.taxidriver.domain.model.Driver;
import com.example.taxidriver.domain.model.User;

public class DriverDTO1 {
        private long id;
        private String name;
        private String surname;
        private String profilePicture;
        private String telephoneNumber;
        private String email;
        private String address;

    public DriverDTO1() {
    }

    public DriverDTO1(Driver driver){
            this.id = driver.getId();
            this.name = driver.getName();
            this.surname = driver.getSurname();
            this.profilePicture = driver.getProfilePicture();
            this.telephoneNumber = driver.getTelephoneNumber();
            this.email = driver.getEmail();
            this.address = driver.getAddress();
        }

        public DriverDTO1(User userLoged, int a) {
            this.id = userLoged.getId();
            this.name = userLoged.getName();
            this.surname = userLoged.getSurname();
            this.profilePicture = userLoged.getProfilePicture();
            this.telephoneNumber = userLoged.getTelephoneNumber();
            this.email = userLoged.getEmail();
            this.address = userLoged.getAddress();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        public void setSurname(String surname) {
            this.surname = surname;
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

        public void setTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }