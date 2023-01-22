package com.eximius.annimonclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.Article;
import java.util.ArrayList;

public class ArticlesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Article> articles;
    private TextView titleTv;
    private TextView textTv;
    private TextView likesTv;
    private TextView authorTv;
    private TextView timeTv;

    public ArticlesAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Article getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return articles.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.articles_list_item, null);
        }

        titleTv = v.findViewById(R.id.articleslistitemTitle);
        textTv = v.findViewById(R.id.articleslistitemText);
        likesTv = v.findViewById(R.id.articleslistitemLikes);
        authorTv = v.findViewById(R.id.articleslistitemUser);
        timeTv = v.findViewById(R.id.articleslistitemTime);

        titleTv.setText(getItem(position).getTitle());
        textTv.setText(getItem(position).getText());
        likesTv.setText("" + getItem(position).getLikes());
        authorTv.setText(getItem(position).getAuthor());
        timeTv.setText(getItem(position).getTime());

		return v;
    }

}
