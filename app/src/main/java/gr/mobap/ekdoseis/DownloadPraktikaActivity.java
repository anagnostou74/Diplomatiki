package gr.mobap.ekdoseis;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gr.mobap.AnalyticsApplication;
import gr.mobap.Base;
import gr.mobap.R;


public class DownloadPraktikaActivity extends Base {
    private FirebaseAnalytics mFirebaseAnalytics;
    // Defined Array values to show in ListView
    public static final String[] titles = new String[]{
            "Ι ΠΕΡΙΟΔΟΣ, Σύνοδος Α",
            "Ι ΠΕΡΙΟΔΟΣ, Σύνοδος Β",
            "Ι ΠΕΡΙΟΔΟΣ, Σύνοδος Γ",
            "Ι ΠΕΡΙΟΔΟΣ, Σύνοδος Δ",
            "ΙΑ ΠΕΡΙΟΔΟΣ, Σύνοδος Α",
            "ΙΑ ΠΕΡΙΟΔΟΣ, Σύνοδος Β",
            "ΙΑ ΠΕΡΙΟΔΟΣ, Σύνοδος Γ",
            "ΙΒ ΠΕΡΙΟΔΟΣ, Σύνοδος Α",
            "ΙΒ ΠΕΡΙΟΔΟΣ, Σύνοδος Β",
            "ΙΓ ΠΕΡΙΟΔΟΣ, Σύνοδος Α",
            "ΙΓ ΠΕΡΙΟΔΟΣ, Σύνοδος Β",
            "ΙΕ ΠΕΡΙΟΔΟΣ, Σύνοδος Α",
            "ΙΕ ΠΕΡΙΟΔΟΣ, Σύνοδος Β",
            "ΙΕ ΠΕΡΙΟΔΟΣ, Σύνοδος Γ",
            "ΙΣΤ ΠΕΡΙΟΔΟΣ, Σύνοδος Α",
            "ΙΖ ΠΕΡΙΟΔΟΣ, Σύνοδος Α",
            "ΙΖ ΠΕΡΙΟΔΟΣ, Σύνοδος B",
            "ΙΖ ΠΕΡΙΟΔΟΣ, Σύνοδος Γ",
            "ΙΖ ΠΕΡΙΟΔΟΣ, Σύνοδος Δ",
    };
    public static final String[] descriptions = new String[]{
            "20/4/2000-27/9/2001, pdf",
            "1/10/2001-3/10/2002, pdf",
            "7/10/2002-2/10/2003, pdf",
            "6/10/2003-10/2/2004, pdf",
            "18/3/2004-29/9/2005, pdf",
            "3/10/2005-28/9/2006, pdf",
            "2/10/2006-2/8/2007, pdf",
            "26/9/2007-3/10/2008, pdf",
            "6/10/2008-3/9/2009, pdf",
            "14/10/2009-1/10/2010, pdf",
            "4/10/2010-30/9/2011, pdf",
            "28/06/2012-03/10/2013, pdf",
            "07/10/2013 - 03/10/2014, pdf",
            "06/10/2014 - 29/12/2014, pdf",
            "05/02/2015 - 26/08/2015, pdf",
            "03/10/2015 - 30/09/2016, pdf",
            "03/10/2016 - 29/09/2017, pdf",
            "02/10/2017 - 28/09/2018, pdf",
            "01/10/2018 - 07/06/2019, pdf"
    };
    public static final Integer[] images = {
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read,
            R.drawable.ekdoseis_read
    };

    ListView listView;
    List<RowItem> rowItems;
    private String TAG = "RegsActivity", pdfURL;
    private Tracker mTracker;


    public static final void openPDF(Context context, Uri localUri) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setDataAndType(localUri, "application/pdf");
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        setContentView(R.layout.activity_pdf);
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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Get ListView object from xml
        listView = findViewById(R.id.listEkd);

