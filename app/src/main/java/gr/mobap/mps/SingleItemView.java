package gr.mobap.mps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import gr.mobap.AnalyticsApplication;
import gr.mobap.MainActivity;
import gr.mobap.R;

public class SingleItemView extends MainActivity {
    private Tracker mTracker;
    // Declare Variables
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
    MpsImageLoader mpsImageLoader = new MpsImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from item_mps
        setContentView(R.layout.item_mps);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        // Locate the ImageView in item_mps
        ImageView imgflag = (ImageView) findViewById(R.id.flag);
        // Locate the TextViews in item_mps.xml   //TextView txtrank = (TextView) findViewById(R.id.rank);
        TextView txtkomma = (TextView) findViewById(R.id.komma);
        TextView txtepitheto = (TextView) findViewById(R.id.epitheto);
        TextView txtonoma = (TextView) findViewById(R.id.onoma);
        TextView txtonomaPatros = (TextView) findViewById(R.id.onomaPatros);
        TextView txttitlos = (TextView) findViewById(R.id.titlos);
        TextView txtgovPosition = (TextView) findViewById(R.id.govPosition);
        TextView txtperifereia = (TextView) findViewById(R.id.perifereia);
        TextView txtbirth = (TextView) findViewById(R.id.birth);
        TextView txtfamily = (TextView) findViewById(R.id.family);
        TextView txtepaggelma = (TextView) findViewById(R.id.epaggelma);
        TextView txtparliamentActivities = (TextView) findViewById(R.id.parliamentActivities);
        TextView txtsocialActivities = (TextView) findViewById(R.id.socialActivities);
        TextView txtspoudes = (TextView) findViewById(R.id.spoudes);
        TextView txtlanguages = (TextView) findViewById(R.id.languages);
        TextView txtaddress = (TextView) findViewById(R.id.address);
        final TextView txtsite = (TextView) findViewById(R.id.site);
        txtsite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String to = txtsite.getText().toString();
                Intent browserIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(to));
                startActivity(browserIntent);
            }
        });
        final TextView txtemail = (TextView) findViewById(R.id.email);
        txtemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = txtemail.getText().toString();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{to});
                i.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.sxetika_mail));
                i.putExtra(Intent.EXTRA_TEXT, getString(R.string.main_me));
                try {
                    startActivity(Intent.createChooser(i,
                            getString(R.string.apostoli)));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SingleItemView.this, getString(R.string.aneu), Toast.LENGTH_SHORT).show();
                }
            }
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

        // Capture position and set results to the ImageView
        // Passes flag images URL into MpsImageLoader.class
        mpsImageLoader.DisplayImage(flag, imgflag);
        // Glide.with(this).load(flag).into(imgflag);

    }
}