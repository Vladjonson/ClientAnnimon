package com.eximius.annimonclient.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.Api;
import com.eximius.annimonclient.MainActivity;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.GalleryAdapter;


public class PhotoAlbums extends Fragment {

    private ListView lv;
    private GalleryAdapter adapter;
    private Button btnPrevPage;
    private Button btnNextPage;
    private int page;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_gallery, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.fragmentgalleryListView);
        btnPrevPage = view.findViewById(R.id.navPagePrev);
        btnNextPage = view.findViewById(R.id.navPageNext);

        new GetAlbums().execute();

        lv.setOnItemClickListener(new OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                    getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new FragmentPhotos(adapter.getItem(p3).getId()))
                        .addToBackStack("")
                        .commit();
                }
            });

        btnPrevPage.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    page -= 10;
                    new GetAlbums().execute();
                }
            });

        btnNextPage.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    page += 10;
                    new GetAlbums().execute();
                }
            });
	}

    private class GetAlbums extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
			((MainActivity)getActivity()).showProgress();
        }


        @Override
        protected Void doInBackground(Void[] p1) {
            adapter = new GalleryAdapter(getActivity(), Api.getPhotoAlbums(page));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            lv.setAdapter(adapter);
			((MainActivity)getActivity()).hideProgress();
        }
    }
}
