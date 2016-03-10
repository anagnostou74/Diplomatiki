package gr.mobap.simera;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.Tracker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

import gr.mobap.R;

public class OlomeleiaFragment extends Fragment {
    private Tracker mTracker;
    private WebView webView;
    private Bundle webViewBundle;
    private ProgressDialog progress;
    String url = "http://www.hellenicparliament.gr/Nomothetiko-Ergo/dailyplan";

    public OlomeleiaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_web,
                container, false);

        webView = (WebView) ll.findViewById(R.id.webView);
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

            try {
                Document doc = Jsoup.connect(url).ignoreContentType(true).get();
                doc.outputSettings().charset("Windows-1252");
                Elements ele = doc.select("div#middlecolumnwide");
                String html = ele.toString();
                String mime = "text/html";
                String encoding = "Windows-1252";
                webView.loadData(html, mime, encoding);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            webView.restoreState(webViewBundle);
        }
        return ll;
    }

    @Override
    public void onPause() {
        super.onPause();

        webViewBundle = new Bundle();
        webView.saveState(webViewBundle);
    }
}