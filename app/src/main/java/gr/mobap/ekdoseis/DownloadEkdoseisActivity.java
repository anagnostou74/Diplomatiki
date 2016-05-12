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

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gr.mobap.AnalyticsApplication;
import gr.mobap.MainActivity;
import gr.mobap.R;


public class DownloadEkdoseisActivity extends MainActivity {
    // Defined Array values to show in ListView
    public static final String[] titles = new String[]{
            "Σύνταγμα της Ελλάδος",
            "Πρόεδροι της Βουλής, Γερουσίας και Εθνοσυνελεύσεων, 1821-2008",
            "Βουλή των Εφήβων",
            "Βουλή των Ελλήνων",
            "Έλληνες Βουλευτές & Ευρω-Βουλευτές, Εθνικές εκλογές της 16ης Σεπ 2007, Ευρωεκλογές της 7ης Ιουνίου 2009 - Άυγουστος 2009",
            "Η αθηναϊκή δημοκρατία μιλάει με τις επιγραφές της των Ελλήνων Μέρος 1ο - Μέρος 2ο",
            "Πενήντα Χρόνια Ευρωπαϊκό Κοινοβούλιο Εμπειρία και Προοπτικές",
            "15η Σεπτεμβρίου - Παγκόσμια Ημέρα της Δημοκρατίας",
            "Ευρω-Μεσογειακή Κοινοβουλευτική Συνέλευση (Μάρτιος 2007 - Μάρτιος 2008)",
            "Ο Λόγος του Θεόδωρου Κολοκοτρώνη στην Πνύκα",
            "Προσανατολισμοί του Νέου Ελληνισμού",
            "ΕΠΙΦΥΛΛΙΔΕΣ - Μάριος Πλωρίτης 1993 - 2004",
            "ΕΠΙΦΥΛΛΙΔΕΣ - Μάριος Πλωρίτης 1989 - 1993",
            "Nicholas Kaltchas : Introduction to the Constitutional History of Modern of Greece",
            "Ορόσημα Ελληνο-Γερμανικών σχέσεων : Ορόσημα",
            "MEILENSTEINE DEUTSCH–GRIECHISCHER BEZIEHUNGEN : Herausgegeben von Wolfgang Schultheiß, Evangelos Chrysos",
            "Ευρωπαϊκό Κοινοβούλιο 50 Χρόνια - Εμπειρία & Προοπτικές:  Πρακτικά Συνεδρίου",
            "Μισός αιώνας γυναικείας ψήφου, μισός αιώνας γυναίκες στη Βουλή, Μάρω Παντελίδου Μαλούτα",
            "Θουκυδίδου Περικλέους Επιτάφιος",
            "Η Μετάβαση στη Δημοκρατία και το Σύνταγμα του 1975, Γιώργος Κασιμάτης",
            "Πρόεδροι της Βουλής, Γερουσίας & Εθνοσυνελεύσεων 1821 - 2008",
            "Νομική Διάταξις της Ανατολικής Χέρσου Ελλάδος",
            "Προσωρινόν Πολίτευμα της νήσου Κρήτης",
            "Νόμος της Επιδαύρου",
            "Πολιτικόν Σύνταγμα της Ελλάδος",
            "Ηγεμονικό",
            "Σύνταγμα του 1844",
            "Σύνταγμα του 1864",
            "Σύνταγμα του 1911",
            "Σύνταγμα του 1927",
            "Σύνταγμα του 1952"
    };
    public static final String[] descriptions = new String[]{
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf",
            "pdf"
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
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
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
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
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
                R.layout.item_books, rowItems);
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/SYNTAGMA1_1.pdf";
                        break;
                    case 1:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/Proedroi.pdf";
                        break;
                    case 2:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/vouli_efivon_low.pdf";
                        break;
                    case 3:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/vouli_site_version.pdf";
                        break;
                    case 4:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/BOOK.pdf";
                        break;
                    case 5:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/epigr_2.pdf";
                        break;
                    case 6:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/EuroParl50.pdf";
                        break;
                    case 7:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/democracy.pdf";
                        break;
                    case 8:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/EUROMED.pdf";
                        break;
                    case 9:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/8c3e9046-78fb-48f4-bd82-bbba28ca1ef5/kolokotronis.pdf";
                        break;
                    case 10:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Books/soma_low.pdf";
                        break;
                    case 11:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Books/ploritisB-low.pdf";
                        break;
                    case 12:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Books/ploritisA-low.pdf";
                        break;
                    case 13:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Kaltchas%20book.pdf";
                        break;
                    case 14:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Orosima%20book.pdf";
                        break;
                    case 15:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Books/soma_germ_1.pdf";
                        break;
                    case 16:
                        pdfURL = "http://foundation.parliament.gr/contentData/%CF%80%CF%81%CE%B1%CE%BA%CF%84%CE%B9%CE%BA%CE%AC%20%CF%83%CF%85%CE%BD%CE%B5%CE%B4%CF%81%CE%AF%CE%BF%CF%85.pdf";
                        break;
                    case 17:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Books/Gynaikes.pdf";
                        break;
                    case 18:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Books/epitaphios.pdf";
                        break;
                    case 19:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Books/Kasimatis.pdf";
                        break;
                    case 20:
                        pdfURL = "http://foundation.parliament.gr/VoulhFoundation/VoulhFoundationPortal/images/site_content/voulhFoundation/file/Books/%CE%A0%CF%81%CE%BF%CE%AD%CE%B4%CF%81%CE%BF%CE%B9.pdf";
                        break;
                    case 21:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn04a.pdf";
                        break;
                    case 22:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn08.pdf";
                        break;
                    case 23:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn07.pdf";
                        break;
                    case 24:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn09.pdf";
                        break;
                    case 25:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn11.pdf";
                        break;
                    case 26:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn12.pdf";
                        break;
                    case 27:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn13.pdf";
                        break;
                    case 28:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn14.pdf";
                        break;
                    case 29:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn15.pdf";
                        break;
                    case 30:
                        pdfURL = "http://www.hellenicparliament.gr/UserFiles/f3c70a23-7696-49db-9148-f24dce6a27c8/syn16.pdf";
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
                    Toast.makeText(DownloadEkdoseisActivity.this, getString(R.string.aneu_diktiou),
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