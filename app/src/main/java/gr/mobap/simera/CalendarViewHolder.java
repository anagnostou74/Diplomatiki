package gr.mobap.simera;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import gr.mobap.R;

public class CalendarViewHolder extends RecyclerView.ViewHolder {
    public TextView dateTxt;
    public TextView timeTxt;
    public TextView typeTxt;
    public TextView placeTxt;
    public TextView detailsTxt;
    public ImageView calImageTxt;
    public String TAG = getClass().getSimpleName();

    public CalendarViewHolder(@NonNull View itemView) {
        super(itemView);

        dateTxt = itemView.findViewById(R.id.dateTxtView);
        timeTxt = itemView.findViewById(R.id.timeTxtView);
        typeTxt = itemView.findViewById(R.id.typeTxtView);
        placeTxt = itemView.findViewById(R.id.placeTxtView);
        detailsTxt = itemView.findViewById(R.id.detailsTxtView);
        calImageTxt = itemView.findViewById(R.id.cal_imageTxtView);
        Log.i(TAG, String.valueOf(dateTxt));

    }
}
