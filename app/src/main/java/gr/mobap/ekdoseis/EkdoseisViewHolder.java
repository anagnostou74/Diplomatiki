package gr.mobap.ekdoseis;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import gr.mobap.R;

public class EkdoseisViewHolder extends RecyclerView.ViewHolder {
    public ImageView urlImg;
    public TextView textTxt;

    public EkdoseisViewHolder(@NonNull View itemView) {
        super(itemView);

        urlImg = itemView.findViewById(R.id.ekdosi_imageView);
        textTxt = itemView.findViewById(R.id.txtView);

    }
}
