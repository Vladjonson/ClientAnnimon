package com.eximius.annimonclient.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.News;
import java.util.ArrayList;

public class ArchiveNewsAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<News> allNews;
	private TextView tvTitle;
	private TextView tvText;
	private TextView tvAuthor;

	public ArchiveNewsAdapter(Context context, ArrayList<News> news) {
		this.context = context;
		this.allNews = news;
	}

	@Override
	public int getCount() {
		return allNews.size();
	}

	@Override
	public News getItem(int position) {
		return allNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup p3) {
		View v = view;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.archive_news_list_item, null);
		}

		tvTitle = v.findViewById(R.id.archivenewslistitemTitle);
		tvText = v.findViewById(R.id.archivenewslistitemText);
		tvAuthor = v.findViewById(R.id.archivenewslistitemCreator);

		tvTitle.setText(Html.fromHtml(getItem(position).getTitle()));

		tvText.setText(Html.fromHtml(getItem(position).getText()));

		tvAuthor.setText(Html.fromHtml(getItem(position).getAuthor()));

		return v;
	}




}
