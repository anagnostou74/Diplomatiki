package gr.mobap.simera;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.analytics.Tracker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import gr.mobap.AndroidNetworkUtility;
import gr.mobap.R;

public class SimeraFragment extends Fragment {
    private Tracker mTracker;
    private WebView webView;
    private Bundle webViewBundle;
    private ProgressDialog progress;
    String url = "https://www.hellenicparliament.gr/Koinovouleftikos-Elenchos/Evdomadiaio-Deltio";
    private static final String TAG = "SimeraFragment";

    public SimeraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_web,
                container, false);
        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(getActivity())) {
            webView = ll.findViewById(R.id.webViewfr);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().supportZoom();
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            progress = ProgressDialog.show(getActivity(), "Παρακαλώ περιμένετε...",
                    "Φορτώνει η σελίδα", true);
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    if (progress != null)
                        progress.dismiss();
                }
            });
            if (webViewBundle == null) { //Κώδικας για webView save State
                webView.loadUrl(url);
            } else {
                webView.restoreState(webViewBundle);
            }

        } else {
            Toast.makeText(getActivity(), getString(R.string.aneu_diktiou),
                    Toast.LENGTH_SHORT).show();
        }
        return ll;
    }

    private void web() {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36")
                    .cookie("cookiesession1", "22258D96MK6VGC4JOO9G8MUK4FAT7DE9")
                    .get();
            doc.select("#ctl00_ContentPlaceHolder1_pcrd1_lnkBack")
                    .remove();
            doc.outputSettings().charset("UTF-8");
            Log.d(TAG, "doc:" + doc);

            Elements ele = doc.select("div#middlecolumnwide");
            String html = ele.toString();
            String mime = "text/html; charset=utf-8";
            String encoding = "UTF-8";
            Log.d(TAG, "html:" + html);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDefaultTextEncodingName("utf-8");
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().supportZoom();
            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webView.setScrollbarFadingEnabled(true);
            progress = ProgressDialog.show(getActivity(), "Παρακαλώ περιμένετε...",
                    "Φορτώνει η σελίδα", true);
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                    return false;
                }

                public void onPageFinished(WebView view, String url) {
                    if (progress != null)
                        progress.dismiss();
                }
            });
            if (webViewBundle == null) { //Κώδικας για webView save State
                webView.loadData(url, mime, encoding);
            } else {
                webView.restoreState(webViewBundle);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webView == null) {
            onDestroy();
        } else {
            webViewBundle = new Bundle();
            webView.saveState(webViewBundle);
        }
    }
}