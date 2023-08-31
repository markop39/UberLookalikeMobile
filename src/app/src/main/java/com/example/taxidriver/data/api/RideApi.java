package com.example.taxidriver.data.api;

import com.example.taxidriver.data.dto.CancelDTO;
import com.example.taxidriver.data.dto.FavouriteRouteResponseDTO;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.ResetPasswordDTO;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.dto.RideDTO4;
import com.example.taxidriver.data.dto.RideRequestDTO;
import com.example.taxidriver.domain.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RideApi {

    @GET("/api/ride/get/{id}")
    Call<RideDTO4> getRide(@Path("id") String id);

    @POST("/api/ride")
    Call<RideDTO> submitRequestForRide(@Body RideRequestDTO rideRequestDTO);

    @PUT("/api/ride/{id}/accept")
    Call<Void> acceptRide(@Path("id") String id);

    @PUT("/api/ride/{id}/cancel")
    Call<Void> cancelRide(@Path("id") String id, @Body CancelDTO cancelDTO);

    @PUT("/api/ride/{id}/start")
    Call<Void> startRide(@Path("id") String id);

    @PUT("/api/ride/{id}/finish")
    Call<Void> finishRide(@Path("id") String id);


    @PUT("/api/ride/{id}/panic")
    Call<Void> panicRide(@Path("id") String id);

    @GET("/api/driver/{id}/accepted-rides")
    Call<PaginatedResponse<RideDTO>> getAcceptedRides(@Path("id") String id);

    @GET("api/ride/{passenger-id}/favorites")
    Call<List<FavouriteRouteResponseDTO>> getFavorites(@Path("passenger-id")String passengerId);

    @DELETE("/api/ride/favorites/{id}")
    Call<Void> deleteFavouriteRide(@Path("id")String id);

}
