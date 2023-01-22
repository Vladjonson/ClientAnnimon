package com.eximius.annimonclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.data.Photo;
import java.util.ArrayList;

public class PhotoAdapter extends BaseAdapter  {

    private Context context;
    private ArrayList<Photo> photos;
    private ImageView img;

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Photo getItem(int position) {
        return photos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = view;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.photo_list_item, null);
        }

        img = v.findViewById(R.id.photolistitemImageView);

        Glide.with(context)
            .load(getItem(position).getPhoto())
            .placeholder(R.drawable.no_ava)
            .into(img);

		return v;
    }
}
