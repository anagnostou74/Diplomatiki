package gr.sextreme.ekdoseis;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gr.sextreme.MainActivity;
import gr.sextreme.R;

/**
 * Created by Gavin on 2015-04-05.
 */
public class DownloadPdfActivity extends MainActivity {
    private String TAG = "RegsActivity", pdfURL;
    ListView listView;
    List<RowItem> rowItems;
    // Defined Array values to show in ListView
    public static final String[] titles = new String[]{"Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος",
            "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος", "Τίτλος"};
    public static final String[] descriptions = new String[]{
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
    };
    public static final Integer[] images = {R.drawable.ic_menu_agenda, R.drawable.ic_menu_agenda,
            R.drawable.ic_menu_agenda, R.drawable.ic_menu_agenda,
            R.drawable.ic_menu_agenda, R.drawable.ic_menu_agenda,
            R.drawable.ic_menu_agenda, R.drawable.ic_menu_agenda,
            R.drawable.ic_menu_agenda, R.drawable.ic_menu_agenda,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

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
                R.layout.list_item, rowItems);
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
                    default:
                }

                CopyPDF();
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
}

