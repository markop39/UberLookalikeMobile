package com.example.taxidriver.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.taxidriver.R;
import com.example.taxidriver.data.dto.MessageDTO;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.ui.activities.driver.DriverHistoryDetailActivity;
import com.example.taxidriver.ui.adapters.DriverInboxAdapter;
import com.example.taxidriver.ui.adapters.HistoryAdapter;
import com.example.taxidriver.ui.fragments.placeholder.PlaceholderContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class DriverInboxFragment extends ListFragment {


    private List<MessageDTO> messages;
    private boolean panic;
    private  boolean support;
    private boolean ride;



    public static DriverInboxFragment newInstance(List<MessageDTO> messageDTOList, boolean panic, boolean support, boolean ride) {

        DriverInboxFragment driverInboxFragment = new DriverInboxFragment();
        driverInboxFragment.setMessages(messageDTOList);
        driverInboxFragment.setPanic(panic);
        driverInboxFragment.setRide(ride);
        driverInboxFragment.setSupport(support);
        return driverInboxFragment;
    }

    public boolean isPanic() {
        return panic;
    }

    public void setPanic(boolean panic) {
        this.panic = panic;
    }

    public boolean isSupport() {
        return support;
    }

    public void setSupport(boolean support) {
        this.support = support;
    }

    public boolean isRide() {
        return ride;
    }

    public void setRide(boolean ride) {
        this.ride = ride;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle data) {

        this.setListAdapter(new DriverInboxAdapter(this.getActivity(), messages, panic ,ride ,support ));

        View view = inflater.inflate(R.layout.history_list, viewGroup, false);

        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView listView, @NonNull View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);


    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messageDTOList) {
        this.messages = messageDTOList;
    }
}