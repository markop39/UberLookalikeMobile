package com.example.taxidriver.util;

import com.example.taxidriver.domain.model.Drive;
import com.example.taxidriver.domain.model.DrivingStatus;
import com.example.taxidriver.domain.model.FavoriteRoute;
import com.example.taxidriver.domain.model.Location;
import com.example.taxidriver.domain.model.Driver;
import com.example.taxidriver.domain.model.Message;
import com.example.taxidriver.domain.model.MessageType;
import com.example.taxidriver.domain.model.Passenger;
import com.example.taxidriver.domain.model.Review;
import com.example.taxidriver.domain.model.Route;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mockup {


    public static List<Passenger> getPassengers(){
        List<Passenger> passengers = new ArrayList<Passenger>();

        Passenger p1 = new Passenger("1", "Pera", "Peric", "passe", "z", "+381957291", "Novi Sad, Narodnog Fronta 15", "123", null, null, null, null, null, null, null);

        passengers.add(p1);

        return passengers;

    }

    public static List<Driver> getDrivers()
    {
        List<Driver> drivers = new ArrayList<Driver>();

        Driver d1 = new Driver("1", "Vozac", "Najbrzi", "drive", "sga", "+381999999999", "Novi Sad, Bulevar Oslobodjenja 15", "123", null, null, null, "A","Saobracajna", true, null, null);

        drivers.add(d1);

        return drivers;
    }





        public static List<Drive> getDrives2() {

        //  Drive(String id, Integer cost, Integer mileage, DrivingStatus status, LocalDateTime startTime, LocalDateTime endTime, Duration estimatedTime, Driver driver, VehicleType vehicleType, List<Message> messages, Route route, Review review, Passenger passenger, RejectionLetter rejectionLetter, boolean panicFlag, boolean babyFlag, boolean petFlag, boolean splitFlag) {


                List<Drive> drives = new ArrayList<>();

        drives.add(new Drive("1",  LocalDateTime.of(2022, 11, 14, 0, 0), LocalDateTime.of(2022, 11, 14, 1, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Bul. Kralja Petra 1", "Gagarinova 10/V"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
        drives.add(new Drive("2", LocalDateTime.of(2022, 11, 14, 1, 15), LocalDateTime.of(2022, 11, 14, 1, 45), 500, 2, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cara Dusana 12", "Cara Urosa 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
        drives.add(new Drive("3", LocalDateTime.of(2022, 11, 15, 0, 0), LocalDateTime.of(2022, 11, 15, 1, 0), 2000, 15, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cetinjska 3", "Dalmatinska 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
        drives.add(new Drive("4",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 30, 3, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Dinarska 12", "Dositejeva 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
        drives.add(new Drive("5",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Futoska 2", "Igmanska 8"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));

            drives.add(new Drive("1",  LocalDateTime.of(2022, 11, 14, 0, 0), LocalDateTime.of(2022, 11, 14, 1, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Bul. Kralja Petra 1", "Gagarinova 10/V"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("2", LocalDateTime.of(2022, 11, 14, 1, 15), LocalDateTime.of(2022, 11, 14, 1, 45), 500, 2, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cara Dusana 12", "Cara Urosa 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("3", LocalDateTime.of(2022, 11, 15, 0, 0), LocalDateTime.of(2022, 11, 15, 1, 0), 2000, 15, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cetinjska 3", "Dalmatinska 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("4",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 30, 3, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Dinarska 12", "Dositejeva 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("5",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Futoska 2", "Igmanska 8"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));

            drives.add(new Drive("1",  LocalDateTime.of(2022, 11, 14, 0, 0), LocalDateTime.of(2022, 11, 14, 1, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Bul. Kralja Petra 1", "Gagarinova 10/V"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("2", LocalDateTime.of(2022, 11, 14, 1, 15), LocalDateTime.of(2022, 11, 14, 1, 45), 500, 2, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cara Dusana 12", "Cara Urosa 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("3", LocalDateTime.of(2022, 11, 15, 0, 0), LocalDateTime.of(2022, 11, 15, 1, 0), 2000, 15, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cetinjska 3", "Dalmatinska 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("4",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 30, 3, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Dinarska 12", "Dositejeva 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("5",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Futoska 2", "Igmanska 8"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));

            drives.add(new Drive("1",  LocalDateTime.of(2022, 11, 14, 0, 0), LocalDateTime.of(2022, 11, 14, 1, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Bul. Kralja Petra 1", "Gagarinova 10/V"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("2", LocalDateTime.of(2022, 11, 14, 1, 15), LocalDateTime.of(2022, 11, 14, 1, 45), 500, 2, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cara Dusana 12", "Cara Urosa 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("3", LocalDateTime.of(2022, 11, 15, 0, 0), LocalDateTime.of(2022, 11, 15, 1, 0), 2000, 15, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cetinjska 3", "Dalmatinska 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("4",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 30, 3, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Dinarska 12", "Dositejeva 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("5",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Futoska 2", "Igmanska 8"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));

            drives.add(new Drive("1",  LocalDateTime.of(2022, 11, 14, 0, 0), LocalDateTime.of(2022, 11, 14, 1, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Bul. Kralja Petra 1", "Gagarinova 10/V"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("2", LocalDateTime.of(2022, 11, 14, 1, 15), LocalDateTime.of(2022, 11, 14, 1, 45), 500, 2, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cara Dusana 12", "Cara Urosa 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("3", LocalDateTime.of(2022, 11, 15, 0, 0), LocalDateTime.of(2022, 11, 15, 1, 0), 2000, 15, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Cetinjska 3", "Dalmatinska 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("4",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 30, 3, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Dinarska 12", "Dositejeva 4"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));
            drives.add(new Drive("5",LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 1500, 10, null, false, false, false, false, DrivingStatus.COMPLETED, getMessages(),new Route("Futoska 2", "Igmanska 8"),getReviews().get(0),getPassengers().get(0),null,null, getDriver()));





            return drives;
    }







        public static Passenger getPassenger(){
        return new Passenger("1", "Pera", "Peric", "pera@email.com", "z", "+381957291", "Novi Sad, Narodnog Fronta 15", "sifra123", null, null, null, null, null, null, null);
    }

    public static List<Review> getReviews(){
        Review r1 = new Review("1", 130, "Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi.", getPassenger(),getDrive());
        Review r2 = new Review("2", 120, "Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi.", getPassenger(),getDrive());
        Review r3 = new Review("3", 150, "Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! ", getPassenger(),getDrive());
        Review r4 = new Review("4", 200, "Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! ", getPassenger(),getDrive());
        Review r5 = new Review("5", 600, "Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! Bas me vozi. Super vozac! ", getPassenger(),getDrive());

        List<Review> reviews = new ArrayList<>();

        reviews.add(r1);
        reviews.add(r2);
        reviews.add(r3);
        reviews.add(r4);
        reviews.add(r5);

        return reviews;

    }



        public static Driver getDriver(){
        return new Driver("1", "Vozac", "Najbrzi", "vozac@gmail.com", "sga", "+381999999999", "Novi Sad, Bulevar Oslobodjenja 15", "sifra", getMessages(), null, null, "A","Saobracajna", true, null, null);

    }

    public static ArrayList<FavoriteRoute> getFavoriteRoutes(){
        ArrayList<FavoriteRoute> list = new ArrayList<FavoriteRoute>();
        FavoriteRoute fr1 = new FavoriteRoute("1", getPassenger(), new Location("1", 1, 1), new Location("2", 2, 2));
        FavoriteRoute fr2 = new FavoriteRoute("2", getPassenger(), new Location("3", 3, 3), new Location("4", 4, 4));
        FavoriteRoute fr3 = new FavoriteRoute("3", getPassenger(), new Location("5", 5, 5), new Location("6", 6, 6));
        FavoriteRoute fr4 = new FavoriteRoute("4", getPassenger(), new Location("7", 7, 7), new Location("8", 8, 8));
        FavoriteRoute fr5 = new FavoriteRoute("5", getPassenger(), new Location("1", 1, 1), new Location("4", 4, 4));
        FavoriteRoute fr6 = new FavoriteRoute("6", getPassenger(), new Location("9", 9, 9), new Location("6", 6, 6));
        list.add(fr1);
        list.add(fr2);
        list.add(fr3);
        list.add(fr4);
        list.add(fr5);
        list.add(fr6);
        return list;
    }

    public static ArrayList<Drive> getDrives(){
        ArrayList<Drive> rides = new ArrayList<Drive>();
        rides.add(new Drive("1", LocalDateTime.of(2022, 11, 14, 0, 0), LocalDateTime.of(2022, 11, 14, 1, 0), 1500, 10, null));
        rides.add(new Drive("2", LocalDateTime.of(2022, 11, 14, 1, 15), LocalDateTime.of(2022, 11, 14, 1, 45), 500, 2,null));
        rides.add(new Drive("3", LocalDateTime.of(2022, 11, 15, 0, 0), LocalDateTime.of(2022, 11, 15, 1, 0), 2000, 15, null));
        rides.add(new Drive("4", LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 30, 3, null));
        rides.add(new Drive("5", LocalDateTime.of(2022, 11, 16, 7, 0), LocalDateTime.of(2022, 11, 16, 9, 0), 1500, 10, null));
        rides.add(new Drive("6", LocalDateTime.of(2022, 11, 18, 0, 0), LocalDateTime.of(2022, 11, 18, 1, 0), 1500, 10, null));

        return rides;
    }

    public static Drive getDrive(){
        return new Drive("1", LocalDateTime.of(2022, 11, 14, 0, 0), LocalDateTime.of(2022, 11, 14, 1, 0), 1500, 10, null);
    }

    public static ArrayList<Message> getMessages(){
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message("1", "Sve je uredu. ",LocalDateTime.of(2022, 11, 14, 0, 0), MessageType.Drive, getDrive()));
        messages.add(new Message("1", "Sve je uredu.",LocalDateTime.of(2022, 11, 15, 0, 0),  MessageType.Drive, getDrive()));
        messages.add(new Message("1", "VOZILO KASNI.",LocalDateTime.of(2022, 11, 13, 0, 0),  MessageType.Support, getDrive()));
        messages.add(new Message("1", "PANIKA.",LocalDateTime.of(2022, 11, 12, 0, 0),  MessageType.Panic, getDrive()));
        return messages;
    }
}
