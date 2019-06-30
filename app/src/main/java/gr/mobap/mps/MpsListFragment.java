package gr.mobap.mps;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import gr.mobap.R;

public abstract class MpsListFragment extends Fragment {

    public String TAG = getClass().getSimpleName();

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]
    private ValueEventListener mMpsListener;
    private FirebaseRecyclerAdapter<MpsData, MpsViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public MpsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_mps, container, false);

        mRecycler = rootView.findViewById(R.id.mpsList);
        mRecycler.setHasFixedSize(true);
        // Initialize Database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query mpsQuery = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<MpsData>()
                .setQuery(mpsQuery, MpsData.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<MpsData, MpsViewHolder>(options) {

            @Override
            public MpsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new MpsViewHolder(inflater.inflate(R.layout.item_mps, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(MpsViewHolder viewHolder, int position, final MpsData model) {
                final DatabaseReference postRef = getRef(position);

                String url = "https://photoshellas.s3.amazonaws.com/" + model.image;
                ImageView imageView = viewHolder.mpsImage;

                Glide
                        .with(MpsListFragment.this)
                        .load(url)
                        .centerCrop()
                        .placeholder(R.drawable.mps)
                        .into(imageView);

                viewHolder.epitheto.setText(model.epitheto);
                viewHolder.onoma.setText(model.onoma);
                if (model.titlos != null) {
                    viewHolder.titlos.setText(model.titlos);
                } else {
                    viewHolder.titlos.setText(" ");
                }
                if (model.govPosition != null) {
                    viewHolder.govPosition.setText(model.govPosition);
                } else {
                    viewHolder.govPosition.setText(" ");
                }

                if (model.perifereia != null) {
                    viewHolder.perifereia.setText(model.perifereia);
                } else {
                    viewHolder.perifereia.setText(" ");
                }

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        Intent intent = new Intent(getActivity(), SingleItemView.class);
                        intent.putExtra(SingleItemView.EXTRA_POST_KEY, postKey);
                        startActivity(intent);
                    }
                });

            }
        };
        mAdapter.startListening();
        mRecycler.setAdapter(mAdapter);
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
