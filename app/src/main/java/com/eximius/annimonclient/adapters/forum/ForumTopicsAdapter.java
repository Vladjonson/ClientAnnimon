package com.eximius.annimonclient.adapters.forum;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.forum.Topic;
import java.util.ArrayList;

public class ForumTopicsAdapter extends BaseAdapter {

	Context context;
	ArrayList<Topic> topics;

	public ForumTopicsAdapter(Context c, ArrayList<Topic> t) {
		this.context = c;
		this.topics = t;
	}

	@Override
	public int getCount() {
		return topics.size();
	}

	@Override
	public Topic getItem(int position) {
		return topics.get(position);
	}

	@Override
	public long getItemId(int p1) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			view = ((LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.forum_list_sub_item, parent, false);
		}
		TextView title=view.findViewById(R.id.forum_list_sub_itemTextView);
		title.setText(Html.fromHtml(getItem(position).getTitle()).toString());
		if (getItem(position).getIsClosed() == 1) {
			ImageView icon=view.findViewById(R.id.forum_list_sub_itemImageView);
			icon.setImageResource(R.drawable.ic_lock);
			icon.setColorFilter(Color.RED);
		}
		return view;
	}




}
