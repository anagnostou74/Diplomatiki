package gr.mobap.mps;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

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

public class SearchMps extends Base {
    private FirebaseAnalytics mFirebaseAnalytics;
    private Tracker mTracker;
    private AlertDialog.Builder builder;
    private Button perifereiaButton;
    private Button kommaButton;
    private Button searchButton;
    private Button clearButton;
    DatabaseReference dbReference;
    String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_search_mps);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END shared_tracker]

        perifereiaButton = (Button) findViewById(R.id.perifereiaButton);
        perifereiaButton.setOnClickListener(this::perifereiaChoiceDialog);

        kommaButton = (Button) findViewById(R.id.kommaButton);
        kommaButton.setOnClickListener(this::kommaChoiceDialog);

        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this::searchMps);

        clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this::clear);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dbReference = database.getReference();
        Log.d(TAG, "database " + database);
        Log.d(TAG, "dbReference " + dbReference);

    }

    private void perifereiaChoiceDialog(View view) {
        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.perif_label);

        final String[] items = {"Α΄ ΑΘΗΝΩΝ",
                "Α΄ ΑΝΑΤΟΛΙΚΗΣ ΑΤΤΙΚΗΣ",
                "Α΄ ΘΕΣΣΑΛΟΝΙΚΗΣ",
                "Α΄ ΠΕΙΡΑΙΩΣ",
                "ΑΙΤΩΛΟΑΚΑΡΝΑΝΙΑΣ",
                "ΑΡΓΟΛΙΔΟΣ",
                "ΑΡΚΑΔΙΑΣ",
                "ΑΡΤΗΣ",
                "ΑΧΑΪΑΣ",
                "Β΄ ΔΥΤΙΚΗΣ ΑΤΤΙΚΗΣ",
                "Β΄ ΘΕΣΣΑΛΟΝΙΚΗΣ",
                "Β΄ ΠΕΙΡΑΙΩΣ",
                "Β1΄ΒΟΡΕΙΟΥ ΤΟΜΕΑ ΑΘΗΝΩΝ",
                "Β2΄ ΔΥΤΙΚΟΥ ΤΟΜΕΑ ΑΘΗΝΩΝ",
                "Β3΄ ΝΟΤΙΟΥ ΤΟΜΕΑ ΑΘΗΝΩΝ",
                "ΒΟΙΩΤΙΑΣ",
                "ΓΡΕΒΕΝΩΝ",
                "ΔΡΑΜΑΣ",
                "ΔΩΔΕΚΑΝΗΣΟΥ",
                "ΕΒΡΟΥ",
                "ΕΥΒΟΙΑΣ",
                "ΕΥΡΥΤΑΝΙΑΣ",
                "ΖΑΚΥΝΘΟΥ",
                "ΗΛΕΙΑΣ",
                "ΗΜΑΘΙΑΣ",
                "ΗΡΑΚΛΕΙΟΥ",
                "ΘΕΣΠΡΩΤΙΑΣ",
                "ΙΩΑΝΝΙΝΩΝ",
                "ΚΑΒΑΛΑΣ",
                "ΚΑΡΔΙΤΣΗΣ",
                "ΚΑΣΤΟΡΙΑΣ",
                "ΚΕΡΚΥΡΑΣ",
                "ΚΕΦΑΛΛΗΝΙΑΣ",
                "ΚΙΛΚΙΣ",
                "ΚΟΖΑΝΗΣ",
                "ΚΟΡΙΝΘΙΑΣ",
                "ΚΥΚΛΑΔΩΝ",
                "ΛΑΚΩΝΙΑΣ",
                "ΛΑΡΙΣΗΣ",
                "ΛΑΣΙΘΙΟΥ",
                "ΛΕΣΒΟΥ",
                "ΛΕΥΚΑΔΟΣ",
                "ΜΑΓΝΗΣΙΑΣ",
                "ΜΕΣΣΗΝΙΑΣ",
                "ΞΑΝΘΗΣ",
                "ΠΕΛΛΗΣ",
                "ΠΙΕΡΙΑΣ",
                "ΠΡΕΒΕΖΗΣ",
                "ΡΕΘΥΜΝΗΣ",
                "ΡΟΔΟΠΗΣ",
                "ΣΑΜΟΥ",
                "ΣΕΡΡΩΝ",
                "ΤΡΙΚΑΛΩΝ",
                "ΦΘΙΩΤΙΔΟΣ",
                "ΦΛΩΡΙΝΗΣ",
                "ΦΩΚΙΔΟΣ",
                "ΧΑΛΚΙΔΙΚΗΣ",
                "ΧΑΝΙΩΝ",
                "ΧΙΟΥ"

        };
        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                perifereiaButton.setText(items[i]);
                dialogInterface.dismiss();
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void kommaChoiceDialog(View view) {
        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.komma);

        final String[] items = {"Nέα Δημοκρατία",
                "ΣΥΡΙΖΑ",
                "Κίνημα Αλλαγής",
                "Κ.Κ.Ε.",
        };

        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                kommaButton.setText(items[i]);
                dialogInterface.dismiss();
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void searchMps(View view) {

        String perifereia = getString(R.string.perif_label);
        String komma = getString(R.string.komma);

        if (perifereia == perifereiaButton.getText()) {
            if (komma == kommaButton.getText()) {
                Toast.makeText(getApplicationContext(), "Παρακαλώ κάνετε μία επιλογή", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), kommaButton.getText(), Toast.LENGTH_SHORT).show();

                // Read from the database
                dbReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Failed to read value.", Toast.LENGTH_SHORT).show();
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


            }
        } else if (komma == kommaButton.getText()) {
            if (perifereia == perifereiaButton.getText()) {
                Toast.makeText(getApplicationContext(), "Παρακαλώ κάνετε μία επιλογή", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), perifereiaButton.getText(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), perifereiaButton.getText() + " " + kommaButton.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    private void clear(View view) {
        perifereiaButton.setText(R.string.perif_label);
        kommaButton.setText(R.string.komma);
    }
}
