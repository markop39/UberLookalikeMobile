package com.example.taxidriver.domain.viewmodel;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.api.VehicleApi;
import com.example.taxidriver.data.dto.ActiveVehicleDTO;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.data.repository.VehicleRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerMainViewModel extends ViewModel {

    private final VehicleRepository vehicleRepository;

    private final MutableLiveData<List<ActiveVehicleDTO>> activeVehiclesLocation;

    public PassengerMainViewModel() {
        vehicleRepository = new VehicleRepository();
        activeVehiclesLocation = new MutableLiveData<>();
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
