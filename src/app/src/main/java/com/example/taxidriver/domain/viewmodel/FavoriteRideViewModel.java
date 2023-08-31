package com.example.taxidriver.domain.viewmodel;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.FavouriteRouteResponseDTO;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.data.repository.RideRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteRideViewModel extends ViewModel {
    private final RideRepository rideRepository;

    private MutableLiveData<List<FavouriteRouteResponseDTO>> favoriteRides;

    public FavoriteRideViewModel() {
        rideRepository = new RideRepository();
        favoriteRides = new MutableLiveData<>();
    }
    public LiveData<List<FavouriteRouteResponseDTO>> getFavourites() {
        return favoriteRides;
    }

    public void fetchFavoriteRoutes(String id) {
        rideRepository.getPassengerFavorites(new Callback<List<FavouriteRouteResponseDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<FavouriteRouteResponseDTO>> call, @NonNull Response<List<FavouriteRouteResponseDTO>> response) {
                if (response.isSuccessful()) {
                    List<FavouriteRouteResponseDTO> favouriteRouteResponseDTOList = response.body();
                    assert favouriteRouteResponseDTOList != null;
//                    Toast.makeText(TaxiDriver.getAppContext(), "Favorite routes, success", Toast.LENGTH_SHORT).show();

                    favoriteRides.postValue(favouriteRouteResponseDTOList);


                } else {
                    Toast.makeText(TaxiDriver.getAppContext(), "No favourite rides", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FavouriteRouteResponseDTO>> call, Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), "Favorite route failure.", Toast.LENGTH_SHORT).show();
            }
        }, id);
    }
}
