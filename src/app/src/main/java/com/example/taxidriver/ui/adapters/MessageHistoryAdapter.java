package com.example.taxidriver.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.taxidriver.R;
import com.example.taxidriver.domain.model.Message;

import java.util.ArrayList;

public class MessageHistoryAdapter extends BaseAdapter {
    private Context context;
    public ArrayList<Message> items;

    public MessageHistoryAdapter(Context context, ArrayList<Message> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public void removeItem(int index){
        items.remove(index);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view;
        Message msg = items.get(i);
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.favorite_route_list, null);
        } else {
            view = convertView;
        }
        TextView from = (TextView) view.findViewById(R.id.from);
        TextView to = (TextView) view.findViewById(R.id.to);
        //from.setText(String.format("%d; %d", msg.getStartingPoint().getLatitude(), msg.getStartingPoint().getLongitude()));
        //to.setText(String.format("%d; %d", msg.getDestination().getLatitude(), msg.getDestination().getLongitude()));


        return view;
    }
}
