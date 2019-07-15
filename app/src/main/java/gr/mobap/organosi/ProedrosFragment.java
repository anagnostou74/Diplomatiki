package gr.mobap.organosi;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import gr.mobap.mps.MpsAdapter;
import gr.mobap.mps.MpsData;
import gr.mobap.mps.MpsViewHolder;

public class ProedrosFragment extends Fragment {
    public String TAG = getClass().getSimpleName();
    private DatabaseReference mDatabase;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    private FirebaseRecyclerAdapter<MpsData, MpsViewHolder> mAdapter;
    private TextView president;
    private ValueEventListener mMpsListener;

    public ProedrosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_pr, container, false);

        mRecycler = rootView.findViewById(R.id.prList);
        mRecycler.setHasFixedSize(true);
        // Initialize Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("mps");
        president = rootView.findViewById(R.id.president);
        final String htmlA = getResources().getString(R.string.proedreioKeim);
        final String htmlB = getResources().getString(R.string.proedreioKeimB);
        president.setMovementMethod(LinkMovementMethod.getInstance());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            president.setText(Html.fromHtml(htmlA + htmlB, Html.FROM_HTML_MODE_COMPACT));
        } else {
            president.setText(Html.fromHtml(htmlA + htmlB));
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference().child("mps");
        // [END create_database_reference]

        // Set up Layout Manager
        mManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query mpsQuery = mDatabase.orderByChild("titlos").equalTo("ΠΡΟΕΔΡΟΣ ΤΗΣ ΒΟΥΛΗΣ");
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

}