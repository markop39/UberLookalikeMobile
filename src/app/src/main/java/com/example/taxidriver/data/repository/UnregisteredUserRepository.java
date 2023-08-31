package com.example.taxidriver.data.repository;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.RetrofitClient;
import com.example.taxidriver.data.api.UnregisteredUserApi;
import com.example.taxidriver.data.api.UserApi;
import com.example.taxidriver.data.dto.ChangePasswordCodeDTO;
import com.example.taxidriver.data.dto.EstimationDTO;
import com.example.taxidriver.data.dto.EstimationRequestDTO2;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.ResetPasswordDTO;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.domain.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UnregisteredUserRepository {
    final private UnregisteredUserApi unregisteredUserApi;

    public UnregisteredUserRepository() {
        unregisteredUserApi = RetrofitClient.getInstance().create(UnregisteredUserApi.class);
    }

    public void getEstimation(Callback<EstimationDTO> callback, EstimationRequestDTO2 estimationRequest) {
        unregisteredUserApi.getEstimation(estimationRequest).enqueue(callback);
    }






}

