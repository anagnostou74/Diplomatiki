/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gr.mobap.youtube;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;

import java.util.ArrayList;
import java.util.List;

import gr.mobap.AnalyticsApplication;
import gr.mobap.Base;
import gr.mobap.R;

/**
 * A sample activity which shows how to use the {@link YouTubeIntents} static methods to create
 * Intents that navigate the user to Activities within the main YouTube application.
 */
public final class IntentsTvActivity extends Base implements OnItemClickListener {
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;

    private static final String KOINOVOULEUTIKO_ERGO = "PLlLw1tG9H3CWmGoCIlvgCmD9zbRITix4V";
    private static final String SINANTISI = "PLlLw1tG9H3CWeAtiRC7qnZlfmyAYf3FOT";
    private static final String EPIKAIROTITA = "PLlLw1tG9H3CXF7tFZwFwNiAC-gkQ_9b_T";
    private static final String KARTA_MELOUS = "PLlLw1tG9H3CXt5EGnQmTp3yEsr-UJXrjQ";
    private static final String VOULI_EPI_EPTA = "PLlLw1tG9H3CWjHk9bp053DCCjdGwWG-7H";
    private static final String KOINOVOULEUTIKOI = "PLlLw1tG9H3CXq6w6DGq6VRPTYykpUdMYG";
    private static final String TI_LEEI_O_NOMOS = "PLlLw1tG9H3CWDXxaXtUtL3mAAAoCwKZWr";
    private static final String SYNEDRIA = "PLlLw1tG9H3CUlZvS_8yA64OP8-TqXRAV6";
    private static final String ENIMEROSI = "PLlLw1tG9H3CVfZjFoasYcUZrj7y6eBMK-";
    private static final String DRASTIRIOTITES_PROEDROU = "PLlLw1tG9H3CVik3liwiGxSn-BdwMAeHo7";
    private static final String EPITROPI_ALITHEIAS = "PLlLw1tG9H3CVOmWiQhhGnWgUqjnq1ywtT";
    private static final String SINENTEUXEIS = "PLlLw1tG9H3CXqA8CChTjcu7ye2jknJBA-";
    private static final String TETRADIA = "PLlLw1tG9H3CUmhENTop7pBJmL2t_XSWw3";
    private static final String EIDISEIS = "PLlLw1tG9H3CV6OxTKGRg-nrvWaJrPL-tW";
    private static final String AFIEROMATA = "PLlLw1tG9H3CVU9cGpXXtOkckX0MruqORl";
    private static final String EFIVOI = "PLlLw1tG9H3CWfdvCUxTG1kGJkqVBmYq65";
    private static final String PROEDRIA_EE = "PLlLw1tG9H3CXiQ6BE9XYMXvYsLGCUR0sI";
    private static final String ETOIMOTITA = "PLlLw1tG9H3CWSpGBDX8TLSV5lqE3uYCUV";
    private static final String USER_ID = "hellenicparliamenttv";
    private static final int SELECT_VIDEO_REQUEST = 1000;

