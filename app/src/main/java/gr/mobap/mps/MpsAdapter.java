package gr.mobap.mps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import gr.mobap.R;

public class MpsAdapter extends FirebaseRecyclerAdapter<MpsData, MpsViewHolder> {
    private final Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MpsAdapter(@NonNull FirebaseRecyclerOptions<MpsData> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MpsViewHolder mpsViewHolder, int position, @NonNull MpsData mpsData) {
        final DatabaseReference postRef = getRef(position);

        String url = mpsData.image;
        ImageView imageView = mpsViewHolder.mpsImage;

        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.mps)
                .into(imageView);

        mpsViewHolder.epitheto.setText(mpsData.epitheto);
        mpsViewHolder.onoma.setText(mpsData.onoma);
        mpsViewHolder.komma.setText(mpsData.komma);
        if (mpsData.titlos != null) {
            mpsViewHolder.titlos.setText(mpsData.titlos);
        } else {
            mpsViewHolder.titlos.setText(" ");
        }
        if (mpsData.govPosition != null) {
            mpsViewHolder.govPosition.setText(mpsData.govPosition);
        } else {
            mpsViewHolder.govPosition.setText(" ");
        }

        if (mpsData.perifereia != null) {
            mpsViewHolder.perifereia.setText(mpsData.perifereia);
        } else {
            mpsViewHolder.perifereia.setText(" ");
        }

        // Set click listener for the whole post view
        final String postKey = postRef.getKey();
        mpsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch PostDetailActivity
                Intent intent = new Intent(context, SingleItemView.class);
                intent.putExtra(SingleItemView.EXTRA_POST_KEY, postKey);
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MpsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MpsViewHolder(inflater.inflate(R.layout.item_mps, parent, false));
    }
}
