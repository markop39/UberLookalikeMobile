package com.example.taxidriver.ui.activities.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.api.RideApi;
import com.example.taxidriver.data.dto.ActiveVehicleDTO;
import com.example.taxidriver.data.dto.EstimationDTO;
import com.example.taxidriver.data.dto.EstimationRequestDTO2;
import com.example.taxidriver.data.dto.IsInRideDTO;
import com.example.taxidriver.data.dto.LocationDTO;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.ResetPasswordDTO;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.dto.RideRequestDTO;
import com.example.taxidriver.data.repository.RideRepository;
import com.example.taxidriver.data.repository.UnregisteredUserRepository;
import com.example.taxidriver.data.repository.UserRepository;
import com.example.taxidriver.domain.model.Ride;
import com.example.taxidriver.domain.viewmodel.PassengerMainViewModel;
import com.example.taxidriver.domain.viewmodel.RideHistoryViewModel;
import com.example.taxidriver.ui.activities.CurrentRideActivity;
import com.example.taxidriver.ui.activities.LoginActivity;
import com.example.taxidriver.ui.activities.driver.DriverMainActivity;
import com.example.taxidriver.ui.fragments.HistoryFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.JsonObject;

import org.mapsforge.core.model.Point;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class PassengerMainActivity extends AppCompatActivity {


    private MapView mapView;
    private EditText destinationEditText;
    private EditText departureEditText;
    private CheckBox petCheckBox;
    private CheckBox kidCheckBox;
    private Spinner vehicleTypeSpinner;
    private Button submitRideRequestButton;
    private UnregisteredUserRepository unregisteredUserRepository;
    private RideRepository rideRepository = new RideRepository();
    private UserRepository userRepository = new UserRepository();
    private PassengerMainViewModel viewModel;
    private Handler handler = new Handler();
    private Runnable activeVehicleRunnable = new Runnable() {
        @Override
        public void run() {
            viewModel.fetchActiveVehiclesLocation();
            handler.postDelayed(activeVehicleRunnable, 60000);
        }
    };
    private Runnable isInRideRunnable = new Runnable() {
        @Override
        public void run() {
            userRepository.isUserInRide(new Callback<IsInRideDTO>() {
                @Override
                public void onResponse(Call<IsInRideDTO> call, Response<IsInRideDTO> response) {
                    if(response.isSuccessful())
                    {
                        IsInRideDTO isInRideDTO = response.body();
                        assert isInRideDTO != null;
                        if(isInRideDTO.getInRide())
                        {
                            RideDTO rideDTO = isInRideDTO.getRideDTO();
                            Intent intent = new Intent(PassengerMainActivity.this, CurrentRideActivity.class);
                            intent.putExtra("rideId", rideDTO.getId().toString());
                            intent.putExtra("startLat", rideDTO.getLocations().getDeparture().getLatitude().toString());
                            intent.putExtra("startLon", rideDTO.getLocations().getDeparture().getLongitude().toString());
                            intent.putExtra("endLat", rideDTO.getLocations().getDestination().getLatitude().toString());
                            intent.putExtra("endLon", rideDTO.getLocations().getDestination().getLongitude().toString());
                            startActivity(intent);
                            finish();
                        }
                    }
                    else
                    {
                        Toast.makeText(TaxiDriver.getAppContext(), "IsInRide response body wrong.", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<IsInRideDTO> call, Throwable t) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Is in ride faliure.", Toast.LENGTH_SHORT).show();
                }
            });
            handler.postDelayed(isInRideRunnable, 10000);
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_main);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));


        viewModel = new ViewModelProvider(this).get(PassengerMainViewModel.class);

        unregisteredUserRepository = new UnregisteredUserRepository();

        handler.postDelayed(activeVehicleRunnable, 60000);

        // Find the elements in the layout by their ID
        destinationEditText = findViewById(R.id.destination);
        departureEditText = findViewById(R.id.departure);
        TimePicker timePicker = findViewById(R.id.time_picker);
        destinationEditText = findViewById(R.id.destination);
        departureEditText = findViewById(R.id.departure);
        if (getIntent().getExtras() != null){
            departureEditText.setText(getIntent().getStringExtra("departure"));
            destinationEditText.setText(getIntent().getStringExtra("destination"));
        }
        petCheckBox = findViewById(R.id.pet);
        kidCheckBox = findViewById(R.id.kid);
        vehicleTypeSpinner = findViewById(R.id.vehicle_type);
        submitRideRequestButton = findViewById(R.id.submit_ride_request);
        mapView = findViewById(R.id.map_view);

        handler.postDelayed(isInRideRunnable, 10000);

        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(15);
        mapView.getController().setCenter(new GeoPoint(45.2396, 19.8227));

        MaterialAlertDialogBuilder builder1 = new MaterialAlertDialogBuilder(PassengerMainActivity.this);
        LayoutInflater inflater = PassengerMainActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.wait_driver_accept_ride_dialog, null);
        builder1.setView(dialogView);
        builder1.setBackground(getResources().getDrawable(R.drawable.rounded_dialog));
        final AlertDialog waitDriverDialog = builder1.create();




        submitRideRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer hour = timePicker.getHour();
                Integer minute = timePicker.getMinute();
                String departure  = departureEditText.getText().toString();
                String destination = destinationEditText.getText().toString();
                boolean isKid = kidCheckBox.isChecked();
                boolean isPet = petCheckBox.isChecked();
                String vehicleType = (String) vehicleTypeSpinner.getSelectedItem();


                Integer currentHour = LocalDateTime.now().getHour();
                Integer currentMinute = LocalDateTime.now().getMinute();

                if (TextUtils.isEmpty(departure)) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Departure field must not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(destination)) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Destination field must not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(departure)) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Departure field must not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (destination.length() > 100) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Destination length too big.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (departure.length() > 100) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Departure length too big.", Toast.LENGTH_SHORT).show();
                    return;
                }

                departure = departure.substring(0, 1).toUpperCase() + departure.substring(1);

                departureEditText.setText(departure);


                destination = destination.substring(0, 1).toUpperCase() + destination.substring(1);

                destinationEditText.setText(destination);

                String regex = "^[A-Z][a-zA-Z ]+[0-9]{1,4}$";

                if (!destination.matches(regex)) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Destination address must end with number.", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (!departure.matches(regex)) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Departure address must end with number.", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (hour < currentHour || (hour == LocalDateTime.now().getHour() && minute < currentMinute))
                {
                    Toast.makeText(TaxiDriver.getAppContext(), "Time must be in future.", Toast.LENGTH_SHORT).show();
                    return;
                }
                EstimationRequestDTO2  estimationRequest = new EstimationRequestDTO2(departure,destination,isKid, isPet, vehicleType);

                unregisteredUserRepository.getEstimation(new Callback<EstimationDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<EstimationDTO> call, @NonNull Response<EstimationDTO> response) {
                        if (response.isSuccessful()) {

                            assert response.body() != null;

                            EstimationDTO estimation = response.body();
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(PassengerMainActivity.this);
                            LayoutInflater inflater = PassengerMainActivity.this.getLayoutInflater();
                            View dialogView = inflater.inflate(R.layout.estimation_accept, null);
                            builder.setView(dialogView);
                            builder.setBackground(getResources().getDrawable(R.drawable.rounded_dialog));
                            final AlertDialog estimationDialog = builder.create();
                            TextView estimatedTime = dialogView.findViewById(R.id.estimated_time);
                            TextView estimatedCost = dialogView.findViewById(R.id.estimated_cost);

                            estimatedTime.setText("Estimated time: " + estimation.getEstimatedTimeInMinutes() + " minutes");
                            estimatedCost.setText("Estimated cost: " + estimation.getEstimatedCost() +  " $");
                            Button sendButton = dialogView.findViewById(R.id.accept_estimation_button);

                            sendButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    waitDriverDialog.show();
                                    estimationDialog.dismiss();
                                    LocalDateTime scheduleTime = LocalDateTime.now().withHour(hour).withMinute(minute);
                                    String scheduleTimeString = scheduleTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                                    LocationDTO location = new LocationDTO(estimation.getDeparture(), estimation.getDestionation());
                                    RideRequestDTO rideRequest = new RideRequestDTO(vehicleType.toUpperCase(Locale.ROOT), isKid, isPet, location, scheduleTimeString);
                                    rideRequest.setEstimationTime(estimation.getEstimatedTimeInMinutes());
                                    rideRequest.setEstimationPrice(estimation.getEstimatedCost());
                                    rideRepository.submitRideRequest(new Callback<RideDTO>() {

                                        @Override
                                        public void onResponse(Call<RideDTO> call, Response<RideDTO> response) {
                                            if (response.isSuccessful()) {
                                                RideDTO rideDTO = response.body();
                                                assert rideDTO != null;
                                                Toast.makeText(TaxiDriver.getAppContext(), "Submitted ride with id: " + rideDTO.getId().toString(), Toast.LENGTH_SHORT).show();
                                                waitDriverDialog.dismiss();

                                            } else {
                                                Toast.makeText(TaxiDriver.getAppContext(), "No driver available, please try again later.", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<RideDTO> call, Throwable t) {
                                            Toast.makeText(TaxiDriver.getAppContext(), "No driver available, please try again later.", Toast.LENGTH_SHORT).show();
                                        }
                                    }, rideRequest);


                        /*
                        String email = emailEditText.getText().toString();
                        if (!TextUtils.isEmpty(email)) {
                            userRepository.sendCode(new ResetPasswordDTO(email));
                            showResetPasswordDialog(email);
                            forgotPasswordDialog.dismiss();
                        } else {
                            Toast.makeText(TaxiDriver.getAppContext(), "Email field is empty.", Toast.LENGTH_SHORT).show();
                        }
                         */
                                }
                            });

                            estimationDialog.show();

                        } else {
                            Toast.makeText(TaxiDriver.getAppContext(), "Address does not exist in Novi Sad.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<EstimationDTO> call, Throwable t) {
                        Toast.makeText(TaxiDriver.getAppContext(), "Address does not exist in Novi Sad.", Toast.LENGTH_SHORT).show();
                    }
                }, estimationRequest);



            }
        });

        viewModel.getAllActiveVehicles().observe(this, list -> {

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


        viewModel.fetchActiveVehiclesLocation();


        ImageView history = findViewById(R.id.history);
        ImageView inbox = findViewById(R.id.inbox);
        ImageView profile = findViewById(R.id.profile);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerMainActivity.this, PassengerHistoryActivity.class));
                finish();

            }
        });
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerMainActivity.this, PassengerInboxActivity2.class));
                finish();

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerMainActivity.this, PassengerAccountActivity.class));
                finish();

            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(isInRideRunnable);
        handler.removeCallbacks(activeVehicleRunnable);
        // handler.removeCallbacks(scheduledRidesRunnable);
    }
}

