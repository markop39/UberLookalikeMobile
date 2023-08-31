package com.example.taxidriver.domain.model;

import java.util.List;

public class Passenger extends User{

    List<Drive> drives;
    List<Review> reviews;
    List<Payment> payments;
    List<FavoriteRoute> favoriteRoutes;

    public Passenger(String id, String name, String lastname, String email, String profilePicture, String phoneNumber, String adress, String password, List<Message> messagesSent, List<Message> messagesRecived, List<RejectionLetter> rejectionLetters, List<Drive> drives, List<Review> reviews, List<Payment> payments, List<FavoriteRoute> favoriteRoutes) {
        super(id, name, lastname, email, profilePicture, phoneNumber, adress, password, messagesSent, messagesRecived, rejectionLetters);
        this.drives = drives;
        this.reviews = reviews;
        this.payments = payments;
        this.favoriteRoutes = favoriteRoutes;
    }

    public Passenger() {
    }

    public List<Drive> getDrives() {
        return drives;
    }

    public void setDrives(List<Drive> drives) {
        this.drives = drives;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<FavoriteRoute> getFavoriteRoutes() {
        return favoriteRoutes;
    }

    public void setFavoriteRoutes(List<FavoriteRoute> favoriteRoutes) {
        this.favoriteRoutes = favoriteRoutes;
    }
}
