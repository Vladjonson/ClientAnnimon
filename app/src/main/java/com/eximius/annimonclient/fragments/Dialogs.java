package com.eximius.annimonclient.fragments;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eximius.annimonclient.R;
import android.widget.ListView;
import com.eximius.annimonclient.adapters.MessageAdapter;
import java.util.ArrayList;
import com.eximius.annimonclient.data.Message;
import android.widget.AdapterView;
import android.widget.Adapter;
import com.eximius.annimonclient.Api;

public class Dialogs extends Fragment {
	ListView lv;
	MessageAdapter adapter;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialogs,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lv=view.findViewById(R.id.fragmentdialogsListView);
		adapter=new MessageAdapter(getActivity(),Api.getMessages());
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
					getFragmentManager().beginTransaction().replace(R.id.content_frame,new Dialog()).addToBackStack("").commit();
				}
				
			
		});
		
	}
    
    
    
}
