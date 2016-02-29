package gr.mobap.web;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import gr.mobap.R;

public class DiafaneiaWeb extends Fragment {

    private WebView webView;
    private Bundle webViewBundle;
    private ProgressDialog progress;

    public DiafaneiaWeb() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
        if (webViewBundle == null) {
            webView.loadUrl("http://diafaneia.hellenicparliament.gr/results/?ada=&datefrom=&dateto=&sector=&freetext=&type=");
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