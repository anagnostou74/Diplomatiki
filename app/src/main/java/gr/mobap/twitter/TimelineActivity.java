package gr.mobap.twitter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.builders.Actions;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TwitterListTimeline;

import java.lang.ref.WeakReference;

import gr.mobap.AnalyticsApplication;
import gr.mobap.AndroidNetworkUtility;
import gr.mobap.Base;
import gr.mobap.MainActivity;
import gr.mobap.R;

/**
 * TimelineActivity shows a full screen timeline which is useful for screenshots.
 */
public class TimelineActivity extends Base {
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;
    final WeakReference<Activity> activityRef = new WeakReference<>(TimelineActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweetui_swipe_timeline);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
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

        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(this)) {
            // launch the app login activity when a guest user tries to favorite a Tweet
            final Callback<Tweet> actionCallback = new Callback<Tweet>() {
                @Override
                public void success(Result<Tweet> result) {
                    // Intentionally blank
                }

                @Override
                public void failure(TwitterException exception) {
                    if (exception instanceof TwitterAuthException) {
                        startActivity(TwitterCoreMainActivity.newIntent(TimelineActivity.this));
                    }
                }
            };

            final SwipeRefreshLayout swipeLayout = findViewById(R.id.swipe_layout);
            final View emptyView = findViewById(android.R.id.empty);
            final ListView listView = findViewById(android.R.id.list);
            listView.setEmptyView(emptyView);

            // Collection "Vouli from user anagnostou74"
            TwitterListTimeline timeline = new TwitterListTimeline.Builder()
                    .slugWithOwnerScreenName("vouli", "anagnostou74")
                    .build();
            TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                    .setTimeline(timeline)
                    .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                    .setOnActionCallback(actionCallback)
                    .build();
            listView.setAdapter(adapter);

            swipeLayout.setColorSchemeResources(R.color.twitter_blue, R.color.twitter_dark);

            // set custom scroll listener to enable swipe refresh layout only when at list top
            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                boolean enableRefresh = false;

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                     int totalItemCount) {
                    if (listView != null && listView.getChildCount() > 0) {
                        // check that the first item is visible and that its top matches the parent
                        enableRefresh = listView.getFirstVisiblePosition() == 0 &&
                                listView.getChildAt(0).getTop() >= 0;
                    } else {
                        enableRefresh = false;
                    }
                    swipeLayout.setEnabled(enableRefresh);
                }
            });

            // specify action to take on swipe refresh
            swipeLayout.setOnRefreshListener(() -> {
                swipeLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        swipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        swipeLayout.setRefreshing(false);
                        final Activity activity = activityRef.get();
                        if (activity != null && !activity.isFinishing()) {
                            Toast.makeText(activity, exception.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            });
        } else {
            // display error
            Toast.makeText(this, getString(R.string.aneu_diktiou),
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> {
                Intent i = new Intent(TimelineActivity.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }, 1000); // wait for 1 second
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public com.google.firebase.appindexing.Action getAction() {
        return Actions.newView("TimeLineActivity Page", "http://www.mobap.gr/timelineactivity");
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