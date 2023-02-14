package com.eximius.annimonclient.fragments.forum;

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
import com.eximius.annimonclient.adapters.forum.ForumTopicsAdapter;

public class ForumViewTopics extends Fragment {

	ListView lv;
	int section;
	int page=0;
	ForumTopicsAdapter adapter;
	String title;
	Button btnPrevPage,btnNextPage;

	public ForumViewTopics(int s, String t) {
		this.section = s;
		this.title = t;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_forum_view_topics, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getActivity().setTitle(title);

		lv = view.findViewById(R.id.fragment_forum_view_topicsListView);
		btnPrevPage = view.findViewById(R.id.navPagePrev);
		btnNextPage = view.findViewById(R.id.navPageNext);

		new GetTopics().execute();

		btnPrevPage.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if (page > 0) {
						page -= 10;
						new GetTopics().execute();
					}
				}
			});

		btnNextPage.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if (adapter.getCount() == 10) {
						page += 10;
						new GetTopics().execute();
					}
				}
			});

		lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
					getActivity().getSupportFragmentManager().
						beginTransaction().
						replace(R.id.content_frame, new ForumViewPosts(adapter.getItem(p3).getId())).addToBackStack("").commit();
				}
			});
	}

    private class GetTopics extends AsyncTask<Void,Void,Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			((MainActivity)getActivity()).showProgress();
		}

		@Override
		protected Void doInBackground(Void[] p1) {
			adapter = new ForumTopicsAdapter(getActivity(), Api.forumGetTopics(section, page));
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
