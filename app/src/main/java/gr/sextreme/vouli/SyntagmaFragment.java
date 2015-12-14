package gr.sextreme.vouli;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gr.sextreme.R;

public class SyntagmaFragment extends Fragment {

    public SyntagmaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_keimena, container, false);

        final String htmlText = getResources().getString(R.string.syntagmaKeim);
        TextView tv = (TextView) view.findViewById(R.id.textThesmos);
        tv.setText(Html.fromHtml(htmlText));
        // Inflate the layout for this fragment
        return view;
    }

}