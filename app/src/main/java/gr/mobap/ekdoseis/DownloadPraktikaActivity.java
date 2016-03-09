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
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gr.mobap.AnalyticsApplication;
import gr.mobap.MainActivity;
import gr.mobap.R;


public class DownloadPraktikaActivity extends MainActivity {
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
            "28/06/2012-03/10/2013, pdf"
    };
    public static final Integer[] images = {
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp,
            R.drawable.ic_local_library_24dp
    };

    ListView listView;
    List<RowItem> rowItems;
    private String TAG = "RegsActivity", pdfURL;
    private Tracker mTracker;

    /**
     * functoin to open the PDF using an Intent
     *
     * @param context
     * @param localUri
     */
    public static final void openPDF(Context context, Uri localUri) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(localUri, "application/pdf");
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
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

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listEkd);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.listEkd);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item_books, rowItems);
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%91%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F.pdf";
                        break;
                    case 1:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%92%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F.pdf";
                        break;
                    case 2:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F.pdf";
                        break;
                    case 3:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%94%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F.pdf";
                        break;
                    case 4:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%CE%91%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%91%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F.pdf";
                        break;
                    case 5:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%CE%91%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%92%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F.pdf";
                        break;
                    case 6:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%CE%91%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F.pdf";
                        break;
                    case 7:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%CE%92%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%91%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F.pdf";
                        break;
                    case 8:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/GEN_EVRETIRIO_IB_B.pdf";
                        break;
                    case 9:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/%CE%99%CE%93%20%CE%A0%CE%95%CE%A1%CE%99%CE%9F%CE%94%CE%9F%CE%A3_%CE%91%20%CE%A3%CE%A5%CE%9D%CE%9F%CE%94%CE%9F%CE%A3_%CE%93%CE%95%CE%9D%CE%99%CE%9A%CE%9F%20%CE%95%CE%A5%CE%A1%CE%95%CE%A4%CE%97%CE%A1%CE%99%CE%9F.pdf";
                        break;
                    case 10:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/GEN_EVRETIRIO_IG_B.pdf";
                        break;
                    case 11:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/GEN_EVRETIRIO_IE_91.pdf";
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
// The place where the downloaded PDF file will be put
        final File tempFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), filename);
        // If we have downloaded the file before, just go ahead and show it(if its cached)
        if (tempFile.exists()) {
            openPDF(context, Uri.fromFile(tempFile));
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
                        openPDF(context, Uri.fromFile(tempFile));
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
}