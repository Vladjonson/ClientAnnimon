package com.eximius.annimonclient.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.Api;
import com.eximius.annimonclient.MainActivity;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.UserAdapter;

public class Users extends Fragment {

	private ListView lv;
	private UserAdapter adapter;
    private int page;
    private Button btnPrevPage;
    private Button btnNextPage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_users, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		lv = view.findViewById(R.id.fragmentUsersListView);
        btnPrevPage = view.findViewById(R.id.navPagePrev);
        btnNextPage = view.findViewById(R.id.navPageNext);

        new GetUsers().execute();

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
					getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, new UserProfile(adapter.getItem(p3)))
                        .addToBackStack("")
                        .commit();
				}
            });

        btnPrevPage.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    page -= 10;
                    new GetUsers().execute();
                }
            });

        btnNextPage.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    page += 10;
                    new GetUsers().execute();
                }
            });
	}


    private class GetUsers extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((MainActivity)getActivity()).showProgress();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                adapter = new UserAdapter(getActivity(), Api.getUsers(page));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            lv.setAdapter(adapter);
            ((MainActivity)getActivity()).hideProgress();
        }
    }
}
