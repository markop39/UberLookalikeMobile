package com.example.taxidriver.data.repository;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.RetrofitClient;
import com.example.taxidriver.data.api.UserApi;
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
import retrofit2.Callback;
import retrofit2.Response;


public class UserRepository {
    final private UserApi userApi;

    public UserRepository() {
        userApi = RetrofitClient.getInstance().create(UserApi.class);
    }

    public void getMessages(Callback<PaginatedResponse<MessageDTO>> callback, String id){
        userApi.getMessages(id).enqueue(callback);
    }
    public  void sendMessage(Callback<MessageDTO> callback, String id, MessageDTO2 request){
        userApi.sendMessage(id, request).enqueue(callback);
    }
    public void getUsers(Callback<PaginatedResponse<User>> callback) {
        userApi.getUsers().enqueue(callback);
    }
    public void changePassword(String id, ChangePasswordDTO changePasswordDTO){
        userApi.changePassword(id, changePasswordDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(TaxiDriver.getAppContext(), "Password successfully changed!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TaxiDriver.getAppContext(), "Incorrect password!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), "Server failure.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCurrentLocation(Callback<LocationDTO3> callback) {
        userApi.getCurrentLocation().enqueue(callback);
    }

    public void isUserInRide(Callback<IsInRideDTO> callback) {
        userApi.isUserInRide().enqueue(callback);
    }


    public void sendCode(ResetPasswordDTO resetPasswordDTO) {
        userApi.sendCode(resetPasswordDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Code successfully sent to email.", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(TaxiDriver.getAppContext(), "Code is not sent.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), "Code is not sent.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void resetPassword(ChangePasswordCodeDTO changePasswordCodeDTO) {
        userApi.resetPassword(changePasswordCodeDTO).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Password successfully changed.", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(TaxiDriver.getAppContext(), "Password is not changed.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), "Password is not changed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

