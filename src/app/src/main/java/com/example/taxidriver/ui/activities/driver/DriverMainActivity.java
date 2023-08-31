package com.example.taxidriver.ui.activities.driver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.ActiveVehicleDTO;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.data.repository.RideRepository;
import com.example.taxidriver.domain.model.Drive;
import com.example.taxidriver.domain.viewmodel.DriverMainViewModel;
import com.example.taxidriver.domain.viewmodel.RideHistoryViewModel;
import com.example.taxidriver.ui.fragments.ScheduledFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverMainActivity extends AppCompatActivity {

    private MapView mapView;
    DriverMainViewModel driverMainViewModel;
    SharedPreferences prefs = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
    AlertDialog acceptRideDialog;
    RideRepository rideRepository;
    DriverRepository driverRepository = new DriverRepository();



    private Handler handler = new Handler();
    private Runnable activeVehicleRunnable = new Runnable() {
        @Override
        public void run() {
            driverMainViewModel.fetchActiveVehiclesLocation();
            handler.postDelayed(activeVehicleRunnable, 60000);
        }
    };

    private Runnable offeringRideRunnable = new Runnable() {
        @Override
        public void run() {
            driverRepository.isTherePendingRide(new Callback<RideDTO>() {
                @Override
                public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {

                    if(response.isSuccessful())
                    {
                        RideDTO rideDTO = response.body();
                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(DriverMainActivity.this);
                        LayoutInflater inflater = DriverMainActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.accept_ride_dialog, null);
                        Button acceptRideButton = dialogView.findViewById(R.id.accept_ride);


                        acceptRideButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                rideRepository.acceptRide(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        if(response.isSuccessful())
                                        {
                                            Toast.makeText(TaxiDriver.getAppContext(), "You accepted ride.", Toast.LENGTH_SHORT).show();
                                            driverMainViewModel.fetchScheduledRides(prefs.getString("userId", "1"));

                                        }
                                        else
                                        {
                                            Toast.makeText(TaxiDriver.getAppContext(), "Something went wrong, you did not accept ride.", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(TaxiDriver.getAppContext(), "Something went wrong, you did not accept ride.", Toast.LENGTH_SHORT).show();

                                    }
                                },rideDTO.getId().toString());
                                acceptRideDialog.dismiss();

                            }
                        });
                        ProgressBar progressBar = dialogView.findViewById(R.id.progress_bar);
                        EditText destinationEditText = dialogView.findViewById(R.id.destination2);
                        EditText departureEditText = dialogView.findViewById(R.id.departure2);
                        TextView timeTextView = dialogView.findViewById(R.id.time2);
                        TextView priceTextView = dialogView.findViewById(R.id.price2);
                        CheckBox petCheckBox = dialogView.findViewById(R.id.pet2);
                        CheckBox kidCheckBox = dialogView.findViewById(R.id.kid2);
                        String departure = rideDTO.getLocations().getDeparture().getAddress();
                        String destination = rideDTO.getLocations().getDestination().getAddress();
                        LocalDateTime startTime = LocalDateTime.parse(rideDTO.getStartTime());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        timeTextView.setText("Time: "+startTime.format(formatter));
                        priceTextView.setText("Price: " + rideDTO.getTotalCost().toString()+ " $");
                        departureEditText.setText(departure);
                        destinationEditText.setText(destination);
                        petCheckBox.setChecked(rideDTO.isPetTransport());
                        kidCheckBox.setChecked(rideDTO.isBabyTransport());

                        builder.setView(dialogView);
                        builder.setBackground(getResources().getDrawable(R.drawable.rounded_dialog));
                        acceptRideDialog = builder.create();
                        acceptRideDialog.show();

                        final int totalProgressTime = 11;
                        final Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int jumpTime = 0;

                                while(jumpTime < totalProgressTime) {
                                    try {
                                        Thread.sleep(1000);
                                        jumpTime += 1;
                                        progressBar.setProgress(jumpTime);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                acceptRideDialog.dismiss();

                            }
                        });
                        thread.start();


                    }
                    else
                    {}

                }

                @Override
                public void onFailure(Call<RideDTO> call, Throwable t) {
                    // handle error
                }
            }, prefs.getString("userId", "1"));
            handler.postDelayed(offeringRideRunnable, 10000);
        }
    };

/*
    private Runnable scheduledRidesRunnable = new Runnable() {
        @Override
        public void run() {
            driverMainViewModel.fetchScheduledRides(prefs.getString("userId", "1"));
            handler.postDelayed(scheduledRidesRunnable, 10000);
        }
    };

 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        rideRepository = new RideRepository();

        driverMainViewModel = new DriverMainViewModel();

        setContentView(R.layout.activity_driver_main);

        mapView = findViewById(R.id.map_view);

        String id = prefs.getString("userId", null);


        FragmentManager fragmentManager = getSupportFragmentManager();

        handler.postDelayed(activeVehicleRunnable, 60000);


        driverMainViewModel.getScheduledRides().observe(this, list -> {

                    if(list != null)
                        fragmentManager.beginTransaction().add(R.id.mainContent2, ScheduledFragment.newInstance(list, DriverMainActivity.this)).commit();

                }
        );
        driverMainViewModel.fetchScheduledRides(id);
       // handler.postDelayed(scheduledRidesRunnable, 5000);



        mapView.setTileSource(TileSourceFactory.MAPNIK);

        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(15);
        mapView.getController().setCenter(new GeoPoint(45.2396, 19.8227));



        // acceptRideDialog.show();



        driverMainViewModel.getAllActiveVehicles().observe(this, list -> {

                    if (list != null) {
                        for (ActiveVehicleDTO activeVehicleDTO : list) {
                            GeoPoint driverLocation = new GeoPoint(activeVehicleDTO.getLatitude(), activeVehicleDTO.getLongitude());
                            Marker driverMarker = new Marker(mapView);
                            driverMarker.setPosition(driverLocation);
                            driverMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                            driverMarker.setFlat(true);

                            if(activeVehicleDTO.isInRide())
                                driverMarker.setTitle("TAKEN");
                            else
                                driverMarker.setTitle("FREE");

                            mapView.getOverlays().add(driverMarker);
                            mapView.invalidate();
                        }
                    }

                }
        );


        driverMainViewModel.fetchActiveVehiclesLocation();

        handler.post(offeringRideRunnable);



        Switch switchButton = (Switch) findViewById(R.id.switch1);

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (switchButton.isChecked()) {
                    driverRepository.putDriverUnactive();
                } else {
                    driverRepository.putDriverUnactive();

                }

            }
        });



        ImageView home = findViewById(R.id.home);
        ImageView history = findViewById(R.id.history);
        ImageView inbox = findViewById(R.id.inbox);
        ImageView profile = findViewById(R.id.profile);

//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DriverMainActivity.this, DriverMainActivity.class));
//            }
//        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverMainActivity.this, DriverHistoryActivity.class));
                finish();

            }
        });
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverMainActivity.this, DriverInboxActivity.class));
                finish();

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverMainActivity.this, DriverAccountActivity.class));
                finish();

            }
        });




    }


    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(offeringRideRunnable);
       // handler.removeCallbacks(scheduledRidesRunnable);
    }
}