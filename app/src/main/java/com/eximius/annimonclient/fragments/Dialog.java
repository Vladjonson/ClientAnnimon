package com.eximius.annimonclient.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.MessageAdapter;
import com.eximius.annimonclient.data.Message;
import java.util.ArrayList;
import java.util.Date;

public class Dialog extends Fragment {

	ListView lv;
	EditText et;
	Button btn;
	ArrayList<Message> messages=new ArrayList<Message>();
	MessageAdapter adapter;
	Date date;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_dialog, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		date = new Date();
		lv = view.findViewById(R.id.fragmentdialogListView);
		et = view.findViewById(R.id.fragmentdialogEditText);
		btn = view.findViewById(R.id.fragmentdialogButton);

		for (int i = 0; i < 50; i++) {
			Message msg=new Message();
			msg.setSender("User " + 0);
			msg.setMessage("Mymessage " + i);
			msg.setDateSend("14.12.2022 / 12:12");
			messages.add(msg);
		}

		adapter = new MessageAdapter(getActivity(), messages);
		lv.setAdapter(adapter);

		btn = view.findViewById(R.id.fragmentdialogButton);

		btn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1) {
					Message msg=new Message();
					msg.setSender("User 1");
					msg.setMessage(et.getText().toString());
					msg.setDateSend("" + date.getDate() +
                                    "." + date.getMonth() +
                                    "." + date.getYear() +
                                    " / " + date.getHours() +
                                    ":" + date.getMinutes() + "");
					messages.add(0, msg);
					adapter.notifyDataSetChanged();
				}


            });
	}



}
