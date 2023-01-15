package com.eximius.annimonclient.adapters;

import android.widget.Adapter;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.SpinnerAdapter;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import com.eximius.annimonclient.data.Photo;
import android.widget.ImageView;
import android.view.LayoutInflater;
import com.eximius.annimonclient.R;
import com.bumptech.glide.Glide;

public class Test extends BaseAdapter  {
    
    Context context;
    ArrayList<Photo> photos;
    ImageView img;
    
    public Test(Context context,ArrayList<Photo> photos){
        this.context=context;
        this.photos=photos;
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

        img=v.findViewById(R.id.photolistitemImageView);
        
        if(position%2==0){
            //img.setImageDrawable(
            Glide.with(context)
            .load(R.drawable.ava)
            .centerInside()
            .into(img);
        //    );
        }else{
            Glide.with(context)
                .load(R.drawable.test)
                .into(img);
        }
        /*Glide.with(context)
        .load("")
        .placeholder(R.drawable.test)
        .into(img);*/

		return v;
    }
    

   }
