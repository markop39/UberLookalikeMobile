package com.example.taxidriver.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.ListFragment;

import com.example.taxidriver.R;
import com.example.taxidriver.ui.adapters.InboxAdapter2;

public class InboxFragment2 extends ListFragment {

	public static InboxFragment2 newInstance() {
        return new InboxFragment2();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle data) {
		this.setListAdapter(new InboxAdapter2(this.getActivity()));


        View view = inflater.inflate(R.layout.inbox2_list, viewGroup, false);

        return view;
	}







}