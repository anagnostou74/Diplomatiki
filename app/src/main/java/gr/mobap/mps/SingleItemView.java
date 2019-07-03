package gr.mobap.mps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import gr.mobap.AnalyticsApplication;
import gr.mobap.Base;
import gr.mobap.R;

public class SingleItemView extends Base {

    public String TAG = getClass().getSimpleName();
    private String mPostKey;
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;
    public static final String EXTRA_POST_KEY = "post_key";
    private DatabaseReference mMpsReference;
    private ValueEventListener mMpsListener;
    private TextView txtrank;
    private TextView txtepitheto;
    private TextView txtonoma;
    private TextView txtonomaPatros;
    private TextView txttitlos;
    private TextView txtgovPosition;
    private TextView txtkomma;
    private TextView txtperifereia;
    private TextView txtbirth;
    private TextView txtfamily;
    private TextView txtepaggelma;
    private TextView txtparliamentActivities;
    private TextView txtsocialActivities;
    private TextView txtspoudes;
    private TextView txtlanguages;
    private TextView txtaddress;
    private TextView txtsite;
    private TextView txtemail;
    private TextView txtphone;
    private TextView txtfacebook;
    private TextView txttwitter;
    private TextView txtyt;
    private ImageView mpsImage;

    private ImageView phoneImage;
    private ImageView fbImage;
    private ImageView webImage;
    private ImageView twitterImage;
    private ImageView emailImage;
    private ImageView mapsImage;
    private ImageView ytImage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.singleview_mps);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END shared_tracker]

        // Get post key from intent
        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }
        // Initialize Database
        mMpsReference = FirebaseDatabase.getInstance().getReference()
                .child("mps").child(mPostKey);
        Log.d("Single", "Value is: " + mMpsReference);

        // Locate the ImageView in singleview_mps
        mpsImage = findViewById(R.id.mps_image);

        phoneImage = findViewById(R.id.phone_img);
        fbImage = findViewById(R.id.fb_img);
        webImage = findViewById(R.id.web_img);
        twitterImage = findViewById(R.id.twitter_img);
        emailImage = findViewById(R.id.email_img);
        mapsImage = findViewById(R.id.maps_img);
        ytImage = findViewById(R.id.yt_img);

        // Locate the TextViews in singleview_mpsew_mps.xml
        // TextView txtrank = (TextView) findViewById(R.id.rank);
        txtkomma = findViewById(R.id.komma);
        txtepitheto = findViewById(R.id.epitheto);
        txtonoma = findViewById(R.id.onoma);
        txtonomaPatros = findViewById(R.id.onomaPatros);
        txttitlos = findViewById(R.id.titlos);
        txtgovPosition = findViewById(R.id.govPosition);
        txtperifereia = findViewById(R.id.perifereia);
        txtbirth = findViewById(R.id.birth);
        txtfamily = findViewById(R.id.family);
        txtepaggelma = findViewById(R.id.epaggelma);
        txtparliamentActivities = findViewById(R.id.parliamentActivities);
        txtsocialActivities = findViewById(R.id.socialActivities);
        txtspoudes = findViewById(R.id.spoudes);
        txtlanguages = findViewById(R.id.languages);
        txtaddress = findViewById(R.id.address);
        txtsite = findViewById(R.id.site);
        txtfacebook = findViewById(R.id.fb);
        txttwitter = findViewById(R.id.tw);
        txtyt = findViewById(R.id.youtube);
        txtemail = findViewById(R.id.email);
        txtphone = findViewById(R.id.phone);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                MpsData mps = dataSnapshot.getValue(MpsData.class);
                // [START_EXCLUDE]

                String url = mps.image;

                Glide
                        .with(SingleItemView.this)
                        .load(url)
                        .centerCrop()
                        .placeholder(R.drawable.mps)
                        .into(mpsImage);

                //txtrank.setText(rank);
                txtepitheto.setText(mps.epitheto);
                txtonoma.setText(mps.onoma);
                txtonomaPatros.setText(mps.onomaPatros);
                txttitlos.setText(mps.titlos);
                txtgovPosition.setText(mps.govPosition);
                txtkomma.setText(mps.komma);
                txtperifereia.setText(mps.perifereia);
                txtbirth.setText(mps.birth);
                txtfamily.setText(mps.family);
                txtepaggelma.setText(mps.epaggelma);
                txtparliamentActivities.setText(mps.parliamentActivities);
                txtsocialActivities.setText(mps.socialActivities);
                txtspoudes.setText(mps.spoudes);
                txtlanguages.setText(mps.languages);
                txtaddress.setText(mps.address);
                txtsite.setText(mps.site);
                txtemail.setText(mps.email);
                txtphone.setText(mps.phone);
                txtfacebook.setText(mps.facebook);
                txttwitter.setText(mps.twitter);
                txtyt.setText(mps.youtube);
                // [END_EXCLUDE]
                phoneImage.setOnClickListener(v -> {
                    // [START custom_event]
                    mTracker.send(new HitBuilders.EventBuilder()
                            .setCategory("Action")
                            .setAction("CallMps")
                            .build());
                    // [END custom_event]
                    String po = txtphone.getText().toString();
                    Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", po, null));
                    startActivity(intent1);
                });
                emailImage.setOnClickListener(v -> {
                    String to = txtemail.getText().toString();
                    Intent i1 = new Intent(Intent.ACTION_SEND);
                    i1.setType("message/rfc822");
                    i1.putExtra(Intent.EXTRA_EMAIL,
                            new String[]{to});
                    i1.putExtra(Intent.EXTRA_SUBJECT,
                            getString(R.string.mps_mail));
                    i1.putExtra(Intent.EXTRA_TEXT, getString(R.string.main_mps));
                    try {
                        startActivity(Intent.createChooser(i1,
                                getString(R.string.apostoli)));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(SingleItemView.this, getString(R.string.aneu), Toast.LENGTH_SHORT).show();
                    }
                });
                webImage.setOnClickListener(v -> {
                    String to = txtsite.getText().toString();
                    Log.d(TAG, "txtsite: " + to);
                    if (to.length() > 2){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(to));
                        startActivity(browserIntent);
                    } else {
                        Toast.makeText(SingleItemView.this, getString(R.string.aneu_log), Toast.LENGTH_SHORT).show();

                    }
                });
                fbImage.setOnClickListener(v -> {
                    String to = txtfacebook.getText().toString();
                    Log.d(TAG, "txtfacebook: " + to);
                    if (to.length() > 2){
                        Intent intent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.facebook.com/" + to));
                        startActivity(intent);
                    } else {
                        Toast.makeText(SingleItemView.this, getString(R.string.aneu_log), Toast.LENGTH_SHORT).show();

                    }

                });
                twitterImage.setOnClickListener(v -> {
                    String to = txttwitter.getText().toString();
                    Log.d(TAG, "txttwitter: " + to);
                    if (to.length() > 2){
                        try {
                            getApplicationContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                            Intent intent = new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("twitter://" + to));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            Log.d(TAG, "Twitter: " + "twitter://" + to);
                        } catch (Exception e) {
                            Intent intent = new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://twitter.com/" + to));
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(SingleItemView.this, getString(R.string.aneu_log), Toast.LENGTH_SHORT).show();
                    }
                });
                mapsImage.setOnClickListener(v -> {
                    String to = txtaddress.getText().toString();
                    Log.d(TAG, "Maps: " + to);
                    if (to.length() > 2){
                        try {
                            getApplicationContext().getPackageManager().getPackageInfo("com.google.android.apps.maps", 0);
                            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + to);
                            Intent mapIntent = new Intent(
                                    Intent.ACTION_VIEW,
                                    gmmIntentUri);
                            startActivity(mapIntent);
                            Log.d(TAG, "Maps: " + "geo:0,0?q=" + to);
                        } catch (Exception e) {
                            Log.d(TAG, "Maps : " + "exception");

                        }
                    } else{
                        Toast.makeText(SingleItemView.this, getString(R.string.aneu_log), Toast.LENGTH_SHORT).show();
                    }
                });
                ytImage.setOnClickListener(v -> {
                    String to = txtyt.getText().toString();
                    Log.d(TAG, "ytImage: " + to);
                    if (to.length() > 2){
                        Intent browserIntent = new Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(to));
                        startActivity(browserIntent);
                    } else {
                        Toast.makeText(SingleItemView.this, getString(R.string.aneu_log), Toast.LENGTH_SHORT).show();

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(SingleItemView.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mMpsReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mMpsListener = postListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mMpsReference != null) {
            mMpsReference.removeEventListener(mMpsListener);
        }

    }

}


