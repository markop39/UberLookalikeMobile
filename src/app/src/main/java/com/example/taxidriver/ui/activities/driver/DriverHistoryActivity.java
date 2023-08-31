package com.example.taxidriver.ui.activities.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.domain.model.Ride;
import com.example.taxidriver.domain.viewmodel.RideHistoryViewModel;
import com.example.taxidriver.ui.fragments.HistoryFragment;
import com.example.taxidriver.util.Mockup;

import java.util.ArrayList;
import java.util.List;

public class DriverHistoryActivity extends AppCompatActivity {

    private RideHistoryViewModel viewModel;
    SharedPreferences prefs = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_history);


        String id = prefs.getString("userId", null);


        FragmentManager fragmentManager = getSupportFragmentManager();

        viewModel = new ViewModelProvider(this).get(RideHistoryViewModel.class);


        viewModel.getRideHistory().observe(this, historyList -> {

            if(historyList != null)
                fragmentManager.beginTransaction().add(R.id.mainContent, HistoryFragment.newInstance(historyList)).commit();

            }
        );
        viewModel.fetchRideHistory(id);


        ImageView home = findViewById(R.id.home);
        ImageView inbox = findViewById(R.id.inbox);
        ImageView profile = findViewById(R.id.profile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverHistoryActivity.this, DriverMainActivity.class));
                finish();
            }
        });


        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverHistoryActivity.this, DriverInboxActivity.class));
                finish();

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverHistoryActivity.this, DriverAccountActivity.class));
                finish();

            }
        });

    }
}