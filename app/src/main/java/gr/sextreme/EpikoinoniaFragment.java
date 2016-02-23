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

public class EpikoinoniaFragment extends Fragment {

    public EpikoinoniaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_epikoinonia,
                container, false);


        Button btnKlisi = (Button) view.findViewById(R.id.btnKlisi);
        btnKlisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                klisi();
            }
        });

        Button btnMail = (Button) view.findViewById(R.id.btnMail);
        btnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail();
            }
        });
        return view;
    }

    private void klisi() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:21037070000"));
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e(getString(R.string.klisi_java), getString(R.string.failed),
                    activityException);
        }
    }

    private void mail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"infopar@parliament.gr"});
        i.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.epikoinonia_mail));
        i.putExtra(Intent.EXTRA_TEXT, getString(R.string.main_epikoinonia));
        try {
            startActivity(Intent.createChooser(i,
                    getString(R.string.apostoli)));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), getString(R.string.aneu),
                    Toast.LENGTH_SHORT).show();
        }
    }
}