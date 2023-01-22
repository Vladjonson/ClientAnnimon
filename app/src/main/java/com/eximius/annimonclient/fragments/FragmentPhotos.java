package com.eximius.annimonclient.fragments;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.Api;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.PhotoAdapter;

public class FragmentPhotos extends Fragment {

    private Gallery galery;
    private PhotoAdapter adapter;
    private ProgressDialog dialog;
    private int id;

    public FragmentPhotos(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_photos, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        galery = view.findViewById(R.id.fragmentphotosGallery);
        dialog = new ProgressDialog(getActivity());

        new GetPhotos().execute();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_photos, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSharePhoto:
                Intent sendIntent= new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, adapter.getItem(galery.getSelectedItemPosition()).getPhoto());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;
            case R.id.itemDownloadPhoto:
                new DownloadPhoto().execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class GetPhotos extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle("Loading photos...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void[] p1) {
            adapter = new PhotoAdapter(getActivity(), Api.getPhotos(id));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            galery.setAdapter(adapter);
            dialog.hide();
        }
    }

    class DownloadPhoto extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle("Download");
            dialog.setMessage(String.format("Downloading %s...", adapter.getItem(galery.getSelectedItemPosition()).getName()));
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void[] p1) {
            DownloadManager downloadmanager = (DownloadManager)getActivity(). getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(adapter.getItem(galery.getSelectedItemPosition()).getPhoto());

            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setTitle(adapter.getItem(galery.getSelectedItemPosition()).getName());
            request.setDescription("Downloading");//request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, adapter.getItem(galery.getSelectedItemPosition()).getName());
            downloadmanager.enqueue(request);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            dialog.hide();
        }
    }
}
