package com.example.taxidriver.data.repository;

import com.example.taxidriver.data.RetrofitClient;
import com.example.taxidriver.data.api.DriverApi;
import com.example.taxidriver.data.api.PassengerApi;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.PassengerDTO;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.dto.RideDTO1;
import com.example.taxidriver.data.dto.VehicleDTO;
import com.example.taxidriver.domain.model.Ride;

import java.util.List;

import retrofit2.Callback;

public class PassengerRepository {
    final private PassengerApi passengerApi;

    public PassengerRepository() {
        passengerApi = RetrofitClient.getInstance().create(PassengerApi.class);
    }

    public void getPassengerDetails(Callback<PassengerDTO> callback, String id) {
        passengerApi.getPassengerDetails(id).enqueue(callback);
    }
    public void putPassengerDetails(Callback<PassengerDTO> callback,String id, PassengerDTO request){
        passengerApi.changePassengerDetails(id, request).enqueue(callback);
    }
    public void getPassengerRides(Callback<PaginatedResponse<RideDTO>> callback, String id){
        passengerApi.getRides(id).enqueue(callback);
    }
}
