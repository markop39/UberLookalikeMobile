package com.example.taxidriver.ui.activities.driver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.domain.viewmodel.RideHistoryViewModel;
import com.example.taxidriver.ui.activities.passenger.PassengerAccountActivity;
import com.example.taxidriver.ui.activities.passenger.PassengerHistoryActivity;
import com.example.taxidriver.ui.activities.passenger.PassengerMainActivity;
import com.example.taxidriver.ui.fragments.DriverAccountProfile;
import com.example.taxidriver.ui.fragments.DriverAccountReports;
import com.example.taxidriver.ui.fragments.HistoryFragment;
import com.example.taxidriver.ui.fragments.PassengerAccountFavouriteRoutes;
import com.example.taxidriver.ui.fragments.PassengerAccountProfile;
import com.example.taxidriver.ui.fragments.PassengerAccountReports;
import com.example.taxidriver.util.FragmentTransition;

public class DriverAccountActivity extends AppCompatActivity {

    private String currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account);
        FragmentTransition.to(DriverAccountProfile.newInstance(), this, false, R.id.mainContent);
        currentTab = "profile";
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayout accountToolbar = findViewById(R.id.driver_toolbar);
        TextView profile = findViewById(R.id.profile_toolbar);
        profile.setBackgroundColor(getResources().getColor(R.color.gray));
        TextView reports = findViewById(R.id.reports_toolbar);

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentTab.equals("reports")){
                    FragmentTransition.to(DriverAccountReports.newInstance(), DriverAccountActivity.this, false, R.id.mainContent);
                    resetToolbar();
                    reports.setBackgroundColor(getResources().getColor(R.color.gray));
                    currentTab="reports";
                }

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentTab.equals("profile")){
                    FragmentTransition.to(DriverAccountProfile.newInstance(), DriverAccountActivity.this, false, R.id.mainContent);
                    resetToolbar();
                    profile.setBackgroundColor(getResources().getColor(R.color.gray));
                    currentTab="profile";
                }

            }
        });
        ImageView home = findViewById(R.id.home);
        ImageView history = findViewById(R.id.history);
        ImageView inbox = findViewById(R.id.inbox);
        ImageView profileT = findViewById(R.id.profile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverAccountActivity.this, DriverMainActivity.class));
                finish();

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverAccountActivity.this, DriverHistoryActivity.class));
                finish();
            }
        });
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverAccountActivity.this, DriverInboxActivity.class));
                finish();

            }
        });
//        profileT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(PassengerAccountActivity.this, PassengerAccountActivity.class));
//            }
//        });

    }

    private void resetToolbar() {
        TextView profile = findViewById(R.id.profile_toolbar);
        profile.setBackgroundColor(Color.WHITE);
        TextView reports = findViewById(R.id.reports_toolbar);
        reports.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}