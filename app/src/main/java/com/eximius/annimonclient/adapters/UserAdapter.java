package com.eximius.annimonclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.User;
import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

	ArrayList<User> allUsers;
	Context context;
	ImageView userAva;
	TextView userNick;
	ImageView userStatusOnline;
	ImageView imgStatus;
	TextView userStatus;

	public UserAdapter(Context context, ArrayList<User> allUsers) {
		this.context = context;
		this.allUsers = allUsers;
    }

	@Override
	public int getCount() {
		return allUsers.size();
	}

	@Override
	public User getItem(int position) {
		return allUsers.get(position);
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
			v = vi.inflate(R.layout.users_list_item, null);
		}

		userNick = v.findViewById(R.id.usersListItemNick);
		userStatusOnline = v.findViewById(R.id.userslistitemStatusOnline);
		imgStatus = v.findViewById(R.id.userslistitemImageStatus);
		userStatus = v.findViewById(R.id.userslistitemStatus);
        userAva = v.findViewById(R.id.usersListItemAva);
        try {
            Glide.with(context).load("https://annimon.com/files/photo/" + allUsers.get(position).getId() + ".jpg").placeholder(R.drawable.ava).into(userAva);
            //Glide.with(context).load(R.drawable.ava).into(userAva);
            // Glide.with(context).load(R.drawable.ic_account).into(userAva);
        } catch (Exception e) {}

		userStatus.setText(allUsers.get(position).getStatus());

		/*
         if(allUsers.get(position).getRank().equalsIgnoreCase("SV!")){
         userStatusOnline.setImageResource(R.drawable.ic_account_check);
         }else if(allUsers.get(position).getRank().equalsIgnoreCase("Adm")){
         userStatusOnline.setImageResource(R.drawable.ic_account_cog);
         }else if(allUsers.get(position).getRank().equalsIgnoreCase("Smd")){
         userStatusOnline.setImageResource(R.drawable.ic_shield_star);
         }else if(allUsers.get(position).getRank().equalsIgnoreCase("FMod")){
         userStatusOnline.setImageResource(R.drawable.ic_shield_account);
         }else if(allUsers.get(position).getRank().equalsIgnoreCase("DMod")){
         userStatusOnline.setImageResource(R.drawable.ic_folder_account);
         }else if(allUsers.get(position).getRank().equalsIgnoreCase("user")){
         userStatusOnline.setImageResource(R.drawable.ic_account);
         }
         */
		imgStatus.setColorFilter(context.getColor(R.color.colorAccent));



		if (allUsers.get(position).isOnline()) {
			userStatusOnline.setColorFilter(0xff4caf50);
		} else {
			userStatusOnline.setColorFilter(0xfff44336);
		}
		userNick.setText(allUsers.get(position).getNick());
		return v;
	}




}
