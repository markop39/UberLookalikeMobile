package com.example.taxidriver.domain.model;
import java.util.List;

public class User {
    Long id;
    String name;
    String surname;
    String email;
    String profilePicture;
    String telephoneNumber;
    String address;
    String password;
    List<Message> messagesSent;
    List<Message> messagesRecived;
    List<RejectionLetter> rejectionLetters;
    boolean is_blocked = false;

    public User() {
    }

    public User(String id, String name, String lastname, String email, String profilePicture, String phoneNumber, String adress, String password, List<Message> messagesSent, List<Message> messagesRecived, List<RejectionLetter> rejectionLetters) {
        this.id = Long.valueOf(id);
        this.name = name;
        this.surname = lastname;
        this.email = email;
        this.profilePicture = profilePicture;
        this.telephoneNumber = phoneNumber;
        this.address = adress;
        this.password = password;
        this.messagesSent = messagesSent;
        this.messagesRecived = messagesRecived;
        this.rejectionLetters = rejectionLetters;
    }

    public User(String id, String name, String lastname, String email, String profilePicture, String phoneNumber, String adress, String password) {
        this.id = Long.valueOf(id);
        this.name = name;
        this.surname = lastname;
        this.email = email;
        this.profilePicture = profilePicture;
        this.telephoneNumber = phoneNumber;
        this.address = adress;
        this.password = password;
        this.messagesSent = messagesSent;
        this.messagesRecived = messagesRecived;
        this.rejectionLetters = rejectionLetters;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public List<Message> getMessagesSent() {
        return messagesSent;
    }

    public void setMessagesSent(List<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public List<Message> getMessagesRecived() {
        return messagesRecived;
    }

    public void setMessagesRecived(List<Message> messagesRecived) {
        this.messagesRecived = messagesRecived;
    }

    public List<RejectionLetter> getRejectionLetters() {
        return rejectionLetters;
    }

    public void setRejectionLetters(List<RejectionLetter> rejectionLetters) {
        this.rejectionLetters = rejectionLetters;
    }
}
