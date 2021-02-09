package gr.mobap.periodiko;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import gr.mobap.R;
import gr.mobap.ekdoseis.EkdoseisData;
import gr.mobap.ekdoseis.EkdoseisViewHolder;

public abstract class PeriodikoListFragment extends Fragment {

    public String TAG = getClass().getSimpleName();

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]
    private ValueEventListener mEkdoseisListener;
    private FirebaseRecyclerAdapter<EkdoseisData, EkdoseisViewHolder> mAdapter;

    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public PeriodikoListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_ekdoseis, container, false);

        mRecycler = rootView.findViewById(R.id.ekdoseisList);
        mRecycler.setHasFixedSize(true);
        // Initialize Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
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
        Query ekdoseisQuery = getQuery(mDatabase);
        Log.d(TAG, "Value is: " + ekdoseisQuery);

        FirebaseRecyclerOptions<EkdoseisData> options = new FirebaseRecyclerOptions.Builder<EkdoseisData>()
                .setQuery(ekdoseisQuery, EkdoseisData.class)
                .build();

        mAdapter = new PeriodikoAdapter(options, getContext());
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

    public abstract Query getQuery(DatabaseReference databaseReference);

}
