package com.example.taxidriver.ui.activities.driver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.domain.viewmodel.DriverInboxViewModel;
import com.example.taxidriver.ui.fragments.DriverInboxFragment;

public class DriverInboxActivity extends AppCompatActivity {
    private DriverInboxViewModel viewModel;
    SharedPreferences prefs = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_inbox);
        String id = prefs.getString("userId", null);
        FragmentManager fragmentManager = getSupportFragmentManager();

        viewModel = new ViewModelProvider(this).get(DriverInboxViewModel.class);
        SwitchCompat ride = findViewById(R.id.rideSwitch);
        SwitchCompat panic = findViewById(R.id.panicSwitch);
        SwitchCompat support = findViewById(R.id.supportSwitch);
        Button load = findViewById(R.id.loadMessages);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewModel.getMessages().observe(DriverInboxActivity.this, messageDTOList -> {

                            if (messageDTOList != null){
                                RelativeLayout mainContent = findViewById(R.id.mainContent);
                                mainContent.removeAllViews();
//                                fragmentManager.beginTransaction().remove(DriverInboxFragment.newInstance(messageDTOList, panic.isChecked(), panic.isChecked(), ride.isChecked())).commit();
                                fragmentManager.beginTransaction().add(R.id.mainContent, DriverInboxFragment.newInstance(messageDTOList, panic.isChecked(), support.isChecked(), ride.isChecked())).commit();
                            }


                        }
                );
                viewModel.fetchMessages(id);
            }
        });


        ImageView home = findViewById(R.id.home);
        ImageView history = findViewById(R.id.history);
        ImageView inbox = findViewById(R.id.inbox);
        ImageView profile = findViewById(R.id.profile);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInboxActivity.this, DriverMainActivity.class));
                finish();

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInboxActivity.this, DriverHistoryActivity.class));
                finish();

            }
        });
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInboxActivity.this, DriverInboxActivity.class));
                finish();

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DriverInboxActivity.this, DriverAccountActivity.class));
                finish();

            }
        });

    }
}