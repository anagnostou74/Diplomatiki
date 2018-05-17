package gr.mobap.simera;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    String url = "http://www.hellenicparliament.gr/Koinovouleftikos-Elenchos/Evdomadiaio-Deltio";

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
        webView = ll.findViewById(R.id.webViewfr);

        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(getActivity())) {
            web();
        } else {
            Toast.makeText(getActivity(), getString(R.string.aneu_diktiou),
                    Toast.LENGTH_SHORT).show();
        }
        return ll;
    }

    private void web() {
        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            doc.select("#ctl00_ContentPlaceHolder1_pcrd1_lnkBack").remove();
            doc.outputSettings().charset("Windows-1252");
            Elements ele = doc.select("div#middlecolumnwide");
            String html = ele.toString();
            String mime = "text/html";
            String encoding = "Windows-1252";
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
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
                webView.loadData(html, mime, encoding);
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