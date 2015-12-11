package gr.sextreme;
// http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8
// http://streamer-cache.grnet.gr/parliament/hls/webtv.m3u8
// http://streamer-cache.grnet.gr/parliament/hls/webtv2.m3u8

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.video);
        VideoView vidView = (VideoView) findViewById(R.id.videoView);
        String vidAddress1 = "http://streamer-cache.grnet.gr/parliament/hls/webtv.m3u8";
        Uri vidUri = Uri.parse(vidAddress1);
        vidView.setVideoURI(vidUri);
        MediaController vidControl = new MediaController(VideoActivity.this); /* Video controls */
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);
        vidView.start();

    }
}