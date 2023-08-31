package com.example.taxidriver.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.taxidriver.R;
import com.example.taxidriver.domain.model.DriveReport;

import java.util.ArrayList;

public class DriveReportAdapter extends BaseAdapter {
    private Context context;
    public ArrayList<DriveReport> items;

    public DriveReportAdapter(Context context, ArrayList<DriveReport> items) {
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

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        // TODO Fix adapter
        View view;
        DriveReport dr = items.get(i);
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.reports_list_item, null);
        } else {
            view = convertView;
        }
        TextView date = view.findViewById(R.id.reportItemDate);
        date.setText(dr.date.toLocalDate().toString());
        TextView rides = view.findViewById(R.id.reportItemRides);
        rides.setText(String.valueOf(dr.rides));
        TextView mileage = view.findViewById(R.id.reportItemKm);
        mileage.setText(String.valueOf(dr.mileage));
        TextView money = view.findViewById(R.id.reportItemSpent);
        money.setText(String.valueOf(dr.spent));
        return view;
    }
}
