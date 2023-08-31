package com.example.taxidriver.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.util.Tools;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!isLocationEnabled) {
            Toast.makeText(this, "Location services are required for this app to function properly", Toast.LENGTH_LONG).show();
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
            finish();
        } else {

            int SPLASH_TIME_OUT = 1500;

            if (Tools.getConnectivityStatus(getApplicationContext()) == 0) {
                Toast.makeText(TaxiDriver.getAppContext(), "Turn on wifi or mobile data.", Toast.LENGTH_SHORT).show();
                SPLASH_TIME_OUT = 10000;
            }

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if (Tools.getConnectivityStatus(getApplicationContext())  != 0) {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }




    }
}