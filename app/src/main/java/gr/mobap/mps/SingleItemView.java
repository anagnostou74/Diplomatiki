package gr.mobap.mps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;

import gr.mobap.AnalyticsApplication;
import gr.mobap.Base;
import gr.mobap.R;

public class SingleItemView extends Base {
    String rank;
    String flag;
    String position;
    String epitheto;
    String onoma;
    String onomaPatros;
    String titlos;
    String govPosition;
    String komma;
    String perifereia;
    String birth;
    String family;
    String epaggelma;
    String parliamentActivities;
    String socialActivities;
    String spoudes;
    String languages;
    String address;
    String site;
    String email;
    String phone;
    MpsImageLoader mpsImageLoader = new MpsImageLoader(this);
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.item_mps);
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

        Intent i = getIntent();
        // Get the result of flag
        flag = i.getStringExtra("flag");
        // Get the result of rank
        rank = i.getStringExtra("rank");
        epitheto = i.getStringExtra("epitheto");
        onoma = i.getStringExtra("onoma");
        onomaPatros = i.getStringExtra("onomaPatros");
        titlos = i.getStringExtra("titlos");
        govPosition = i.getStringExtra("govPosition");
        komma = i.getStringExtra("komma");
        perifereia = i.getStringExtra("perifereia");
        birth = i.getStringExtra("birth");
        family = i.getStringExtra("family");
        epaggelma = i.getStringExtra("epaggelma");
        parliamentActivities = i.getStringExtra("parliamentActivities");
        socialActivities = i.getStringExtra("socialActivities");
        spoudes = i.getStringExtra("spoudes");
        languages = i.getStringExtra("languages");
        address = i.getStringExtra("address");
        site = i.getStringExtra("site");
        email = i.getStringExtra("email");
        phone = i.getStringExtra("phone");

        // Locate the ImageView in item_mps
        ImageView imgflag = findViewById(R.id.flag);
        // Locate the TextViews in item_mps.xml   //TextView txtrank = (TextView) findViewById(R.id.rank);
        TextView txtkomma = findViewById(R.id.komma);
        TextView txtepitheto = findViewById(R.id.epitheto);
        TextView txtonoma = findViewById(R.id.onoma);
        TextView txtonomaPatros = findViewById(R.id.onomaPatros);
        TextView txttitlos = findViewById(R.id.titlos);
        TextView txtgovPosition = findViewById(R.id.govPosition);
        TextView txtperifereia = findViewById(R.id.perifereia);
        TextView txtbirth = findViewById(R.id.birth);
        TextView txtfamily = findViewById(R.id.family);
        TextView txtepaggelma = findViewById(R.id.epaggelma);
        TextView txtparliamentActivities = findViewById(R.id.parliamentActivities);
        TextView txtsocialActivities = findViewById(R.id.socialActivities);
        TextView txtspoudes = findViewById(R.id.spoudes);
        TextView txtlanguages = findViewById(R.id.languages);
        TextView txtaddress = findViewById(R.id.address);
        final TextView txtsite = findViewById(R.id.site);
        txtsite.setOnClickListener(v -> {
            String to = txtsite.getText().toString();
            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(to));
            startActivity(browserIntent);
        });
        final TextView txtemail = findViewById(R.id.email);
        txtemail.setOnClickListener(v -> {
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

        final TextView txtphone = findViewById(R.id.phone);
        txtphone.setOnClickListener(v -> {
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


        // Set results to the TextViews
        //txtrank.setText(rank);
        txtepitheto.setText(epitheto);
        txtonoma.setText(onoma);
        txtonomaPatros.setText(onomaPatros);
        txttitlos.setText(titlos);
        txtgovPosition.setText(govPosition);
        txtkomma.setText(komma);
        txtperifereia.setText(perifereia);
        txtbirth.setText(birth);
        txtfamily.setText(family);
        txtepaggelma.setText(epaggelma);
        txtparliamentActivities.setText(parliamentActivities);
        txtsocialActivities.setText(socialActivities);
        txtspoudes.setText(spoudes);
        txtlanguages.setText(languages);
        txtaddress.setText(address);
        txtsite.setText(site);
        txtemail.setText(email);
        txtphone.setText(phone);

        // Capture position and set results to the ImageView
        // Passes flag images URL into MpsImageLoader.class
        //mpsImageLoader.DisplayImage(flag, imgflag);
        Glide.with(this).load(flag).into(imgflag);

    }
}