    private List<TvListViewItem> intentItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_youtube);
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
        intentItems = new ArrayList<>();
        intentItems.add(new IntentItem("Κοινοβουλευτικό έργο", IntentType.KOINOVOULEUTIKO_ERGO));
        intentItems.add(new IntentItem("Συνάντηση", IntentType.SINANTISI));
        intentItems.add(new IntentItem("Εκπομπές επικαιρότητας", IntentType.EPIKAIROTITA));
        intentItems.add(new IntentItem("Κάρτα Μέλους", IntentType.KARTA_MELOUS));
        intentItems.add(new IntentItem("Βουλή επί 7", IntentType.VOULI_EPI_EPTA));
        intentItems.add(new IntentItem("Οι Κοινοβουλευτικοί - Διαμορφωτές της Σύγχρονης Ελλάδας", IntentType.KOINOVOULEUTIKOI));
        intentItems.add(new IntentItem("Τι λέει ο Νόμος", IntentType.TI_LEEI_O_NOMOS));
        intentItems.add(new IntentItem("Ημερίδες - Συνέδρια - Εκδηλώσεις", IntentType.SYNEDRIA));
        intentItems.add(new IntentItem("Βουλή - Ενημέρωση", IntentType.ENIMEROSI));
        intentItems.add(new IntentItem("Δραστηριότητες Προέδρου της Βουλής", IntentType.DRASTIRIOTITES_PROEDROU));
        intentItems.add(new IntentItem("Επιτροπή Αλήθειας Δημοσίου Χρέους", IntentType.EPITROPI_ALITHEIAS));
        intentItems.add(new IntentItem("Συνεντεύξεις", IntentType.SINENTEUXEIS));
        intentItems.add(new IntentItem("Τετράδια Κοινοβουλευτικού Λόγου", IntentType.TETRADIA));
        intentItems.add(new IntentItem("Κοινοβουλευτικές Ειδήσεις", IntentType.EIDISEIS));
        intentItems.add(new IntentItem("Αφιερώματα", IntentType.AFIEROMATA));
        intentItems.add(new IntentItem("Βουλή των Εφήβων", IntentType.EFIVOI));
        intentItems.add(new IntentItem("Ελληνική Προεδρεία στην Ε.Ε. 2014", IntentType.PROEDRIA_EE));
        intentItems.add(new IntentItem("Σε Θέση Ετοιμότητας", IntentType.ETOIMOTITA));
        intentItems.add(new IntentItem("Βουλή Τηλεόραση", IntentType.OPEN_USER));

        ListView listView = findViewById(R.id.intent_list);
        TvArrayAdapter adapter =
                new TvArrayAdapter(this, R.layout.item_youtube, intentItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        TextView youTubeVersionText = findViewById(R.id.youtube_version_text);
        String version = YouTubeIntents.getInstalledYouTubeVersionName(this);
        if (version != null) {
            String text = String.format(getString(R.string.youtube_currently_installed), version);
            youTubeVersionText.setText(text);
        } else {
            youTubeVersionText.setText(getString(R.string.youtube_not_installed));
        }
    }

    public boolean isIntentTypeEnabled(IntentType type) {
        switch (type) {
            case KOINOVOULEUTIKO_ERGO:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case SINANTISI:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case EPIKAIROTITA:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case KARTA_MELOUS:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case VOULI_EPI_EPTA:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case KOINOVOULEUTIKOI:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case TI_LEEI_O_NOMOS:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case SYNEDRIA:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case ENIMEROSI:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case DRASTIRIOTITES_PROEDROU:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case EPITROPI_ALITHEIAS:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case SINENTEUXEIS:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case TETRADIA:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case EIDISEIS:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case AFIEROMATA:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case EFIVOI:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case PROEDRIA_EE:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case ETOIMOTITA:
                return YouTubeIntents.canResolvePlayPlaylistIntent(this);
            case OPEN_USER:
                return YouTubeIntents.canResolveUserIntent(this);
        }

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IntentItem clickedIntentItem = (IntentItem) intentItems.get(position);

        Intent intent;
        switch (clickedIntentItem.type) {
            case KOINOVOULEUTIKO_ERGO:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, KOINOVOULEUTIKO_ERGO);
                startActivity(intent);
                break;
            case SINANTISI:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, SINANTISI);
                startActivity(intent);
                break;
            case EPIKAIROTITA:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, EPIKAIROTITA);
                startActivity(intent);
                break;
            case KARTA_MELOUS:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, KARTA_MELOUS);
                startActivity(intent);
                break;
            case VOULI_EPI_EPTA:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, VOULI_EPI_EPTA);
                startActivity(intent);
                break;
            case KOINOVOULEUTIKOI:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, KOINOVOULEUTIKOI);
                startActivity(intent);
                break;
            case TI_LEEI_O_NOMOS:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, TI_LEEI_O_NOMOS);
                startActivity(intent);
                break;
            case SYNEDRIA:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, SYNEDRIA);
                startActivity(intent);
                break;
            case ENIMEROSI:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, ENIMEROSI);
                startActivity(intent);
                break;
            case DRASTIRIOTITES_PROEDROU:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, DRASTIRIOTITES_PROEDROU);
                startActivity(intent);
                break;
            case EPITROPI_ALITHEIAS:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, EPITROPI_ALITHEIAS);
                startActivity(intent);
                break;
            case SINENTEUXEIS:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, SINENTEUXEIS);
                startActivity(intent);
                break;
            case TETRADIA:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, TETRADIA);
                startActivity(intent);
                break;
            case EIDISEIS:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, EIDISEIS);
                startActivity(intent);
                break;
            case AFIEROMATA:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, AFIEROMATA);
                startActivity(intent);
                break;
            case EFIVOI:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, EFIVOI);
                startActivity(intent);
                break;
            case PROEDRIA_EE:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, PROEDRIA_EE);
                startActivity(intent);
                break;
            case ETOIMOTITA:
                intent = YouTubeIntents.createPlayPlaylistIntent(this, ETOIMOTITA);
                startActivity(intent);
                break;
            case OPEN_USER:
                intent = YouTubeIntents.createUserIntent(this, USER_ID);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent returnedIntent) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECT_VIDEO_REQUEST:
                    Intent intent = YouTubeIntents.createUploadIntent(this, returnedIntent.getData());
                    startActivity(intent);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, returnedIntent);
    }

    public Action getAction() {
        return Actions.newView("IntentsTv Page", "http://www.mobap.gr/intentstvactivity");
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

    private enum IntentType {
        KOINOVOULEUTIKO_ERGO,
        SINANTISI,
        EPIKAIROTITA,
        KARTA_MELOUS,
        VOULI_EPI_EPTA,
        KOINOVOULEUTIKOI,
        TI_LEEI_O_NOMOS,
        SYNEDRIA,
        ENIMEROSI,
        DRASTIRIOTITES_PROEDROU,
        EPITROPI_ALITHEIAS,
        SINENTEUXEIS,
        TETRADIA,
        EIDISEIS,
        AFIEROMATA,
        EFIVOI,
        PROEDRIA_EE,
        ETOIMOTITA,
        OPEN_USER,
    }

    private final class IntentItem implements TvListViewItem {

        public final String title;
        public final IntentType type;

        public IntentItem(String title, IntentType type) {
            this.title = title;
            this.type = type;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public boolean isEnabled() {
            return isIntentTypeEnabled(type);
        }

        @Override
        public String getDisabledText() {
            return getString(R.string.intent_disabled);
        }

    }

}