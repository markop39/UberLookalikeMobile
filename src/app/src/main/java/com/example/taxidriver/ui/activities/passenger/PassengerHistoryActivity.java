package com.example.taxidriver.ui.activities.passenger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.taxidriver.ui.fragments.HistoryFragment;
import com.example.taxidriver.ui.fragments.PassengerAccountFavouriteRoutes;
import com.example.taxidriver.ui.fragments.PassengerAccountProfile;
import com.example.taxidriver.ui.fragments.PassengerAccountReports;
import com.example.taxidriver.util.FragmentTransition;

public class PassengerHistoryActivity extends AppCompatActivity {
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
        viewModel.fetchPassengerRideHistory(id);



        ImageView home = findViewById(R.id.home);
        ImageView history = findViewById(R.id.history);
        ImageView inbox = findViewById(R.id.inbox);
        ImageView profileT = findViewById(R.id.profile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerHistoryActivity.this, PassengerMainActivity.class));
                finish();

            }
        });
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerHistoryActivity.this, PassengerInboxActivity2.class));
                finish();

            }
        });
        profileT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerHistoryActivity.this, PassengerAccountActivity.class));
            }
        });

    }

}
