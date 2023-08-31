package com.example.taxidriver.domain.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.dto.VehicleDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.data.repository.RideRepository;
import com.example.taxidriver.data.repository.UserRepository;
import com.example.taxidriver.data.repository.VehicleRepository;
import com.example.taxidriver.domain.model.Drive;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentRideViewModel extends ViewModel {

    private final UserRepository userRepository;

    private MutableLiveData<LocationDTO3> vehicleLocation;


    public CurrentRideViewModel() {
        userRepository = new UserRepository();
        vehicleLocation = new MutableLiveData<>();
    }

    public LiveData<LocationDTO3> getVehicle() {
        return vehicleLocation;
    }

    public void fetchCurrentLocationVehicle() {
        userRepository.getCurrentLocation(new Callback<LocationDTO3>() {
            @Override
            public void onResponse(@NonNull Call<LocationDTO3> call, @NonNull Response<LocationDTO3> response) {
                if (response.isSuccessful()) {
                    LocationDTO3 locationDTO3 = response.body();
                    assert locationDTO3 != null;
                    vehicleLocation.postValue(locationDTO3);


                } else {
                    Toast.makeText(TaxiDriver.getAppContext(), "Vehicle, response body wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LocationDTO3> call, Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), " Vehicle, on failure.", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
