package com.eximius.annimonclient.fragments.forum;

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
import com.eximius.annimonclient.MainActivity;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.forum.ForumPostsAdapter;

public class ForumViewPosts extends Fragment {

	ListView lv;
	ForumPostsAdapter adapter;
	Button btnPrevPage;
	Button btnNextPage;
	int topic,page=0;
	//String title;

	public ForumViewPosts(int topic) {
		//this.title=title;
		this.topic = topic;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_forum_view_posts, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		//getActivity().setTitle(title);
		lv = view.findViewById(R.id.fragment_forum_view_postsListView);
		btnNextPage = view.findViewById(R.id.navPageNext);
		btnPrevPage = view.findViewById(R.id.navPagePrev);

		new GetPosts().execute();

		btnPrevPage.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if (page > 0) {
						page -= 10;
						new GetPosts().execute();
					}
				}
			});

		btnNextPage.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					if (adapter.getCount() == 10) {
						page += 10;
						new GetPosts().execute();
					}
				}
			});
	}


    private class GetPosts extends AsyncTask<Void,Void,Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			((MainActivity)getActivity()).showProgress();
		}

		@Override
		protected Void doInBackground(Void[] p1) {
			adapter = new ForumPostsAdapter(getActivity(), Api.forumGetPosts(topic, page));
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
