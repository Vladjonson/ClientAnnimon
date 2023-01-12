package com.eximius.annimonclient.fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.Api;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.ArchiveNewsAdapter;

public class ArchiveNews extends Fragment {
	ListView lv;
	ArchiveNewsAdapter adapter;
	ProgressDialog progressDialog;
	int navPage=0;
    Button navPrev;
    Button navNext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_archive_news, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lv = view.findViewById(R.id.fragmentarchivenewsListView);
        navPrev = view.findViewById(R.id.navpagePrev);
        navNext = view.findViewById(R.id.navpageNext);
		progressDialog = new ProgressDialog(getActivity());
		new GetNews().execute();

        navPrev.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    navPage -= 10;
                    new GetNews().execute();
                    adapter.notifyDataSetChanged();
                }

            });

        navNext.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    navPage += 10;
                    new GetNews().execute();
                    adapter.notifyDataSetChanged();
                }


            });
	}


	//
	private class GetNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
			progressDialog.setTitle("Loading news...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                adapter = new ArchiveNewsAdapter(getActivity(), Api.getNews(navPage));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
			lv.setAdapter(adapter);
			progressDialog.hide();
        }
    }
	//



}
