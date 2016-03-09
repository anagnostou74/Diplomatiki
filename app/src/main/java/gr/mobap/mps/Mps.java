package gr.mobap.mps;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.analytics.Tracker;

import java.lang.ref.WeakReference;

import gr.mobap.AnalyticsApplication;
import gr.mobap.MainActivity;
import gr.mobap.R;

/**
 * TimelineActivity shows a full screen timeline which is useful for screenshots.
 */
public class Mps extends MainActivity {
    private Tracker mTracker;

    final WeakReference<Activity> activityRef = new WeakReference<Activity>(Mps.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vouleutes_list);

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

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            // Get ListView object from xml
            final ListView listView = (ListView) findViewById(R.id.listViewMps);

            // Create a new Adapter
            final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    R.layout.list_item_mps, R.id.onoma);

            // Assign adapter to ListView
            listView.setAdapter(adapter);

            // Use Firebase to populate the list.
            Firebase.setAndroidContext(this);

            Firebase ref = new Firebase("https://incandescent-heat-2208.firebaseio.com/vouleutis");
            // Attach an listener to read the data at our posts reference

            ref.addChildEventListener(new ChildEventListener() {
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    adapter.add((String) dataSnapshot.child("Onoma").getValue());
                    adapter.add((String) dataSnapshot.child("OnomaPatros").getValue());
                    adapter.add((String) dataSnapshot.child("Epitheto").getValue());
                    adapter.add((String) dataSnapshot.child("Komma").getValue());
                    adapter.add((String) dataSnapshot.child("Perifereia").getValue());
                    adapter.add((String) dataSnapshot.child("Email").getValue());
                }

                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    adapter.remove((String) dataSnapshot.child("Onoma").getValue());
                    adapter.remove((String) dataSnapshot.child("OnomaPatros").getValue());
                    adapter.remove((String) dataSnapshot.child("Epitheto").getValue());
                    adapter.remove((String) dataSnapshot.child("Komma").getValue());
                    adapter.remove((String) dataSnapshot.child("Perifereia").getValue());
                    adapter.remove((String) dataSnapshot.child("Email").getValue());
                }

                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                public void onCancelled(FirebaseError firebaseError) {
                }
            });
        } else {
            // display error
            Toast.makeText(this, getString(R.string.aneu_diktiou),
                    Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}