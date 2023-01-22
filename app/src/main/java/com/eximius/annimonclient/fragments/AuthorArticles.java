package com.eximius.annimonclient.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.eximius.annimonclient.Api;
import com.eximius.annimonclient.R;
import com.eximius.annimonclient.adapters.ArticlesAdapter;

public class AuthorArticles extends Fragment {

    private ListView lv;
    private ArticlesAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_authors_articles, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
        lv = view.findViewById(R.id.fragmentAuthorsArticlesListView);

        new GetAuthorArticles().execute();
	}

    class GetAuthorArticles extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void[] p1) {
            adapter = new ArticlesAdapter(getActivity(), Api.getAuthorArticles());
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            lv.setAdapter(adapter);
        }
    }
    
}
