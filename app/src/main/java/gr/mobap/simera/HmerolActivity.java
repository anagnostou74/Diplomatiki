package gr.mobap.simera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;

import java.util.ArrayList;
import java.util.List;

import gr.mobap.AnalyticsApplication;
import gr.mobap.Base;
import gr.mobap.R;

public class HmerolActivity extends Base {
    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        setContentView(R.layout.activity_vouli);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END shared_tracker]
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OlomeleiaFragment(), getString(R.string.today_olom));
        adapter.addFrag(new SimeraFragment(), getString(R.string.koin_eleghos));
        adapter.addFrag(new OlomeleiaEleghosFragment(), getString(R.string.today_el));
        adapter.addFrag(new EpiTodayFragment(), getString(R.string.today_ep));
        viewPager.setAdapter(adapter);
    }

    // After
    public Action getAction() {
        return Actions.newView("Hmerol Page", "http://www.mobap.gr/hmerolactivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
   /* If you’re logging an action on an item that has already been added to the index,
   you don’t have to add the following update line. See
   https://firebase.google.com/docs/app-indexing/android/personal-content#update-the-index for
   adding content to the index */
        FirebaseAppIndex.getInstance().update();
        FirebaseUserActions.getInstance().start(getAction());
    }

    @Override
    protected void onStop() {
        FirebaseUserActions.getInstance().end(getAction());
        super.onStop();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}