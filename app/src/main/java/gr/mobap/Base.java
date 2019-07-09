package gr.mobap;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import gr.mobap.diafaneia.Diafaneia;
import gr.mobap.ekdoseis.DownloadEkdoseisActivity;
import gr.mobap.ekdoseis.DownloadPraktikaActivity;
import gr.mobap.goverment.GovActivity;
import gr.mobap.images.Image;
import gr.mobap.komma.KommActivity;
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
import gr.mobap.vouli.Thesmos;
import gr.mobap.web.MailWeb;
import gr.mobap.web.MobapWeb;
import gr.mobap.web.SindesmoiActivity;
import gr.mobap.youtube.IntentsTvActivity;

/**
 * Created by kanag on 15/Οκτ/2016.
 */

public class Base extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private Tracker mTracker;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ProgressBar mRegistrationProgressBar;
    private TextView mInformationTextView;
    private boolean isReceiverRegistered;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public String TAG = getClass().getSimpleName();
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

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
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [START custom_event]
        String phone = "+302103707000";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Call")
                .build());
        // [END custom_event]
    }

    private void mail() {
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [START custom_event]
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Mail")
                .build());
        // [END custom_event]
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"infopar@parliament.gr"});
        i.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.epikoinonia_mail));
        i.putExtra(Intent.EXTRA_TEXT, getString(R.string.main_epikoinonia));
        try {
            startActivity(Intent.createChooser(i, getString(R.string.apostoli)));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, getString(R.string.aneu),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home: {
                Intent i = new Intent(Base.this, MainActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_thesmos: {
                Intent i = new Intent(Base.this, Thesmos.class);
                startActivity(i);
                break;
            }
            case R.id.nav_organosi: {
                Intent i = new Intent(Base.this, OrganosiActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_kommata: {
                Intent i = new Intent(Base.this, KommActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_vouleutes: {
                Intent i = new Intent(Base.this, MpsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_simera: {
                Intent i = new Intent(Base.this, HmerolActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_sindesmoi: {
                Intent i = new Intent(Base.this, SindesmoiActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_gov: {
                Intent i = new Intent(Base.this, GovActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_ekdoseis: {
                Intent i = new Intent(Base.this, DownloadEkdoseisActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_photo: {
                Intent i = new Intent(Base.this, Image.class);
                startActivity(i);
                break;
            }
            case R.id.nav_kanali: {
                Intent i = new Intent(Base.this, IntentsTvActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_live_ena: {
                Intent i = new Intent(Base.this, LiveVideoActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_live_dio: {
                Intent i = new Intent(Base.this, LiveVideoDioActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_nea: {
                Intent i = new Intent(Base.this, NeaActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_praktika: {
                Intent i = new Intent(Base.this, DownloadPraktikaActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_diafaneia: {
                Intent i = new Intent(Base.this, Diafaneia.class);
                startActivity(i);
                break;
            }
            case R.id.nav_eleghos: {
                Intent i = new Intent(Base.this, EleghosActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_katatethenta: {
                Intent i = new Intent(Base.this, NsKatActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_psifisthenta: {
                Intent i = new Intent(Base.this, NsPsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_sinedriaseis_epitropon: {
                Intent i = new Intent(Base.this, SinEpActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_ektheseis: {
                Intent i = new Intent(Base.this, EktheseisEpActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_drastiriotites: {
                Intent i = new Intent(Base.this, DrastActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_twitter: {
                Intent i = new Intent(Base.this, TimelineActivity.class);
                startActivity(i);
                break;
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
                Intent i = new Intent(Base.this, MapsActivity.class);
                startActivity(i);
                return true;
            case R.id.action_peri:
                Intent p = new Intent(Base.this, PeriActivity.class);
                startActivity(p);
                return true;
            case R.id.menu_refresh:
                refresh();
                return true;
            case R.id.menu_share:
                shareSocial();
                return true;
            case R.id.menu_play:
                try {
                    String url = "https://play.google.com/store/apps/dev?id=9092318171925704848";
                    Intent m = new Intent(Intent.ACTION_VIEW);
                    m.setData(Uri.parse(url));
                    startActivity(m);
                } catch (ActivityNotFoundException activityException) {
                    Log.e("navigate Site", "Site failed", activityException);
                }
                return true;
            case R.id.menu_fb:
                Intent k = new Intent(Base.this, Share.class);
                startActivity(k);
                return true;
            case R.id.nav_diktio:
                Intent d = new Intent(Base.this, MailWeb.class);
                startActivity(d);
                return true;
            case R.id.menu_mobap:
                Intent m = new Intent(Base.this, MobapWeb.class);
                startActivity(m);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareSocial() {
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        CharSequence subject = "Δες τι βρήκα!";
        CharSequence text = "Αξίζει να κατεβάσεις αυτή την εφαρμογή για να βλέπεις εργασίες, που επηρεάζουν το δικό σου σήμερα " +
                "https://play.google.com/store/apps/details?id=gr.mobap";
        Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + "share");
        // [START custom_event]
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
        // [END custom_event]

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject.toString());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        //startActivity(Intent.createChooser(intent, "Κοινοποίηση εφαρμογής"));

        // get available share intents
        List<Intent> targets = new ArrayList<>();
        Intent template = new Intent(Intent.ACTION_SEND);
        template.setType("text/plain");
        List<ResolveInfo> candidates = this.getPackageManager().
                queryIntentActivities(template, 0);

        // remove facebook which has a broken share intent
        for (ResolveInfo candidate : candidates) {
            String packageName = candidate.activityInfo.packageName;
            if (!packageName.equals("com.facebook.katana")) {
                Intent target = new Intent(android.content.Intent.ACTION_SEND);
                target.setType("text/plain");
                target.putExtra(Intent.EXTRA_TEXT, text);
                target.putExtra(Intent.EXTRA_SUBJECT, subject);
                target.setPackage(packageName);
                targets.add(target);
            }
        }
        Intent chooser = Intent.createChooser(targets.remove(0), "Κοινοποίηση εφαρμογής");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, targets.toArray(new Parcelable[]{}));
        startActivity(chooser);
    }

    private void refresh() {
        finish();
        startActivity(getIntent());
    }
}
