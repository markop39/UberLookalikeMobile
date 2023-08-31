package com.example.taxidriver.data.api;

import com.example.taxidriver.data.dto.FavouriteRouteResponseDTO;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.PassengerDTO;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.dto.RideDTO1;
import com.example.taxidriver.data.dto.VehicleDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PassengerApi {

    @GET("api/passenger/{passenger-id}")
    Call<PassengerDTO> getPassengerDetails(@Path("passenger-id") String id);

    @PUT("api/passenger/{passenger-id}")
    Call<PassengerDTO> changePassengerDetails(@Path("passenger-id") String id,@Body PassengerDTO request);

    @GET("api/passenger/{passenger-id}/ride")
    Call<PaginatedResponse<RideDTO>> getRides(@Path("passenger-id") String passengerId);


}
