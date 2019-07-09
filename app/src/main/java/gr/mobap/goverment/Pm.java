package gr.mobap.goverment;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import gr.mobap.R;
import gr.mobap.mps.MpsAdapter;
import gr.mobap.mps.MpsData;
import gr.mobap.mps.MpsListFragment;
import gr.mobap.mps.MpsViewHolder;

public class Pm extends MpsListFragment {
    public String TAG = getClass().getSimpleName();
    private DatabaseReference mDatabase;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    private FirebaseRecyclerAdapter<MpsData, MpsViewHolder> mAdapter;
    private TextView president;

    public Pm() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_pr, container, false);

        mRecycler = rootView.findViewById(R.id.mpsList);
        mRecycler.setHasFixedSize(true);
        // Initialize Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        president = rootView.findViewById(R.id.president);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            president.setText(Html.fromHtml(getString(R.string.pm_aksioma), Html.FROM_HTML_MODE_COMPACT));
        } else {
            president.setText(Html.fromHtml(getString(R.string.pm_aksioma)));
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        // [END create_database_reference]

        // Set up Layout Manager
        mManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query mpsQuery = getQuery(mDatabase);
        Log.d(TAG, "Value is: " + mpsQuery);

        FirebaseRecyclerOptions<MpsData> options = new FirebaseRecyclerOptions.Builder<MpsData>()
                .setQuery(mpsQuery, MpsData.class)
                .build();

        mAdapter = new MpsAdapter(options, getContext());
        mRecycler.setAdapter(mAdapter);
        mAdapter.startListening();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("goverment").orderByChild("govPosition").equalTo("ΠΡΩΘΥΠΟΥΡΓΟΣ");
    }
}