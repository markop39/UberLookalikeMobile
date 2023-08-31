package com.example.taxidriver.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.CancelDTO;
import com.example.taxidriver.data.dto.ChangePasswordCodeDTO;
import com.example.taxidriver.data.dto.IsInRideDTO;
import com.example.taxidriver.data.dto.ResetPasswordDTO;
import com.example.taxidriver.data.dto.ReviewDTO2;
import com.example.taxidriver.data.repository.ReviewRepository;
import com.example.taxidriver.data.repository.RideRepository;
import com.example.taxidriver.data.repository.UserRepository;
import com.example.taxidriver.domain.viewmodel.CurrentRideViewModel;
import com.example.taxidriver.domain.viewmodel.RideHistoryViewModel;
import com.example.taxidriver.ui.activities.driver.DriverMainActivity;
import com.example.taxidriver.ui.activities.passenger.PassengerMainActivity;
import com.example.taxidriver.ui.fragments.HistoryFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrentRideActivity extends AppCompatActivity {

    MapView mapView;
    private CurrentRideViewModel viewModel;
    private TextView mTimerTextView;
    private int mSeconds = 0;
    SharedPreferences prefs = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
    ReviewRepository reviewRepository = new ReviewRepository();

    Boolean panicSent = false;
    private String rideId;

    RideRepository rideRepository;
    private Handler handler = new Handler();
    private Runnable vehicleLocationRunnable = new Runnable() {
        @Override
        public void run() {
            viewModel.fetchCurrentLocationVehicle();
            handler.postDelayed(vehicleLocationRunnable, 10000);
        }
    };

    UserRepository userRepository = new UserRepository();
    private Runnable isInRideRunnable = new Runnable() {
        @Override
        public void run() {
            userRepository.isUserInRide(new retrofit2.Callback<IsInRideDTO>() {
                @Override
                public void onResponse(retrofit2.Call<IsInRideDTO> call, retrofit2.Response<IsInRideDTO> response) {
                    if (response.isSuccessful()) {
                        IsInRideDTO isInRideDTO = response.body();
                        assert isInRideDTO != null;
                        if (!isInRideDTO.getInRide()) {

                            handler.removeCallbacks(isInRideRunnable);

                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(CurrentRideActivity.this);
                            LayoutInflater inflater = CurrentRideActivity.this.getLayoutInflater();
                            View dialogView = inflater.inflate(R.layout.submit_review_dialog, null);
                            builder.setView(dialogView);
                            builder.setBackground(getResources().getDrawable(R.drawable.rounded_dialog));
                            final AlertDialog forgotPasswordDialog = builder.create();

                            final EditText driverCommentEditText = dialogView.findViewById(R.id.driver_comment);
                            final EditText vehicleCommentEditText = dialogView.findViewById(R.id.vehicle_comment);

                            RatingBar driverRatingBar = dialogView.findViewById(R.id.rating_bar_driver);
                            RatingBar vehicleRatingBar = dialogView.findViewById(R.id.rating_bar_vehicle);

                            Button sendButton = dialogView.findViewById(R.id.submit_review_button);

                            sendButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String driverComment = driverCommentEditText.getText().toString();
                                    String vehicleComment = vehicleCommentEditText.getText().toString();
                                    int ratingDriver = Math.round(driverRatingBar.getRating());
                                    int ratingVehicle = Math.round(vehicleRatingBar.getRating());
                                    if (!TextUtils.isEmpty(driverComment) || !TextUtils.isEmpty(vehicleComment)) {
                                        reviewRepository.postReviewDVehicle(new ReviewDTO2(ratingVehicle, vehicleComment), rideId);
                                        reviewRepository.postReviewDriver(new ReviewDTO2(ratingDriver, driverComment), rideId);
                                        startActivity(new Intent(CurrentRideActivity.this, PassengerMainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(TaxiDriver.getAppContext(), "Write something...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            forgotPasswordDialog.show();
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<IsInRideDTO> call, Throwable t) {
                    Toast.makeText(TaxiDriver.getAppContext(), "Is in ride faliure.", Toast.LENGTH_SHORT).show();
                }
            });
            handler.postDelayed(isInRideRunnable, 10000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_current_ride);

        rideId = getIntent().getStringExtra("rideId");
        String startLatitude = getIntent().getStringExtra("startLat");
        String startLongitude = getIntent().getStringExtra("startLon");
        String endLatitude = getIntent().getStringExtra("endLat");
        String endLongitude = getIntent().getStringExtra("endLon");

        rideRepository = new RideRepository();

        mapView = findViewById(R.id.map_view);

        GeoPoint startPoint = new GeoPoint(Double.parseDouble(startLatitude), Double.parseDouble(startLongitude));



        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(15);
        mapView.getController().setCenter(startPoint);


        Marker driverMarker = new Marker(mapView);
        driverMarker.setPosition(startPoint);
        driverMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        driverMarker.setFlat(true);
        driverMarker.setTitle("Driver");
        driverMarker.setSubDescription("Standard");
        mapView.getOverlays().add(driverMarker);


        String apiKey = "5b3ce3597851110001cf6248ef20a8142c654e9dba129cbcb06678f1";

        String baseUrl = "https://api.openrouteservice.org/v2/directions/driving-car";
        String requestUrl = baseUrl + "?api_key=" + apiKey + "&start=" + startLongitude + "," + startLatitude + "&end=" + endLongitude + "," + endLatitude;

// Use a HTTP client library such as OkHttp to make the API request
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(requestUrl).build();


        FragmentManager fragmentManager = getSupportFragmentManager();

        viewModel = new ViewModelProvider(this).get(CurrentRideViewModel.class);


        viewModel.getVehicle().observe(this, locationDTO3 -> {

                    if(locationDTO3 != null)
                    {
                        GeoPoint newLocation = new GeoPoint(locationDTO3.getLatitude(), locationDTO3.getLongitude());
                        driverMarker.setPosition(newLocation);
                        mapView.invalidate();
                    }
                }
        );

        viewModel.fetchCurrentLocationVehicle();
        handler.postDelayed(vehicleLocationRunnable, 5000);




        client.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                                            }

                                            @Override
                                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                                String responseBody = response.body().string();
                                                JSONObject jsonResponse = null;
                                                JSONArray coordinates = null;
                                                JSONObject coordinates1 = null;
                                                try {
                                                    jsonResponse = new JSONObject(responseBody);
                                                    JSONArray routes = jsonResponse.getJSONArray("features");
                                                    JSONObject route = routes.getJSONObject(0);
                                                    coordinates1 = route.getJSONObject("geometry");
                                                    coordinates = coordinates1.getJSONArray("coordinates");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                                List<GeoPoint> routePoints = new ArrayList<>();
                                                for (int i = 0; i < coordinates.length(); i++) {
                                                    JSONArray point = null;
                                                    double latitude = 0;
                                                    double longitude = 0;
                                                    try {
                                                        point = coordinates.getJSONArray(i);

                                                        latitude = point.getDouble(1);
                                                        longitude = point.getDouble(0);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    routePoints.add(new GeoPoint(latitude, longitude));
                                                }

                                                // Draw the route on the map
                                                Polyline roadOverlay = new Polyline();
                                                roadOverlay.setPoints(routePoints);
                                                roadOverlay.setColor(Color.BLACK);
                                                roadOverlay.setWidth(8f);
                                                mapView.getOverlays().add(roadOverlay);
                                                mapView.invalidate();
                                            }
                                        });



        Button finishButton = findViewById(R.id.finish_ride);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CurrentRideActivity.this, DriverMainActivity.class);
                startActivity(intent);
                rideRepository.finishRide(new retrofit2.Callback<Void>() {
                    @Override
                    public void onResponse(retrofit2.Call<Void> call, retrofit2.Response<Void> response) {}
                    @Override
                    public void onFailure(retrofit2.Call<Void> call, Throwable t) {}
                }, rideId);
                finish();
            }
        });

        Button cancelButton = findViewById(R.id.cancle_ride);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(CurrentRideActivity.this);
                LayoutInflater inflater = CurrentRideActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.forgot_password_dialog, null);
                builder.setView(dialogView);
                builder.setBackground(getResources().getDrawable(R.drawable.rounded_dialog));
                final AlertDialog forgotPasswordDialog = builder.create();

                final EditText emailEditText = dialogView.findViewById(R.id.email_edit_text);
                Button sendButton = dialogView.findViewById(R.id.send_button);
                TextView enterText = dialogView.findViewById(R.id.enter_text);
                emailEditText.setHint("They were drunk...");
                enterText.setText("Enter your reason: ");

                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = emailEditText.getText().toString();
                        if (!TextUtils.isEmpty(email)) {
                            CancelDTO cancelDTO = new CancelDTO(email);
                            rideRepository.cancelRide(new retrofit2.Callback<Void>() {
                                @Override
                                public void onResponse(retrofit2.Call<Void> call, retrofit2.Response<Void> response) {
                                    Toast.makeText(TaxiDriver.getAppContext(), "Cancel succesfully sent..", Toast.LENGTH_SHORT).show();

                                }
                                @Override
                                public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                                    Toast.makeText(TaxiDriver.getAppContext(), "Cancel NOT succesfully sent..", Toast.LENGTH_SHORT).show();

                                }
                            }, rideId, cancelDTO);
                            forgotPasswordDialog.dismiss();
                            Intent intent = new Intent(CurrentRideActivity.this, DriverMainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(TaxiDriver.getAppContext(), "Reason field is empty.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                forgotPasswordDialog.show();
            }
        });

        Button panicButton = findViewById(R.id.panic_driver);

        panicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (panicSent)
                {
                    Toast.makeText(TaxiDriver.getAppContext(), "Help sent, stay calm.", Toast.LENGTH_SHORT).show();
                    return;
                }


                rideRepository.panicRide(new retrofit2.Callback<Void>() {
                    @Override
                    public void onResponse(retrofit2.Call<Void> call, retrofit2.Response<Void> response) {

                        Toast.makeText(TaxiDriver.getAppContext(), "We are sending help.", Toast.LENGTH_SHORT).show();
                        panicSent = true;
                    }
                    @Override
                    public void onFailure(retrofit2.Call<Void> call, Throwable t) {}
                }, rideId);
            }
        });


        mTimerTextView = findViewById(R.id.timer_text_view);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = mSeconds/60;
                int seconde = mSeconds%60;
                String strMinute = "";
                String strSeconde = "";

                if(minutes< 10)
                    strMinute += "0";

                if(seconde< 10)
                    strSeconde += "0";

                strMinute += minutes;
                strSeconde += seconde;
                mTimerTextView.setText("Time: " + strMinute + ":" + strSeconde);
                mSeconds++;
                handler.postDelayed(this, 1000);
            }
        });


        if(prefs.getString("role", "").equals("ROLE_PASSENGER"))
        {
            ViewGroup cancelParent = (ViewGroup) cancelButton.getParent();
            cancelParent.removeView(cancelButton);

            ViewGroup finishParent = (ViewGroup) finishButton.getParent();
            finishParent.removeView(finishButton);

            handler.postDelayed(isInRideRunnable, 10000);

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(vehicleLocationRunnable);
        handler.removeCallbacks(isInRideRunnable);
        // handler.removeCallbacks(scheduledRidesRunnable);
    }

}