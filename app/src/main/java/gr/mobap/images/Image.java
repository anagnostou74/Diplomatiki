package gr.mobap.images;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;

import gr.mobap.AnalyticsApplication;
import gr.mobap.MainActivity;
import gr.mobap.R;

public class Image extends MainActivity {

    public static final String[] titles = new String[]{"Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος",
            "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος"};
    public static String IMGS[] = {
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_03_04_9_39.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_72%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_88%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_56%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_44%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_2a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_120%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_9_26%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_9_16%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_9_38a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_9_48%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_30_9_52%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_125%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_140.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_145a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2004-12-13_06%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2004-12-13_008a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_030.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_21_044.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2009_11_20_9_27%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2009_11_20_9_23%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2009_11_20_33a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_04_17_48.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_65%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_11_9_11%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_6.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_96%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_102%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/img2007_02_16_3.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_23.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_31.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_02_17_9_37.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2005-03-22_20.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/img2005_10_14_12%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_20_9_7.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/1%20IMG_2008_02_22_9_63a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/5%20IMG_2008_02_22_9_123.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/6%20IMG_2009_04_29_027.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/7%20%20%20%20%20120x70_final%20_xorisandra.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_03_05_9_48a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_02_27_58a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_02_27_68c.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_02_27_77a_hor.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_08_21_54.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_12_09_9_73b.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_09_29_9_91a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2%20IMG_2009_02_11_013.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_10_17_9_226%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_02_27_86a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/P7130030a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/00155%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/Img2004_02_20_64.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/8%20IMG_2008_10_17_9_273.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2α%20IMG_2009_02_11_069.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/3%20IMG_2008_09_29_9_148b.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/3a%20IMG_2008_09_29_9_144%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/3b%20IMG_2009_08_21_16.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/4%20IMG_2008_02_22_9_135.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/4c%20IMG_2008_02_27_99_6a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/4b%20IMG_2008_02_27_99_14a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_01_13_9_60a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/6%20IMG_2008_02_22_9_11.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_1%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_8%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_09_29_9_21a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_12_02_9_121%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_23%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_31%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_93%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_009%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_01_30_9_32%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_018%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_106%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_03_04_9_47a%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_08_25_18a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_08_25_47a%20copy.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_9_124a%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2008_09_22_9_1a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_1%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2010_04_29_7%20copy.JPG",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2012_06_28_9_102a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_01_13_9_9a.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/2%20IMG_2009_01_30_9_70.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/3%20IMG_2009_01_30_9_55.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/4%20IMG_2009_01_30_9_74.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/5%20IMG_2009_01_30_9_72.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2009_03_18_014.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2016_03_01_069.jpg",
            "http://www.hellenicparliament.gr/UserThumbs/130dd656-40e0-4140-baf7-ff5d9eaa975d/thumbs_737x410/IMG_2016_03_01_057.jpg",

    };
    GalleryAdapter mAdapter;
    RecyclerView mRecyclerView;
    ArrayList<ImageModel> data = new ArrayList<>();
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            for (int i = 0; i < IMGS.length; i++) {

                ImageModel imageModel = new ImageModel();
                imageModel.setName("Φωτογραφία " + i);
                imageModel.setUrl(IMGS[i]);
                data.add(imageModel);

            }

            mRecyclerView = (RecyclerView) findViewById(R.id.list);
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            mRecyclerView.setHasFixedSize(true);


            mAdapter = new GalleryAdapter(Image.this, data);
            mRecyclerView.setAdapter(mAdapter);

            mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                    new RecyclerItemClickListener.OnItemClickListener() {

                        @Override
                        public void onItemClick(View view, int position) {

                            Intent intent = new Intent(Image.this, DetailActivity.class);
                            intent.putParcelableArrayListExtra("data", data);
                            intent.putExtra("pos", position);
                            startActivity(intent);

                        }
                    }));
        } else {
            // display error
            Toast.makeText(this, getString(R.string.aneu_diktiou),
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Image.this, MainActivity.class);
                    // close this activity
                    finish();
                }
            }, 1000); // wait for 1 second
        }


    }

}