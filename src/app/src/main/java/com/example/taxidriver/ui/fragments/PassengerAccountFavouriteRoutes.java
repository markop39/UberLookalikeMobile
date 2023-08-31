package com.example.taxidriver.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.FavouriteRouteResponseDTO;
import com.example.taxidriver.data.repository.RideRepository;
import com.example.taxidriver.data.repository.UserRepository;
import com.example.taxidriver.domain.viewmodel.FavoriteRideViewModel;
import com.example.taxidriver.domain.viewmodel.RideHistoryViewModel;
import com.example.taxidriver.ui.activities.passenger.PassengerMainActivity;
import com.example.taxidriver.ui.adapters.FavouriteRouteAdapter;
import com.example.taxidriver.domain.model.FavoriteRoute;
import com.example.taxidriver.util.Mockup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PassengerAccountFavouriteRoutes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PassengerAccountFavouriteRoutes extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<FavouriteRouteResponseDTO> routes = new ArrayList<>();
    private ListView routesListView;

    RideRepository rideRepository = new RideRepository();
    UserRepository userRepository = new UserRepository();

    private FavoriteRideViewModel viewModel;
    SharedPreferences prefs = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);

    public PassengerAccountFavouriteRoutes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PAssangerAccountFavouriteRides.
     */
    // TODO: Rename and change types and number of parameters
    public static PassengerAccountFavouriteRoutes newInstance(String param1, String param2) {
        PassengerAccountFavouriteRoutes fragment = new PassengerAccountFavouriteRoutes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static PassengerAccountFavouriteRoutes newInstance() {
        return new PassengerAccountFavouriteRoutes();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_passenger_account_favourite_routes, container, false);
//        prepareList();
        routesListView = view.findViewById(R.id.fav_routes_listview);
        FavouriteRouteAdapter adapter = new FavouriteRouteAdapter(getActivity(), routes);
        routesListView.setOnItemClickListener(new RoutesItemsClickListener());

        String id = prefs.getString("userId", null);

        viewModel = new ViewModelProvider(this).get(FavoriteRideViewModel.class);

        viewModel.getFavourites().observe(getActivity(), favouriteRouteResponseDTOList -> {

                    if(favouriteRouteResponseDTOList != null){

                        routes = favouriteRouteResponseDTOList;
                        adapter.items = routes;
                        routesListView.setAdapter(adapter);

                    }

                }
        );
        viewModel.fetchFavoriteRoutes(id);
        routesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Delete the chosen route?");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "Yes",
                        (dialog, id1) -> {
                            removeFavourite(String.valueOf(adapter.items.get(Integer.parseInt(id)).getId()));
                            adapter.removeItem(i);

                            dialog.cancel();
                        });

                builder.setNegativeButton(
                        "No",
                        (dialog, id2) -> dialog.cancel());

                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
        return view;
    }
    private void removeFavourite(String id){
        rideRepository.deleteFavoriteRide(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(TaxiDriver.getAppContext(), "Successfully deleted route", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TaxiDriver.getAppContext(), "Encountered an error please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TaxiDriver.getAppContext(), "Server failure", Toast.LENGTH_SHORT).show();

            }
        }, prefs.getString("userId", null));

    }

    private class RoutesItemsClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setMessage("Order an uber for the chosen route?");
            builder.setCancelable(true);
            builder.setPositiveButton(
                    "Yes",
                    (dialog, id1) -> {
                        Intent intent = new Intent(view.getContext(), PassengerMainActivity.class);
                        intent.putExtra("departure", routes.get(position).getStartingPoint().getAddress());
                        intent.putExtra("destination", routes.get(position).getDestination().getAddress());
                        startActivity(intent);
                        dialog.cancel();
                    });

            builder.setNegativeButton(
                    "No",
                    (dialog, id2) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
        }


    }

    private void prepareList() {
        rideRepository.getPassengerFavorites(new retrofit2.Callback<List<FavouriteRouteResponseDTO>>() {
            @Override
            public void onResponse(Call<List<FavouriteRouteResponseDTO>> call, Response<List<FavouriteRouteResponseDTO>> response) {
                if( response.isSuccessful()){
                    for (FavouriteRouteResponseDTO fr : response.body()) {
                        routes.add(fr);
                    }
                }else{
                    // TODO no favourites currently
                }

            }

            @Override
            public void onFailure(Call<List<FavouriteRouteResponseDTO>> call, Throwable t) {

            }
        }, prefs.getString("userId", null));

    }
}