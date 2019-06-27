package gr.mobap.map;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;

import gr.mobap.AnalyticsApplication;
import gr.mobap.AndroidNetworkUtility;
import gr.mobap.Base;
import gr.mobap.R;

public class MapsActivity extends Base implements OnMapReadyCallback {
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]
        // [START screen_view_hit]
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(this)) {
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } else {
            // display error
            Toast.makeText(this, getString(R.string.aneu_diktiou),
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Parliament.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Parliament and move the camera
        LatLng vouli = new LatLng(37.975279, 23.736974);
        mMap.addMarker(new MarkerOptions()
                .position(vouli).title("Βουλή των Ελλήνων")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .title(getString(R.string.app_name))
                .snippet(getString(R.string.map_epikoinonia))
        );
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vouli, 17));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vouli));
    }

    public Action getAction() {
        return Actions.newView("Maps Page", "http://www.mobap.gr/mapsactivity");
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
}
