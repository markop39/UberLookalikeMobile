package com.example.taxidriver.data.repository;

import android.widget.Toast;

import com.example.taxidriver.data.RetrofitClient;
import com.example.taxidriver.data.api.DriverApi;
import com.example.taxidriver.data.api.ReviewApi;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.ReviewDTO2;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.dto.VehicleDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewRepository {
    final private ReviewApi reviewApi;

    public ReviewRepository() {
        reviewApi = RetrofitClient.getInstance().create(ReviewApi.class);
    }


    public void postReviewDVehicle(ReviewDTO2 reviewDTO2, String rideId) {
        reviewApi.postReviewDVehicle(reviewDTO2, rideId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });
    }


    public void postReviewDriver(ReviewDTO2 reviewDTO2, String rideId) {
        reviewApi.postReviewDriver(reviewDTO2, rideId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {}

            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
    }


}

