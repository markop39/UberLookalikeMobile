package com.example.taxidriver.data.api;

import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.ReviewDTO2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewApi {
    @POST("/api/review/{rideId}/vehicle")
    Call<Void> postReviewDVehicle(@Body ReviewDTO2 reviewDTO2, @Path("rideId") String id);


    @POST("/api/review/{rideId}/driver")
    Call<Void> postReviewDriver(@Body ReviewDTO2 reviewDTO2, @Path("rideId") String id);
}
