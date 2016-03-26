package gr.mobap.mps;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.analytics.Tracker;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import gr.mobap.AnalyticsApplication;
import gr.mobap.MainActivity;
import gr.mobap.R;

public class MpsActivity extends MainActivity {
    private Tracker mTracker;
    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    MpsImageLoader mpsImageLoader = new MpsImageLoader(this);
    private List<MpsData> worldpopulationlist = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.listview_main);

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

        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MpsActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Παρακαλώ, περιμένετε...");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            worldpopulationlist = new ArrayList<MpsData>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("mps");
                // Locate the column named "ranknum" in Parse.com and order list
                // by ascending
                query.orderByAscending("Epitheto");
                ob = query.find();
                for (ParseObject mps : ob) {
                    // Locate images in flag column
                    ParseFile imageLoader = (ParseFile) mps.get("Image");
                    MpsData map = new MpsData();
                    map.setRank((String) mps.get("Rank"));
                    map.setEpitheto((String) mps.get("Epitheto"));
                    map.setOnoma((String) mps.get("Onoma"));
                    map.setOnomaPatros((String) mps.get("OnomaPatros"));
                    map.setTitlos((String) mps.get("Titlos"));
                    map.setGovPosition((String) mps.get("GovPosition"));
                    map.setKomma((String) mps.get("Komma"));
                    map.setPerifereia((String) mps.get("Perifereia"));
                    map.setBirth((String) mps.get("Birth"));
                    map.setFamily((String) mps.get("Family"));
                    map.setEpaggelma((String) mps.get("Epaggelma"));
                    map.setParliamentActivities((String) mps.get("ParliamentActivities"));
                    map.setSocialActivities((String) mps.get("SocialActivities"));
                    map.setSpoudes((String) mps.get("Spoudes"));
                    map.setAddress((String) mps.get("Address"));
                    map.setSite((String) mps.get("Site"));
                    map.setEmail((String) mps.get("Email"));
                    map.setFlag(imageLoader.getUrl());
                    worldpopulationlist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(MpsActivity.this,
                    worldpopulationlist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}