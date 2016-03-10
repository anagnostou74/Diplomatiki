package gr.mobap.vouli;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import java.util.ArrayList;
import java.util.List;

import gr.mobap.AnalyticsApplication;
import gr.mobap.MainActivity;
import gr.mobap.R;
import gr.mobap.ekdoseis.DownloadEkdoseisActivity;
import gr.mobap.ekdoseis.DownloadPraktikaActivity;
import gr.mobap.images.Image;
import gr.mobap.map.MapsActivity;
import gr.mobap.mps.Mps;
import gr.mobap.organosi.OrganosiActivity;
import gr.mobap.rss.activities.DrastActivity;
import gr.mobap.rss.activities.EktheseisEpActivity;
import gr.mobap.rss.activities.EleghosActivity;
import gr.mobap.rss.activities.NeaActivity;
import gr.mobap.rss.activities.NsKatActivity;
import gr.mobap.rss.activities.NsPsActivity;
import gr.mobap.rss.activities.SinEpActivity;
import gr.mobap.twitter.TimelineActivity;
import gr.mobap.video.LiveVideoActivity;
import gr.mobap.video.LiveVideoDioActivity;
import gr.mobap.vouli.EpitropesFragment;
import gr.mobap.vouli.KtirioFragment;
import gr.mobap.vouli.ProedreioFragment;
import gr.mobap.vouli.SyntagmaFragment;
import gr.mobap.vouli.ThesmosFragment;
import gr.mobap.web.SindesmoiActivity;
import gr.mobap.web.entos.DiktioActivity;
import gr.mobap.youtube.IntentsTvActivity;
import io.fabric.sdk.android.Fabric;

public class VouliActivity extends MainActivity {
    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Firebase.setAndroidContext(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ThesmosFragment(), getString(R.string.thesmos));
        adapter.addFrag(new KtirioFragment(), getString(R.string.ktirio));
        adapter.addFrag(new SyntagmaFragment(), getString(R.string.syntagma));
        adapter.addFrag(new ProedreioFragment(), getString(R.string.proedreio));
        adapter.addFrag(new EpitropesFragment(), getString(R.string.epitropes));
        viewPager.setAdapter(adapter);
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