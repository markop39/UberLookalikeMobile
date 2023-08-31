package com.example.taxidriver.data.api;

import com.example.taxidriver.data.dto.ActiveVehicleDTO;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VehicleApi {

    @GET("/api/vehicle/active2")
    Call<PaginatedResponse<ActiveVehicleDTO>> getAllActiveVehicles();

}
