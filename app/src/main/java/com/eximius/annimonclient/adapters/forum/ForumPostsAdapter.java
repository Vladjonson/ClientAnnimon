package com.eximius.annimonclient.adapters.forum;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.forum.Post;
import com.eximius.annimonclient.utils.GlideImageGetter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForumPostsAdapter extends BaseAdapter {

	Context context;
	ArrayList<Post> posts;

	public ForumPostsAdapter(Context c, ArrayList<Post> p) {
		this.context = c;
		this.posts = p;
	}

	@Override
	public int getCount() {
		return posts.size();
	}

	@Override
	public Post getItem(int position) {
		return posts.get(position);
	}

	@Override
	public long getItemId(int p1) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			view = ((LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.forum_list_item_post, parent, false);
		}

		ImageView ava=view.findViewById(R.id.forum_list_item_postUserAva);
		TextView user=view.findViewById(R.id.forum_list_item_postUser);
		TextView text=view.findViewById(R.id.forum_list_item_postText);
		TextView time=view.findViewById(R.id.forum_list_item_postTime);

		Glide.with(context)
			.load("https://annimon.com/files/avatar/" + getItem(position).getUserId() + ".png")
			.placeholder(R.drawable.no_ava)
			.into(ava);

		user.setText(getItem(position).getUser());
		
		text.setText(Html.fromHtml(getItem(position).getText(), new GlideImageGetter(text), null));
		text.setMovementMethod(LinkMovementMethod.getInstance());

		long t=getItem(position).getTime();
		Date date=new Date(t * 1000L);
		SimpleDateFormat df2 = new SimpleDateFormat("dd.MM.yyyy/HH:MM");
		time.setText(String.format("(%s)", df2.format(date)));

		return view;
	}




}
