package com.eximius.annimonclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.Message;
import java.util.ArrayList;

public class MessageAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Message> allMessages;
	private ImageView userAva;
	private TextView userNick;
	private TextView message;
	private TextView dateSend;

	public MessageAdapter(Context context, ArrayList<Message> messages) {
		this.context = context;
		this.allMessages = messages;
	}

	@Override
	public int getCount() {
		return allMessages.size();
	}

	@Override
	public Message getItem(int position) {
		return allMessages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		View v = view;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.dialogs_list_item, null);
		}

		userAva = v.findViewById(R.id.dialogslistitemAva);
		userNick = v.findViewById(R.id.dialogslistitemUser);
		message = v.findViewById(R.id.dialogslistitemMessage);
		dateSend = v.findViewById(R.id.dialogslistitemDate);

		userNick.setText(allMessages.get(position).getSender());
		message.setText(allMessages.get(position).getMessage());
		dateSend.setText(allMessages.get(position).getDateSend());

		return v;
	}




}
