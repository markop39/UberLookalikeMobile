package com.example.taxidriver.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.taxidriver.R;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.data.repository.RideRepository;
import com.example.taxidriver.ui.activities.CurrentRideActivity;
import com.example.taxidriver.ui.activities.driver.DriverHistoryActivity;
import com.example.taxidriver.ui.activities.driver.DriverMainActivity;
import com.example.taxidriver.ui.activities.passenger.PassengerMainActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ScheduledAdapter extends BaseAdapter{

    private final Activity activity;
    private final List<RideDTO> rideDTOList;
    private RideRepository rideRepository;
    private Context context;

    public ScheduledAdapter(Activity activity, List<RideDTO> rideDTOList, Context context) {
        this.rideRepository = new RideRepository();
        this.rideDTOList = rideDTOList;
        this.activity = activity;
        this.context = context;
    }



    @Override
    public int getCount() {
        return rideDTOList.size();
    }
    @Override
    public Object getItem(int position) {
        return rideDTOList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RideDTO rideDTO = rideDTOList.get(position);
        @SuppressLint("ViewHolder")
        View view = activity.getLayoutInflater().inflate(R.layout.scheduled_list_element, null);


        Button acceptRideButton = view.findViewById(R.id.accept_ride);

        acceptRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rideRepository.startRide(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(context, CurrentRideActivity.class);
                        intent.putExtra("rideId", rideDTO.getId().toString());
                        intent.putExtra("startLat", rideDTO.getLocations().getDeparture().getLatitude().toString());
                        intent.putExtra("startLon", rideDTO.getLocations().getDeparture().getLongitude().toString());
                        intent.putExtra("endLat", rideDTO.getLocations().getDestination().getLatitude().toString());
                        intent.putExtra("endLon", rideDTO.getLocations().getDestination().getLongitude().toString());
                        context.startActivity(intent);
                        ((Activity) context).finish();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                }, rideDTO.getId().toString());
                //acceptRideDialog.dismiss();
            }
        });
        EditText destinationEditText = view.findViewById(R.id.destination2);
        EditText departureEditText = view.findViewById(R.id.departure2);
        TextView timeTextView = view.findViewById(R.id.time2);
        TextView priceTextView = view.findViewById(R.id.price2);
        CheckBox petCheckBox = view.findViewById(R.id.pet2);
        CheckBox kidCheckBox = view.findViewById(R.id.kid2);
        String departure = rideDTO.getLocations().getDeparture().getAddress();
        String destination = rideDTO.getLocations().getDestination().getAddress();
        LocalDateTime startTime = LocalDateTime.parse(rideDTO.getStartTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        timeTextView.setText("Time: "+ startTime.format(formatter));
        priceTextView.setText("Price: " + rideDTO.getTotalCost().toString()+ " $");
        departureEditText.setText(departure);
        destinationEditText.setText(destination);
        petCheckBox.setChecked(rideDTO.isPetTransport());
        kidCheckBox.setChecked(rideDTO.isBabyTransport());
        return view;
    }
}
