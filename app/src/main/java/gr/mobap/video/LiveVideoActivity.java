package gr.mobap.video;
// http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8
// http://streamer-cache.grnet.gr/parliament/hls/webtv.m3u8
// http://streamer-cache.grnet.gr/parliament/hls/webtv2.m3u8
// http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.devbrackets.android.exomedia.EMVideoView;
import com.google.android.gms.analytics.Tracker;

import gr.mobap.AnalyticsApplication;
import gr.mobap.AndroidNetworkUtility;
import gr.mobap.MainActivity;
import gr.mobap.R;

public class LiveVideoActivity extends Activity {
    protected EMVideoView emVideoView;
    protected boolean pausedInOnStop = false;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_video);
        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(this)) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            init();
        } else {
            // display error
            Toast.makeText(this, getString(R.string.aneu_diktiou),
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(LiveVideoActivity.this, MainActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }, 1000); // wait for 1 second
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (emVideoView.isPlaying()) {
            pausedInOnStop = true;
            emVideoView.pause();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (pausedInOnStop) {
            emVideoView.start();
            pausedInOnStop = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void init() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        emVideoView = (EMVideoView) findViewById(R.id.video_play_activity_video_view);
        emVideoView.setVideoPath("http://streamer-cache.grnet.gr/parliament/hls/webtv_640_640x360/index.m3u8");
        emVideoView.setDefaultControlsEnabled(true);
        emVideoView.getBufferPercentage();
        emVideoView.getCurrentPosition();
        emVideoView.startProgressPoll();
        emVideoView.start();
    }
}