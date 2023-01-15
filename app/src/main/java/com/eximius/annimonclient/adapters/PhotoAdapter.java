package com.eximius.annimonclient.adapters;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import com.eximius.annimonclient.data.Photo;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import com.eximius.annimonclient.R;
import android.app.Dialog;
import com.bumptech.glide.Glide;



public class PhotoAdapter  extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private final ArrayList<Photo> photos;
    Context context;

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
       // this.context=context;
        inflater=inflater.from(context);
        this.photos = photos;
    }
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.photo_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.img.setImageResource(R.drawable.ava);
       /*Glide.with(context)
       .load("")
       .placeholder(R.drawable.ava)
       .into(holder.img);*/
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

     public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        ViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.photolistitemImageView);

        }
    }
}
