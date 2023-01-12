package com.eximius.annimonclient;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.eximius.annimonclient.fragments.ArchiveNews;
import com.eximius.annimonclient.fragments.AuthorArticles;
import com.eximius.annimonclient.fragments.Dialogs;
import com.eximius.annimonclient.fragments.Diarys;
import com.eximius.annimonclient.fragments.Forum;
import com.eximius.annimonclient.fragments.GuestBook;
import com.eximius.annimonclient.fragments.Login;
import com.eximius.annimonclient.fragments.PhotoAlbums;
import com.eximius.annimonclient.fragments.QA;
import com.eximius.annimonclient.fragments.UsefulCodes;
import com.eximius.annimonclient.fragments.Users;
import com.eximius.annimonclient.fragments.WriterCorner;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	Toolbar toolbar;
	DrawerLayout drawer;
	ActionBarDrawerToggle toogle;
	NavigationView navigationView;
	FragmentManager fragmentManager;
	ArrayList<Fragment> allFragments=new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		toolbar = findViewById(R.id.toolbar);
		drawer = findViewById(R.id.drawer_layout);
		navigationView = findViewById(R.id.nav_view);
		fragmentManager = getSupportFragmentManager();
		toolbar.setTitleTextColor(Color.WHITE);
		setSupportActionBar(toolbar);
		toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toogle);
		toogle.syncState();

		navigationView.setNavigationItemSelectedListener(this);

		loadFragments();
    }

	@Override
	public boolean onNavigationItemSelected(MenuItem p1) {
		navigationView.setCheckedItem(p1);
		setTitle(p1.getTitle());
		fragmentManager.beginTransaction().replace(R.id.content_frame, allFragments.get(getNavId())).commit();
		//fragmentManager.beginTransaction().replace(R.id.content_frame,new Users()).commit();
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}



	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else if (fragmentManager.getBackStackEntryCount() > 0) {
			fragmentManager.popBackStack();
		} else {
			super.onBackPressed();
		}
	}


	public void loadFragments() {
		allFragments.add(new Users());
		allFragments.add(new Dialogs());
		allFragments.add(new ArchiveNews());
		allFragments.add(new AuthorArticles());
		allFragments.add(new QA());
		allFragments.add(new UsefulCodes());
		allFragments.add(new PhotoAlbums());
		allFragments.add(new Forum());
		allFragments.add(new WriterCorner());
		allFragments.add(new Diarys());
		allFragments.add(new GuestBook());
		fragmentManager.beginTransaction().replace(R.id.content_frame, new Login()).commit();

	}

	private int getNavId() {
		for (int i=0;i < navigationView.getMenu().size();i++) {
			if (navigationView.getMenu().getItem(i).isChecked()) {
				return i;
			}
		}
		return -1;
	}


}
