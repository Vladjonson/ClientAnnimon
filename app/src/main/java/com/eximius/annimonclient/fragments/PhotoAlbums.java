package com.eximius.annimonclient.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.R;
import android.os.AsyncTask;
import android.widget.ListView;
import com.eximius.annimonclient.adapters.GalleryAdapter;
import com.eximius.annimonclient.Api;
import android.app.Dialog;
import androidx.recyclerview.widget.RecyclerView;
import com.eximius.annimonclient.adapters.PhotoAdapter;
import android.content.Context;
import java.util.ArrayList;
import com.eximius.annimonclient.data.Photo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.text.Layout;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.widget.Gallery;
import android.widget.Adapter;
import com.eximius.annimonclient.adapters.Test;


public class PhotoAlbums extends Fragment {
    
    ListView lv;
    RecyclerView rvPhotos;
    GalleryAdapter adapter;
    Dialog dialog;
    Context context;
    ArrayList<Photo> p=new ArrayList<Photo>();
    Gallery g;
    Test test;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=container.getContext();
		return inflater.inflate(R.layout.fragment_gallery,container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
        
        lv=view.findViewById(R.id.fragmentgalleryListView);
        
        new GetAlbums().execute();
        
        //
        
        //
        
        lv.setOnItemClickListener(new OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                  //  dialog=new Dialog(getActivity());
                    /*dialog.setContentView(R.layout.dialog_photos);
                    rvPhotos=dialog.findViewById(R.id.dialogphotosRecyclerView);
                    LinearLayoutManager lm=new LinearLayoutManager(getActivity());
                    lm.setOrientation(lm.HORIZONTAL);
                    rvPhotos.setLayoutManager(lm);
                    //photos.add(new Photo());
                PhotoAdapter adapterPhotos=new PhotoAdapter(dialog.getContext(),Api.getPhotos());
                    rvPhotos.setAdapter(adapterPhotos);*/
                 /*   g=new Gallery(context);
                    test=new Test(getActivity(),Api.getPhotos());
                    g.setAdapter(test);
                    dialog.setContentView(g);*/
                    //adapterPhotos.notifyAll();
                  //  dialog.show();
                    //Toast.makeText(getActivity(),""+adapterPhotos.getItemCount(),Toast.LENGTH_SHORT).show();
                    getFragmentManager().beginTransaction().replace(R.id.content_frame,new FragmentPhotos()).addToBackStack("").commit();
                }
                
            
        });
	}
    
    private class GetAlbums extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        

        @Override
        protected Void doInBackground(Void[] p1) {
            adapter=new GalleryAdapter(getActivity(),Api.getPhotoAlbums());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            lv.setAdapter(adapter);
        }
        
        
    }



}
