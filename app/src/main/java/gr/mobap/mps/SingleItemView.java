package gr.mobap.mps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.analytics.Tracker;

import gr.mobap.AnalyticsApplication;
import gr.mobap.MainActivity;
import gr.mobap.R;

public class SingleItemView extends MainActivity {
	private Tracker mTracker;
	// Declare Variables
	String rank;
	String epitheto;
	String onoma;
	String flag;
	String position;
	MpsImageLoader mpsImageLoader = new MpsImageLoader(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		// [START shared_tracker]
		// Obtain the shared Tracker instance.
		AnalyticsApplication application = (AnalyticsApplication) getApplication();
		mTracker = application.getDefaultTracker();
		// [END shared_tracker]


		Intent i = getIntent();
		// Get the result of rank
		rank = i.getStringExtra("rank");
		// Get the result of country
		epitheto = i.getStringExtra("epitheto");
		// Get the result of population
		onoma = i.getStringExtra("onoma");
		// Get the result of flag
		flag = i.getStringExtra("flag");

		// Locate the TextViews in singleitemview.xml
		TextView txtrank = (TextView) findViewById(R.id.rank);
		TextView txtcountry = (TextView) findViewById(R.id.country);
		TextView txtpopulation = (TextView) findViewById(R.id.population);
		// Locate the ImageView in singleitemview.xml
		ImageView imgflag = (ImageView) findViewById(R.id.flag);
		// Set results to the TextViews
		txtrank.setText(rank);
		txtcountry.setText(epitheto);
		txtpopulation.setText(onoma);

		// Capture position and set results to the ImageView
		// Passes flag images URL into MpsImageLoader.class
		mpsImageLoader.DisplayImage(flag, imgflag);
	}
}