package com.example.taxidriver.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.example.taxidriver.R;
import com.example.taxidriver.domain.model.Message;

import java.util.ArrayList;

public class PassengerInboxAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Message> items; //data source of the list adapter

    //public constructor
    public PassengerInboxAdapter(Context context, ArrayList<Message> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.inbox_list, parent, false);
        }

        // get current item to be displayed
        Message currentItem = (Message) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.textView10);
        TextView textViewItemDescription = (TextView)
                convertView.findViewById(R.id.textView11);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getId());
        textViewItemDescription.setText(currentItem.getBody());

        // returns the view for the current row
        return convertView;
    }

}
