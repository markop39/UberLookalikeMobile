package com.example.taxidriver.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.LocationDTO3;
import com.example.taxidriver.data.dto.PaginatedResponse;
import com.example.taxidriver.data.dto.PassengerDTO;
import com.example.taxidriver.data.repository.AuthRepository;
import com.example.taxidriver.data.repository.UserRepository;
import com.example.taxidriver.domain.model.User;
import com.example.taxidriver.ui.activities.driver.DriverMainActivity;
import com.example.taxidriver.ui.activities.passenger.PassengerMainActivity;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        authRepository = new AuthRepository();

        EditText editTextEmail = findViewById(R.id.email);
        EditText editTextPassword = findViewById(R.id.password);
        EditText editTextAddress = findViewById(R.id.address);
        EditText editTextName = findViewById(R.id.name);
        EditText editTextSurname = findViewById(R.id.surname);
        EditText editTextPhoneNumber = findViewById(R.id.phoneNumber);

        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Editable emailEditable = editTextEmail.getText();
                Editable passwordEditable = editTextPassword.getText();
                Editable addressEditable = editTextAddress.getText();
                Editable nameEditable = editTextName.getText();
                Editable surnameEditable = editTextSurname.getText();
                Editable phoneNumberEditable = editTextPhoneNumber.getText();


                if(emailEditable == null || passwordEditable == null|| addressEditable == null
                        || nameEditable == null || surnameEditable == null || phoneNumberEditable == null)
                    return;

                String email = emailEditable.toString();
                String password = passwordEditable.toString();
                String address = addressEditable.toString();
                String name = nameEditable.toString();
                String surname = surnameEditable.toString();
                String telephoneNumber = phoneNumberEditable.toString();

                PassengerDTO passengerDTO = new PassengerDTO(name,surname,password,"johndoe.jpg",
                        telephoneNumber,email,address);

                authRepository.signup(passengerDTO, new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        else {
                            Toast.makeText(TaxiDriver.getAppContext(), "Form not filed successful!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(TaxiDriver.getAppContext(), "Form not filed successful!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


}