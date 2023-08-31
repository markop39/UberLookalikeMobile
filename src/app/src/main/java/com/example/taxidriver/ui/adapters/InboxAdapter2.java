package com.example.taxidriver.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taxidriver.R;
import com.example.taxidriver.domain.model.Message;
import com.example.taxidriver.util.Mockup;
import com.example.taxidriver.util.Mokap;

import java.time.format.DateTimeFormatter;


public class InboxAdapter2 extends BaseAdapter{

    private final Activity activity;

    public InboxAdapter2(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Mokap.getCinemas().size();
    }
    @Override
    public Object getItem(int position) {
        return Mokap.getCinemas().get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cinema cinema = Mokap.getCinemas().get(position);
        @SuppressLint("ViewHolder")
        View view = activity.getLayoutInflater().inflate(R.layout.inbox2_list_element, null);

        TextView name = view.findViewById(R.id.MessageType);
        TextView description = view.findViewById(R.id.Body);
        ImageView image = view.findViewById(R.id.item_icon);

        name.setText(((Cinema) cinema).getName());
        description.setText(cinema.getDescription());
        // image.setImageResource(cinema.getAvatar());


        return view;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // ... inflate the view as before
        Message message = Mockup.getMessages().get(position);
        @SuppressLint("ViewHolder")
        View view = activity.getLayoutInflater().inflate(R.layout.inbox2_list_element, null);

        TextView name = view.findViewById(R.id.MessageType);
        TextView description = view.findViewById(R.id.Body);
        ImageView image = view.findViewById(R.id.item_icon);

        name.setText(message.getMessageType().toString());
        description.setText(message.getBody());
        // image.setImageResource(cinema.getAvatar());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                View customView = activity.getLayoutInflater().inflate(R.layout.pop_out_message, null);
                builder.setView(customView);
                //builder.setTitle(cinema.getName());
                //builder.setMessage(cinema.getDescription());
                TextView nameTextView = customView.findViewById(R.id.text_name);
                nameTextView.setText(message.getMessageType().toString());
                TextView descriptionTextView = customView.findViewById(R.id.text_description);
                descriptionTextView.setText(message.getBody());
                ImageView imageView = customView.findViewById(R.id.image_message);
                TextView timeTextView = customView.findViewById(R.id.pop_up_ride_time);
                timeTextView.setText(message.getTimeSent().format(DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm")));
                TextView driverTextView = customView.findViewById(R.id.pop_up_driver_name);
                //driverTextView.setText(message.getDrive().getDriver().getLastname());
                //imageView.setImageDrawable(R.id.);message.getDrive().getDriver().getLastname() + " " +
                //                        message.getDrive().getDriver().getName()
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });

        return view;
    }
}
