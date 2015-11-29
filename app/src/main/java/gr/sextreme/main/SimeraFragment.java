package gr.sextreme.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import gr.sextreme.R;

public class SimeraFragment extends Fragment {


    public SimeraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.fragment_simera,
                container, false);

        return ll;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

}