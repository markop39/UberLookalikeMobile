package com.example.taxidriver.data;

import com.example.taxidriver.data.repository.AuthRepository;
import com.example.taxidriver.data.token.TokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private static Retrofit retrofit = null;


        public static Retrofit getInstance() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(new OkHttpClient.Builder()
                                .addInterceptor(new TokenInterceptor(new AuthRepository()))
                                .build())
                        .build();
            }
            return retrofit;
        }



}
