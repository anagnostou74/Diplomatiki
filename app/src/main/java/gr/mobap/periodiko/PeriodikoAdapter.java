package gr.mobap.periodiko;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import gr.mobap.R;
import gr.mobap.ekdoseis.EkdoseisData;
import gr.mobap.ekdoseis.EkdoseisViewHolder;

public class PeriodikoAdapter extends FirebaseRecyclerAdapter<EkdoseisData, EkdoseisViewHolder> {
    private final Context context;
    public String TAG = getClass().getSimpleName();
    private String pdfURL;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PeriodikoAdapter(@NonNull FirebaseRecyclerOptions<EkdoseisData> options, Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull EkdoseisViewHolder ekdoseisViewHolder, int position, @NonNull EkdoseisData ekdoseisData) {
        final DatabaseReference postRef = getRef(position);

        String image = ekdoseisData.img_url;
        ImageView imagEkdoseisView = ekdoseisViewHolder.urlImg;

        Glide.with(context)
                .load(image)
                .fitCenter()
                .placeholder(R.drawable.ic_splash)
                .into(imagEkdoseisView);

        ekdoseisViewHolder.textTxt.setText(ekdoseisData.text);

        // Set click listener for the whole post view
        final String postKey = postRef.getKey();
        ekdoseisViewHolder.itemView.setOnClickListener(v -> {
            // Launch PostDetailActivity
            Intent intent = new Intent(context, PeriodikoActivity.class);
            intent.putExtra(PeriodikoActivity.EXTRA_POST_KEY, postKey);
            context.startActivity(intent);

        });

    }

    @NonNull
    @Override
    public EkdoseisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new EkdoseisViewHolder(inflater.inflate(R.layout.item_ekdosi, parent, false));
    }

}
