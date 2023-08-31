package com.example.taxidriver.data.repository;

import com.example.taxidriver.data.RetrofitClient;
import com.example.taxidriver.data.api.DriverApi;
import com.example.taxidriver.data.api.VehicleApi;
import com.example.taxidriver.data.dto.ActiveVehicleDTO;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;

import retrofit2.Callback;


public class VehicleRepository {
    final private VehicleApi vehicleApi;

    public VehicleRepository() {
        vehicleApi = RetrofitClient.getInstance().create(VehicleApi.class);
    }

    public void getAllActiveVehicles(Callback<PaginatedResponse<ActiveVehicleDTO>> callback) {
        vehicleApi.getAllActiveVehicles().enqueue(callback);
    }

}

