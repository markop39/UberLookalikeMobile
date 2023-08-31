package com.example.taxidriver.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.api.AuthApi;
import com.example.taxidriver.data.dto.PassengerDTO;
import com.example.taxidriver.domain.model.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthRepository {
    private final AuthApi authApi;
    SharedPreferences pref = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);


    public AuthRepository() {
        String BASE_URL = "http://10.0.2.2:8080/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authApi = retrofit.create(AuthApi.class);
    }

    public void login(String email, String password, Callback<JsonObject> callback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);

        Call<JsonObject> call = authApi.login(jsonObject);
        call.enqueue(callback);
    }

    public void signup(PassengerDTO passengerDTO, Callback<JsonObject> callback) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email",passengerDTO.getEmail());
        jsonObject.addProperty("firstname",passengerDTO.getName());
        jsonObject.addProperty("lastname", passengerDTO.getSurname());
        jsonObject.addProperty("address",passengerDTO.getAddress());
        jsonObject.addProperty("password", passengerDTO.getPassword());
        jsonObject.addProperty("telephoneNumber",passengerDTO.getTelephoneNumber());
        jsonObject.addProperty("profilePicture",passengerDTO.getProfilePicture());
        Call<JsonObject> call = authApi.signup(jsonObject);

        call.enqueue(callback);
    }

    public void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", null);
        editor.apply();
    }




    public interface AuthCallback {
        void onSuccess();
        void onFailure(String message);
    }
}

