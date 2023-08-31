package com.example.taxidriver.domain.viewmodel;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.ActiveVehicleDTO;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.RideRepository;
import com.example.taxidriver.data.repository.VehicleRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverMainViewModel extends ViewModel {

    private final VehicleRepository vehicleRepository;
    private final RideRepository rideRepository;

    private MutableLiveData<List<RideDTO>> scheduledRides;


    private final MutableLiveData<List<ActiveVehicleDTO>> activeVehiclesLocation;

    public DriverMainViewModel() {
        vehicleRepository = new VehicleRepository();
        rideRepository = new RideRepository();
        activeVehiclesLocation = new MutableLiveData<>();
        scheduledRides = new MutableLiveData<>();
    }
    public LiveData<List<RideDTO>> getScheduledRides() {
        return scheduledRides;
    }

    public void fetchScheduledRides(String id) {
        rideRepository.getAcceptedRides(new Callback<PaginatedResponse<RideDTO>>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedResponse<RideDTO>> call, @NonNull Response<PaginatedResponse<RideDTO>> response) {
                if (response.isSuccessful()) {
                    PaginatedResponse<RideDTO> paginatedResponse = response.body();
                    assert paginatedResponse != null;
                    Toast.makeText(TaxiDriver.getAppContext(), "Scheduled rides, success", Toast.LENGTH_SHORT).show();
                    List<RideDTO> ridesDTO = paginatedResponse.getResults();
                    scheduledRides.postValue(ridesDTO);


                } else {
                    Toast.makeText(TaxiDriver.getAppContext(), "Scheduled rides, response body wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedResponse<RideDTO>> call, Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), "Scheduled rides, on failure.", Toast.LENGTH_SHORT).show();
            }
        }, id);
    }

    public LiveData<List<ActiveVehicleDTO>> getAllActiveVehicles() {
        return activeVehiclesLocation;
    }

    public void fetchActiveVehiclesLocation() {
        vehicleRepository.getAllActiveVehicles(new Callback<PaginatedResponse<ActiveVehicleDTO>>() {
            @Override
            public void onResponse(@NonNull Call<PaginatedResponse<ActiveVehicleDTO>> call, @NonNull Response<PaginatedResponse<ActiveVehicleDTO>> response) {
                if (response.isSuccessful()) {
                    PaginatedResponse<ActiveVehicleDTO> paginatedResponse = response.body();
                    assert paginatedResponse != null;
                    Toast.makeText(TaxiDriver.getAppContext(), "Active Vehicles Location, success", Toast.LENGTH_SHORT).show();
                    List<ActiveVehicleDTO> list = paginatedResponse.getResults();
                    activeVehiclesLocation.postValue(list);


                } else {
                    Toast.makeText(TaxiDriver.getAppContext(), "Active Vehicles Location, response body wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PaginatedResponse<ActiveVehicleDTO>> call, Throwable t) {
              Toast.makeText(TaxiDriver.getAppContext(), "Active Vehicles Location, on failure.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
