package com.example.taxidriver.ui.activities.passenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxidriver.R;
import com.example.taxidriver.ui.fragments.PassengerAccountFavouriteRoutes;
import com.example.taxidriver.ui.fragments.PassengerAccountProfile;
import com.example.taxidriver.ui.fragments.PassengerAccountReports;
import com.example.taxidriver.util.FragmentTransition;

//6. PassengerAccountActivity - omogućiti prikaz menija sa sledećim stavkama:
//        6.1. Nalog: pregled informacija o nalogu putnika i njihovo ažuriranje:
//        dodavanje profilne slike, izmena podataka o plaćanju, izmena osnovnih
//        informacija (ime, prezime, lokacija..)
//        6.2. Omiljene rute: prikaz omiljenih ruta od korisnika, odakle može da ponovo
//        izabere istu rutu za poručivanje odmah ili kasnije, kao i da je obriše iz
//        omiljenih.
//        6.3. Izveštaj: mogućnost generisanja izveštaja o prethodnim vožnjama za
//        definisan vremenski period. Na osnovu definisanog opsega datuma
//        prikaže se graf koji prikazuje broj vožnji po danima, broj pređenih
//        kilometara, količinu potrošenog novca za sopstvene vožnje. Uz te p

public class PassengerAccountActivity extends AppCompatActivity {
    private String currentTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_account);
        FragmentTransition.to(PassengerAccountProfile.newInstance(), this, false, R.id.mainContent);
        currentTab = "profile";
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayout accountToolbar = findViewById(R.id.passengerToolbar);
        TextView profile = findViewById(R.id.profile_toolbar);
        profile.setBackgroundColor(getResources().getColor(R.color.gray));
        TextView reports = findViewById(R.id.reports_toolbar);
        TextView favRoutes = findViewById(R.id.fav_routes_toolbar);

        favRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentTab.equals("favorites")){
                    FragmentTransition.to(PassengerAccountFavouriteRoutes.newInstance(), PassengerAccountActivity.this, false, R.id.mainContent);
                    resetToolbar();
                    favRoutes.setBackgroundColor(getResources().getColor(R.color.gray));
                    currentTab="favorites";                }

            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentTab.equals("reports")){
                    FragmentTransition.to(PassengerAccountReports.newInstance(), PassengerAccountActivity.this, false, R.id.mainContent);
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
                    FragmentTransition.to(PassengerAccountProfile.newInstance(), PassengerAccountActivity.this, false, R.id.mainContent);
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
                startActivity(new Intent(PassengerAccountActivity.this, PassengerMainActivity.class));
                finish();

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerAccountActivity.this, PassengerHistoryActivity.class));
                finish();
            }
        });
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerAccountActivity.this, PassengerInboxActivity2.class));
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
        TextView favRoutes = findViewById(R.id.fav_routes_toolbar);
        favRoutes.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}