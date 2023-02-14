package com.eximius.annimonclient.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.Api;
import com.eximius.annimonclient.MainActivity;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.ForumAdapter;
import com.eximius.annimonclient.fragments.forum.ForumViewTopics;

public class Forum extends Fragment {

	ExpandableListView lv;
	ForumAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_forum, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getActivity().setTitle("Форум");
		lv = view.findViewById(R.id.fragment_forumExpandableListView);
		
		new GetForums().execute();
		lv.setOnChildClickListener(new OnChildClickListener(){

				@Override
				public boolean onChildClick(ExpandableListView p1, View p2, int p3, int p4, long p5) {
					getActivity().getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.content_frame, new ForumViewTopics(adapter.getChild(p3, p4).getId(), adapter.getChild(p3, p4).getText())).addToBackStack("").commit();
					return true;
				}


			});
	}

	private class GetForums extends AsyncTask<Void,Void,Void> {

		@Override
		protected void onPreExecute() {
			((MainActivity)getActivity()).showProgress();
			super.onPreExecute();
		}


		@Override
		protected Void doInBackground(Void[] p1) {
			adapter = new ForumAdapter(getActivity(), Api.forumGetSections());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			lv.setAdapter(adapter);

			for (int i = 0; i < adapter.getGroupCount(); i++) {
				lv.expandGroup(i);
			}

			((MainActivity)getActivity()).hideProgress();

		}
	}

}
