package com.example.taxidriver.domain.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.data.repository.PassengerRepository;
import com.example.taxidriver.domain.model.Ride;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RideHistoryViewModel extends ViewModel {

    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;

    private MutableLiveData<List<RideDTO>> rideHistory;

    public RideHistoryViewModel() {
        driverRepository = new DriverRepository();
        passengerRepository = new PassengerRepository();
        rideHistory = new MutableLiveData<>();
    }

    public LiveData<List<RideDTO>> getRideHistory() {
        return rideHistory;
    }

    public void fetchRideHistory(String id) {
        driverRepository.getRideHistory(new Callback<PaginatedResponse<RideDTO>>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedResponse<RideDTO>> call, @NonNull Response<PaginatedResponse<RideDTO>> response) {
                if (response.isSuccessful()) {
                    PaginatedResponse<RideDTO> paginatedResponse = response.body();
                    assert paginatedResponse != null;
                    Toast.makeText(TaxiDriver.getAppContext(), "Ride History, success", Toast.LENGTH_SHORT).show();
                    List<RideDTO> ridesDTO = paginatedResponse.getResults();
                    rideHistory.postValue(ridesDTO);


                } else {
                    Toast.makeText(TaxiDriver.getAppContext(), "Ride History, response body wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedResponse<RideDTO>> call, Throwable t) {
              Toast.makeText(TaxiDriver.getAppContext(), "Ride History, on failure.", Toast.LENGTH_SHORT).show();
            }
        }, id);
    }
    public void fetchPassengerRideHistory(String id){
        passengerRepository.getPassengerRides(new Callback<PaginatedResponse<RideDTO>>() {
            @Override
            public void onResponse(Call<PaginatedResponse<RideDTO>> call, Response<PaginatedResponse<RideDTO>> response) {
                if (response.isSuccessful()) {
                    PaginatedResponse<RideDTO> paginatedResponse = response.body();
                    assert paginatedResponse != null;
                    Toast.makeText(TaxiDriver.getAppContext(), "Ride History, success", Toast.LENGTH_SHORT).show();
                    List<RideDTO> ridesDTO = paginatedResponse.getResults();
                    rideHistory.postValue(ridesDTO);


                } else {
                    Toast.makeText(TaxiDriver.getAppContext(), "Ride History, response body wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaginatedResponse<RideDTO>> call, Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), "Ride History, on failure.", Toast.LENGTH_SHORT).show();
            }
        }, id);
    }
}
