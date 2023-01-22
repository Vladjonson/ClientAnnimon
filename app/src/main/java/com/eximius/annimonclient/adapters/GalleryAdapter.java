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
import com.eximius.annimonclient.data.PhotoAlbum;
import java.util.ArrayList;


public class GalleryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PhotoAlbum> allAlbums;
    private ImageView image;
    private TextView title;
    private TextView author;

    public GalleryAdapter(Context context, ArrayList<PhotoAlbum> albums) {
        this.context = context;
        this.allAlbums = albums;
    }

    @Override
    public int getCount() {
        return allAlbums.size();
    }

    @Override
    public PhotoAlbum getItem(int position) {
        return allAlbums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup group) {
        View v = view;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.gallery_list_item, null);
        }

        image = v.findViewById(R.id.gallerylistitemImage);
        title = v.findViewById(R.id.gallerylistitemTitle);
        author = v.findViewById(R.id.gallerylistitemUser);

        Glide.with(context)
            .load(getItem(position).getUrlPhoto())
            .placeholder(R.drawable.no_ava)
            .into(image);

        title.setText(getItem(position).getName() + "(" + getItem(position).getNumPhotos() + ")");
        author.setText(getItem(position).getAuthor());

		return v;
    }
}
