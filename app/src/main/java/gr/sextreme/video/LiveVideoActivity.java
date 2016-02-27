package gr.sextreme.video;
// http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8
// http://streamer-cache.grnet.gr/parliament/hls/webtv.m3u8
// http://streamer-cache.grnet.gr/parliament/hls/webtv2.m3u8

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.devbrackets.android.exomedia.EMVideoView;

import gr.sextreme.MainActivity;
import gr.sextreme.R;

public class LiveVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.content_video);

        EMVideoView emVideoView = (EMVideoView) findViewById(R.id.video_play_activity_video_view);
        emVideoView.setVideoURI(Uri.parse("http://streamer-cache.grnet.gr/parliament/hls/webtv.m3u8"));
        emVideoView.setDefaultControlsEnabled(true);
        emVideoView.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}