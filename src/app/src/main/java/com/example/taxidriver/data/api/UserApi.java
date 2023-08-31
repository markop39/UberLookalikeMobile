package com.example.taxidriver.data.api;

import com.example.taxidriver.data.dto.ChangePasswordCodeDTO;
import com.example.taxidriver.data.dto.ChangePasswordDTO;
import com.example.taxidriver.data.dto.IsInRideDTO;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.MessageDTO;
import com.example.taxidriver.data.dto.MessageDTO2;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.ResetPasswordDTO;
import com.example.taxidriver.domain.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @PUT("api/user/{id}/changePassword")
    Call<Void> changePassword(@Path("id") String id, @Body ChangePasswordDTO request);

    @GET("api/user")
    Call<PaginatedResponse<User>> getUsers();

    @GET("api/user/location")
    Call<LocationDTO3> getCurrentLocation();


    @GET("/api/user/is-in-ride")
    Call<IsInRideDTO> isUserInRide();

    @POST("/api/user/code")
    Call<Void> sendCode(@Body ResetPasswordDTO resetPasswordDTO);

    @PUT("/api/user/resetPassword")
    Call<Void> resetPassword(@Body ChangePasswordCodeDTO changePasswordCodeDTO);

    @GET("api/user/{id}/message")
    Call<PaginatedResponse<MessageDTO>> getMessages(@Path("id") String id);

    @POST("api/user/{id}/message")
    Call<MessageDTO> sendMessage(@Path("id") String id, @Body MessageDTO2 message);

}
