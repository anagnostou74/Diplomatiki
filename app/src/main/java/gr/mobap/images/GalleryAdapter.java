package gr.mobap.images;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import gr.mobap.R;

public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<ImageModel> data = new ArrayList<>();

    public GalleryAdapter(Context context, List<ImageModel> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.content_images, parent, false);
        viewHolder = new MyItemHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Glide.with(context).load(data.get(position).getUrl())
                .thumbnail(0.5f)
                .apply(new RequestOptions()
                        .override(200, 200)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(new DrawableTransitionOptions().crossFade())
                .into(((MyItemHolder) holder).mImg);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder {
        ImageView mImg;


        public MyItemHolder(View itemView) {
            super(itemView);

            mImg = itemView.findViewById(R.id.item_img);
        }

    }


}
