package com.example.taxidriver.data.api;

import com.example.taxidriver.data.dto.ChangePasswordCodeDTO;
import com.example.taxidriver.data.dto.EstimationDTO;
import com.example.taxidriver.data.dto.EstimationRequestDTO2;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.ResetPasswordDTO;
import com.example.taxidriver.domain.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UnregisteredUserApi {

    @POST("/api/unregisteredUser2")
    Call<EstimationDTO> getEstimation(@Body EstimationRequestDTO2 estimationRequest);
}
