package gr.mobap;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.SyncListener;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;
import com.firebase.client.Firebase;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gr.mobap.ekdoseis.DownloadEkdoseisActivity;
import gr.mobap.ekdoseis.DownloadPraktikaActivity;
import gr.mobap.images.Image;
import gr.mobap.map.MapsActivity;
import gr.mobap.mps.MpsActivity;
import gr.mobap.organosi.OrganosiActivity;
import gr.mobap.rss.activities.DrastActivity;
import gr.mobap.rss.activities.EktheseisEpActivity;
import gr.mobap.rss.activities.EleghosActivity;
import gr.mobap.rss.activities.NeaActivity;
import gr.mobap.rss.activities.NsKatActivity;
import gr.mobap.rss.activities.NsPsActivity;
import gr.mobap.rss.activities.SinEpActivity;
import gr.mobap.simera.HmerolActivity;
import gr.mobap.twitter.TimelineActivity;
import gr.mobap.video.LiveVideoActivity;
import gr.mobap.video.LiveVideoDioActivity;
import gr.mobap.vouli.ElegxosFragment;
import gr.mobap.vouli.EpitropesFragment;
import gr.mobap.vouli.KtirioFragment;
import gr.mobap.vouli.NomoFragment;
import gr.mobap.vouli.SyntagmaFragment;
import gr.mobap.vouli.ThesmosFragment;
import gr.mobap.web.MailWeb;
import gr.mobap.web.SindesmoiActivity;
import gr.mobap.youtube.IntentsTvActivity;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SyncListener {
    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;
    CleverTapAPI cleverTap;
    private CleverTapAPI ct = null;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProgressBar mRegistrationProgressBar;
    private TextView mInformationTextView;
    private boolean isReceiverRegistered;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "mJfPJqTMOxPvkAfxdT33ettnY";
    private static final String TWITTER_SECRET = "LVQljKvSX2zhI6EThT8O4n1ERSAQr0JySmNwYNKKvhjeRjs8re";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try {
            // CleverTap
            CleverTapAPI.setDebugLevel(1); // optional
            ct = CleverTapAPI.getInstance(getApplicationContext());
            ct.enablePersonalization();  // optional
            ct.setSyncListener(this); // optional
        } catch (CleverTapMetaDataNotFoundException e) {
            // handle appropriately
            e.printStackTrace();
        } catch (CleverTapPermissionsNotSatisfied e) {
            // handle appropriately
            e.printStackTrace();
        }
        assert ct != null;
        // Read key value pairs from an incoming notification
        try {
            Bundle extras = getIntent().getExtras();
            for (String key : extras.keySet()) {
                Log.i("StarterProject", "key = " + key + "; value = " + extras.get(key).toString());
            }
        } catch (Exception e) {
            // Ignore
        }

        Firebase.setAndroidContext(this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        AppRate.app_launched(this);

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
    public File getCacheDir() {
        // NOTE: this method is used in Android 2.1
        return getApplicationContext().getCacheDir();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_klisi:
                // User chose the "Settings" item, show the app settings UI...
                klisi();
                return true;
            case R.id.action_mail:
                // User chose the "Settings" item, show the app settings UI...
                mail();
                return true;
            case R.id.action_map:
                // User chose the "Settings" item, show the app settings UI...
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
                return true;
            case R.id.action_peri:
                Intent p = new Intent(MainActivity.this, PeriActivity.class);
                startActivity(p);
                return true;
            case R.id.menu_refresh:
                refresh();
                return true;
            case R.id.menu_share:
                setShareIntent();
                return true;
            case R.id.nav_diktio:
                Intent d = new Intent(MainActivity.this, MailWeb.class);
                startActivity(d);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void refresh() {
        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        } else {
            finish();
            startActivity(getIntent());
        }
    }

    private void setShareIntent() {
        // [START custom_event]
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
        // [END custom_event]
        String text = "I'd love you to hear about ";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;
        super.onPause();
    }


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }


    private void klisi() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:21037070000"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e(getString(R.string.klisi_java), getString(R.string.failed),
                    activityException);
        }
    }

    private void mail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"infopar@parliament.gr"});
        i.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.epikoinonia_mail));
        i.putExtra(Intent.EXTRA_TEXT, getString(R.string.main_epikoinonia));
        try {
            startActivity(Intent.createChooser(i,
                    getString(R.string.apostoli)));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, getString(R.string.aneu),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_vouli) {
            Intent i = new Intent(MainActivity.this, HmerolActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_organosi) {
            Intent i = new Intent(MainActivity.this, OrganosiActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_vouleutes) {
            Intent i = new Intent(MainActivity.this, MpsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_simera) {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_sindesmoi) {
            Intent i = new Intent(MainActivity.this, SindesmoiActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_ekdoseis) {
            Intent i = new Intent(MainActivity.this, DownloadEkdoseisActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_photo) {
            Intent i = new Intent(MainActivity.this, Image.class);
            startActivity(i);
        } else if (id == R.id.nav_kanali) {
            Intent i = new Intent(MainActivity.this, IntentsTvActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_live_ena) {
            Intent i = new Intent(MainActivity.this, LiveVideoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_live_dio) {
            Intent i = new Intent(MainActivity.this, LiveVideoDioActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_nea) {
            Intent i = new Intent(MainActivity.this, NeaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_praktika) {
            Intent i = new Intent(MainActivity.this, DownloadPraktikaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_eleghos) {
            Intent i = new Intent(MainActivity.this, EleghosActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_katatethenta) {
            Intent i = new Intent(MainActivity.this, NsKatActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_psifisthenta) {
            Intent i = new Intent(MainActivity.this, NsPsActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_sinedriaseis_epitropon) {
            Intent i = new Intent(MainActivity.this, SinEpActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_ektheseis) {
            Intent i = new Intent(MainActivity.this, EktheseisEpActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_drastiriotites) {
            Intent i = new Intent(MainActivity.this, DrastActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_twitter) {
            Intent i = new Intent(MainActivity.this, TimelineActivity.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ThesmosFragment(), getString(R.string.thesmos));
        adapter.addFrag(new NomoFragment(), getString(R.string.nomo));
        adapter.addFrag(new ElegxosFragment(), getString(R.string.elegxos));
        adapter.addFrag(new KtirioFragment(), getString(R.string.ktirio));
        adapter.addFrag(new SyntagmaFragment(), getString(R.string.syntagma));
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

    // SyncListener
    public void profileDataUpdated(JSONObject updates) {
        Log.d("PR_UPDATES", updates.toString());
    }

    public void profileDidInitialize(String cleverTapID) {
        Log.d("CLEVERTAP_ID", cleverTapID);
    }

}