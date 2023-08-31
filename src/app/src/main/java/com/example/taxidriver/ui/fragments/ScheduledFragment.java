package com.example.taxidriver.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.taxidriver.R;
import com.example.taxidriver.data.dto.RideDTO;
import com.example.taxidriver.domain.model.Drive;
import com.example.taxidriver.ui.activities.driver.DriverHistoryDetailActivity;
import com.example.taxidriver.ui.adapters.HistoryAdapter;
import com.example.taxidriver.ui.adapters.ScheduledAdapter;
import com.example.taxidriver.util.Mockup;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ScheduledFragment extends ListFragment {

	private List<RideDTO> rideDTOList;
	private Context context;
	public static ScheduledFragment newInstance(List<RideDTO> rideDTOList, Context context) {

		ScheduledFragment historyFragment = new ScheduledFragment();
		historyFragment.setRideDTOList(rideDTOList);
		historyFragment.setContext(context);
		return historyFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle data) {

		this.setListAdapter(new ScheduledAdapter(this.getActivity(), rideDTOList, this.context));

        View view = inflater.inflate(R.layout.scheduled_list, viewGroup, false);

        return view;
	}

	@Nullable
	@Override
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<RideDTO> getRideDTOList() {
		return rideDTOList;
	}

	public void setRideDTOList(List<RideDTO> rideDTOList) {
		this.rideDTOList = rideDTOList;
	}
}