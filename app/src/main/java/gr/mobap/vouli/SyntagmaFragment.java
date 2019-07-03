package gr.mobap.vouli;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import gr.mobap.R;

public class SyntagmaFragment extends Fragment {

    public SyntagmaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_keimena, container, false);

        final String htmlA = getResources().getString(R.string.syntagmaKeimA);
        final String htmlB = getResources().getString(R.string.syntagmaKeimB);
        TextView tv = view.findViewById(R.id.textThesmos);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(Html.fromHtml(htmlA + htmlB));
        // Inflate the layout for this fragment
        return view;
    }

}