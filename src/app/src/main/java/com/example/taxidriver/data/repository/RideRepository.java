package com.example.taxidriver.data.repository;

import android.widget.Toast;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.RetrofitClient;
import com.example.taxidriver.data.api.DriverApi;
import com.example.taxidriver.data.api.RideApi;
import com.example.taxidriver.data.dto.CancelDTO;
import com.example.taxidriver.data.dto.FavouriteRouteResponseDTO;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.dto.RideDTO4;
import com.example.taxidriver.data.dto.RideRequestDTO;
import com.example.taxidriver.domain.model.Ride;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RideRepository {
    final private RideApi rideApi;

    public RideRepository() {
        rideApi = RetrofitClient.getInstance().create(RideApi.class);
    }

    public void getRide(Callback<RideDTO4> callback, String id) {
        rideApi.getRide(id).enqueue(callback);
    }

    public void submitRideRequest(Callback<RideDTO> callback, RideRequestDTO rideRequest) {
        rideApi.submitRequestForRide(rideRequest).enqueue(callback);
    }

    public void getAcceptedRides(Callback<PaginatedResponse<RideDTO>> callback, String id) {
        rideApi.getAcceptedRides(id).enqueue(callback);
    }

    public void acceptRide(Callback<Void> callback,  String id) {
        rideApi.acceptRide(id).enqueue(callback);
    }

    public void cancelRide(Callback<Void> callback, String id, CancelDTO cancelDTO) {
        rideApi.cancelRide(id, cancelDTO).enqueue(callback);
    }

    public void panicRide(Callback<Void> callback, String id) {
        rideApi.panicRide(id).enqueue(callback);
    }


    public void startRide(Callback<Void> callback,  String id) {
        rideApi.startRide(id).enqueue(callback);
    }

    public void finishRide(Callback<Void> callback,  String id) {
        rideApi.finishRide(id).enqueue(callback);
    }
    public void getPassengerFavorites(Callback<List<FavouriteRouteResponseDTO>> callback, String id){
        rideApi.getFavorites(id).enqueue(callback);
    }

    public void deleteFavoriteRide(Callback<Void> callback, String id){
        rideApi.deleteFavouriteRide(id);
    }

}