        rowItems = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = findViewById(R.id.listEkd);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.item_books, rowItems);
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener((parent, view, position, id) -> {

            switch (position) {
                case 0:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/i_per_a_sin_gen.pdf";
                    break;
                case 1:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/i_per_b_sin_gen.pdf";
                    break;
                case 2:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/i_per_c_sin_gen.pdf";
                    break;
                case 3:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/i_per_d_sin_gen.pdf";
                    break;
                case 4:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/ia_per_a_sin_gen.pdf";
                    break;
                case 5:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/ia_per_b_sin_gen.pdf.pdf";
                    break;
                case 6:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/ia_per_c_sin_gen.pdf";
                    break;
                case 7:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/ib_per_a_sin_gen.pdf";
                    break;
                case 8:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/ib_per_b_sin_gen.pdf";
                    break;
                case 9:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/ic_per_a_sin_gen.pdf.pdf";
                    break;
                case 10:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/ic_per_b_sin_gen.pdf";
                    break;
                case 11:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/ie_per_a_sin_gen.pdf";
                    break;
                case 12:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/evretirio_I%CE%95_%CE%92.pdf";
                    break;
                case 13:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/perIZ-sunG.pdf";
                    break;
                case 14:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/evretirio_ist_a.pdf";
                    break;
                case 15:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/evretirio_IZ_A.pdf";
                    break;
                case 16:
                    pdfURL = "https://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/sunB-per.IZ.pdf";
                    break;

                default:
            }

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                // fetch data
                CopyPDF();

            } else {
                // display error
                Toast.makeText(DownloadPraktikaActivity.this, getString(R.string.aneu_diktiou),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //method to write the PDFs file to sd card under a PDF directory.
    private void CopyPDF() {
        File sdDir = Environment.getExternalStorageDirectory();
        File newdir = new File(sdDir.getAbsolutePath() + "/PDF");
        newdir.mkdirs();
        try {
            downloadAndOpenPDF(this, pdfURL);
        } catch (Exception e) {
            Log.d("Downloader", e.getMessage());
        }
    }

    public void downloadAndOpenPDF(final Context context, final String pdfUrl) {
// Get filename
        final String filename = pdfUrl.substring(pdfUrl.lastIndexOf("/") + 1);
        final String decoded = Uri.decode(filename); //handles spaces at filename
// The place where the downloaded PDF file will be put
        final File tempFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), decoded);
        // If we have downloaded the file before, just go ahead and show it(if its cached)

        if (tempFile.exists()) {
            Uri contentUri;
            if (Build.VERSION.SDK_INT >= 24) {
                contentUri = FileProvider.getUriForFile(DownloadPraktikaActivity.this,
                        getApplicationContext().getPackageName() + ".provider",
                        tempFile);
                openPDF(context, contentUri);
            } else {
                contentUri = Uri.fromFile(tempFile);
                openPDF(context, contentUri);
            }
            return;
        }

        // Show progress dialog while downloading
        final ProgressDialog progress = ProgressDialog.show(context, "Λήψη έκδοσης", "Περιμένετε να κατέβει το pdf.", true);

        // Create the download request
        DownloadManager.Request r = new DownloadManager.Request(Uri.parse(pdfUrl));
        r.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, filename);
        final DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        //Broadcast receiver for when downloading the PDF is complete
        BroadcastReceiver onComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!progress.isShowing()) {
                    return;
                }
                context.unregisterReceiver(this);
                //Dismiss the progressDialog
                progress.dismiss();
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                Cursor c = dm.query(new DownloadManager.Query().setFilterById(downloadId));
                //if download was successful attempt to open the PDF
                if (c.moveToFirst()) {
                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        if (tempFile.exists()) {
                            Uri contentUri;
                            if (Build.VERSION.SDK_INT >= 24) {
                                contentUri = FileProvider.getUriForFile(DownloadPraktikaActivity.this,
                                        getApplicationContext().getPackageName() + ".provider",
                                        tempFile);
                                openPDF(context, contentUri);
                            } else {
                                contentUri = Uri.fromFile(tempFile);
                                openPDF(context, contentUri);
                            }
                            return;
                        }
                    }
                }
                c.close();
            }
        };
        //Resister the receiver
        context.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        // Enqueue the request
        dm.enqueue(r);
    }

    public Action getAction() {
        return Actions.newView("DownloadPraktika Page", "http://www.mobap.gr/downloadpraktikaactivity");
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