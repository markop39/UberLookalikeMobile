package com.example.taxidriver.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.taxidriver.R;
import com.example.taxidriver.data.dto.FavouriteRouteResponseDTO;
import com.example.taxidriver.domain.model.FavoriteRoute;

import java.util.ArrayList;
import java.util.List;

public class FavouriteRouteAdapter extends BaseAdapter {
    private Context context;
    public  List<FavouriteRouteResponseDTO> items;

    public FavouriteRouteAdapter(Context context, List<FavouriteRouteResponseDTO> items) {
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

    public List<FavouriteRouteResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<FavouriteRouteResponseDTO> items) {
        this.items = items;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view;
        FavouriteRouteResponseDTO fr = items.get(i);
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.favorite_route_list, null);
        } else {
            view = convertView;
        }
        TextView from = (TextView) view.findViewById(R.id.from);
        TextView to = (TextView) view.findViewById(R.id.to);
        from.setText(String.format("FROM: %s", fr.getStartingPoint().getAddress()));
        to.setText(String.format("TO: %s", fr.getDestination().getAddress()));


        return view;
    }

}
