package gr.sextreme.organosi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gr.sextreme.R;

public class GrammateisFragment extends Fragment {

    public GrammateisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String htmlText = getResources().getString(R.string.grammateisKeim);

        View view = inflater.inflate(R.layout.fragment_keimena, container, false);

        TextView tv = (TextView) view.findViewById(R.id.textThesmos);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(Html.fromHtml(htmlText));
        // Inflate the layout for this fragment
        return view;
    }
}