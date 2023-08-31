package com.example.taxidriver.data.token;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.repository.AuthRepository;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private AuthRepository auth;

    public TokenInterceptor(AuthRepository auth) {
        this.auth = auth;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        SharedPreferences prefs = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        String token = prefs.getString("token", null);

        if (token != null) {
            request = request.newBuilder().addHeader("Authorization", "Bearer " + token).build();
        }
        return chain.proceed(request);
    }
}

