package com.eximius.annimonclient.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.Api;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.MessageAdapter;

public class GuestBook extends Fragment {
	private ListView lv;

	private MessageAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_guest_book, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lv = view.findViewById(R.id.fragmentguestbookListView);
		adapter = new MessageAdapter(getActivity(), Api.getGuestBookPosts());
		lv.setAdapter(adapter);
	}



}
