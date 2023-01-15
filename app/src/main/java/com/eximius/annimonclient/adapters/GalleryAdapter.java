package com.eximius.annimonclient.adapters;

import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import java.util.ArrayList;
import com.eximius.annimonclient.data.PhotoAlbum;
import android.view.LayoutInflater;
import com.eximius.annimonclient.R;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;


public class GalleryAdapter extends BaseAdapter {
    
    private Context context;
    private ArrayList<PhotoAlbum> allAlbums;
    ImageView image;
    TextView title;
    TextView author;
    
    public GalleryAdapter(Context context,ArrayList<PhotoAlbum> albums){
        this.context=context;
        this.allAlbums=albums;
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

        image=v.findViewById(R.id.gallerylistitemImage);
        title=v.findViewById(R.id.gallerylistitemTitle);
        author=v.findViewById(R.id.gallerylistitemUser);
        
        Glide.with(context)
        .load(getItem(position).getUrlPhoto())
        .placeholder(R.drawable.ava)
        .into(image);
        
        title.setText(getItem(position).getName()+"("+getItem(position).getNumPhotos()+")");
        author.setText(getItem(position).getAuthor());

		return v;
    }
    
    
    
  
}
