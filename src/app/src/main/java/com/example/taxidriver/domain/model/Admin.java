package com.example.taxidriver.domain.model;

import java.util.List;

public class Admin extends User{


    public Admin(String id, String name, String lastname, String email, String profilPicture, String phoneNumber, String adress, String password, List<Message> messagesSent, List<Message> messagesRecived, List<RejectionLetter> rejectionLetters) {
        super(id, name, lastname, email,  profilPicture, phoneNumber, adress, password, messagesSent, messagesRecived, rejectionLetters);
    }
}
