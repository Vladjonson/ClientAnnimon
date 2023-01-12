package com.eximius.annimonclient.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.Api;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.UserAdapter;

public class Users extends Fragment {
	ListView lv;
	UserAdapter adapter;
	Context context;
    ProgressDialog progressDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.context = container.getContext();
		return inflater.inflate(R.layout.fragment_users, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		lv = view.findViewById(R.id.fragmentUsersListView);

        progressDialog = new ProgressDialog(getActivity());

        new GetUsers().execute();

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
					getFragmentManager().beginTransaction().replace(R.id.content_frame, new UserProfile(adapter.getItem(p3))).addToBackStack("").commit();
				}


            });



	}

    //
    private class GetUsers extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Loading users...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //adapter=new ArchiveNewsAdapter(getActivity(),Api.getNews(navPage));
                adapter = new UserAdapter(getActivity(), Api.getUsers());
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
