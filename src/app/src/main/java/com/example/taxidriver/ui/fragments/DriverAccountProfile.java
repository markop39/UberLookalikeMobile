package com.example.taxidriver.ui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.taxidriver.R;
import com.example.taxidriver.TaxiDriver;
import com.example.taxidriver.data.dto.ChangePasswordDTO;
import com.example.taxidriver.data.dto.DriverDTO1;
import com.example.taxidriver.data.dto.PassengerDTO;
import com.example.taxidriver.data.repository.DriverRepository;
import com.example.taxidriver.data.repository.PassengerRepository;
import com.example.taxidriver.data.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DriverAccountProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DriverAccountProfile extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    DriverRepository driverRepository = new DriverRepository();
    UserRepository userRepository = new UserRepository();
    SharedPreferences prefs = TaxiDriver.getAppContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);

    public DriverAccountProfile() {
        // Required empty public constructor
    }

    public static DriverAccountProfile newInstance(String param1, String param2) {
        DriverAccountProfile fragment = new DriverAccountProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    public static DriverAccountProfile newInstance() {
        return new DriverAccountProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private void fillDriverInfo(View view) {
        driverRepository.getDriverDetails(new retrofit2.Callback<DriverDTO1>() {
            @Override
            public void onResponse(Call<DriverDTO1> call, Response<DriverDTO1> response) {
                if (response.isSuccessful()) {
                    DriverDTO1 passenger = response.body();
                    EditText name = (EditText) view.findViewById(R.id.nameInput);
                    name.setText(passenger.getName());
                    EditText surname = (EditText) view.findViewById(R.id.surnameInput);
                    surname.setText(passenger.getSurname());
                    EditText address = (EditText) view.findViewById(R.id.addressInput);
                    address.setText(passenger.getAddress());
                    EditText phoneNum = (EditText) view.findViewById(R.id.phoneNumberInput);
                    phoneNum.setText(passenger.getTelephoneNumber());
                    EditText email = (EditText) view.findViewById(R.id.emailInput);
                    email.setText(passenger.getEmail());


                } else {

                    Toast.makeText(TaxiDriver.getAppContext(), "Repository connection unsucesfull!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DriverDTO1> call, Throwable t) {

            }
        }, prefs.getString("userId", null));


    }

    private void changeDriverInfo(View view) {
        DriverDTO1 passenger = new DriverDTO1();
        EditText name = (EditText) view.findViewById(R.id.nameInput);
        passenger.setName(name.getText().toString());
        EditText surname = (EditText) view.findViewById(R.id.surnameInput);
        passenger.setSurname(surname.getText().toString());
        EditText address = (EditText) view.findViewById(R.id.addressInput);
        passenger.setAddress(address.getText().toString());
        EditText phoneNum = (EditText) view.findViewById(R.id.phoneNumberInput);
        passenger.setTelephoneNumber(phoneNum.getText().toString());
        EditText email = (EditText) view.findViewById(R.id.emailInput);
        passenger.setEmail(email.getText().toString());
        passenger.setProfilePicture("profile.png");
        driverRepository.putDriverDetails(new retrofit2.Callback<DriverDTO1>() {
            @Override
            public void onResponse(Call<DriverDTO1> call, Response<DriverDTO1> response) {
                if (response.isSuccessful()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Change successful!");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    try {
                        builder.setMessage(response.errorBody().string())
                                .setTitle("Error");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<DriverDTO1> call, Throwable t) {

            }
        }, prefs.getString("userId", null), passenger);

    }

    private boolean validateForms(View view) {
        EditText name = (EditText) view.findViewById(R.id.nameInput);
        EditText surname = (EditText) view.findViewById(R.id.surnameInput);
        EditText address = (EditText) view.findViewById(R.id.addressInput);
        EditText phoneNum = (EditText) view.findViewById(R.id.phoneNumberInput);
        EditText email = (EditText) view.findViewById(R.id.emailInput);
        ArrayList<EditText> fieldsNonEmpty = new ArrayList<EditText>(Arrays.asList(name, surname, address, phoneNum, phoneNum));
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        final String phoneNumberPattern = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$";
//        if (!phoneNum.getText().toString().matches(phoneNumberPattern)) {
//            phoneNum.setError("Invalid email address");
//            return false;
//        } else {
//            phoneNum.setError(null);
//        }
        if (!email.getText().toString().matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
        }
        boolean isEmpty = false;
        for (EditText field : fieldsNonEmpty) {
            if (TextUtils.isEmpty(field.getText())) {
                field.setError("Cannot be empty");
                isEmpty = true;
            } else {
                field.setError(null);
            }
        }

        return !isEmpty;
    }

    private void showChangePasswordDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.change_password_dialog, null);
        builder.setView(dialogView);
        builder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText currentPassword = dialogView.findViewById(R.id.current_password_input);
                EditText newPassword = dialogView.findViewById(R.id.new_password_input);
                EditText confirmPassword = dialogView.findViewById(R.id.confirm_password_input);
                if (! newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(TaxiDriver.getAppContext(), "New password must match confirm password.", Toast.LENGTH_SHORT).show();
                } else if (newPassword.getText().toString().equals(currentPassword.getText().toString())) {
                    Toast.makeText(TaxiDriver.getAppContext(), "New password can't be the same as your current one", Toast.LENGTH_SHORT).show();
                } else {
                    userRepository.changePassword(prefs.getString("userId", null),
                            new ChangePasswordDTO(newPassword.getText().toString(), currentPassword.getText().toString()));
//                    dialog.dismiss();
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_passenger_account_profile, container, false);
        fillDriverInfo(view);
        Button confirm_changes = view.findViewById(R.id.confirm_button);
        confirm_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                if (!validateForms(view)) {
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(view1.getContext());
                builder.setMessage("Confirm changes?");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "Yes",
                        (dialog, id1) -> {
                            changeDriverInfo(view);
                            dialog.cancel();
                        });

                builder.setNegativeButton(
                        "No",
                        (dialog, id2) -> dialog.cancel());

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        Button change_password = view.findViewById(R.id.change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                showChangePasswordDialog(view);
            }
        });
        return view;
    }

}