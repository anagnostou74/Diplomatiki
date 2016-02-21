package gr.sextreme;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by kanag on 21/Φεβ/2016.
 */

public class PeriFragment extends Fragment {
    public static String PACKAGE_NAME;

    public PeriFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_peri,
                container, false);


        Button button = (Button) view.findViewById(R.id.btnDevel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop();
            }
        });

        Button button2 = (Button) view.findViewById(R.id.btnMail);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail();
            }
        });

        Button button3 = (Button) view.findViewById(R.id.btnVathm);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });

        return view;
    }

    private void shop() {
        try {
            String url = "https://play.google.com/store/apps/developer?id=%CE%9A%CF%8E%CF%83%CF%84%CE%B1%CF%82%20%CE%91%CE%BD%CE%B1%CE%B3%CE%BD%CF%8E%CF%83%CF%84%CE%BF%CF%85\n";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (ActivityNotFoundException activityException) {
            Log.e("navigate Site", "Site failed", activityException);
        }
    }
    private void mail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"anagnostou74@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.sxetika_mail));
        i.putExtra(Intent.EXTRA_TEXT, getString(R.string.main_me));
        try {
            startActivity(Intent.createChooser(i,
                    getString(R.string.apostoli)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), getString(R.string.aneu),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getActivity(), getString(R.string.failed2), Toast.LENGTH_LONG)
                    .show();
        }
    }
}