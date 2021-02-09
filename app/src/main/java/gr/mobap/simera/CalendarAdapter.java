package gr.mobap.simera;

import android.content.Context;
import android.text.Html;
import android.util.Log;
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

public class CalendarAdapter extends FirebaseRecyclerAdapter<CalendarData, CalendarViewHolder> {
    private final Context context;
    public String TAG = getClass().getSimpleName();
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CalendarAdapter(@NonNull FirebaseRecyclerOptions<CalendarData> options, Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull CalendarViewHolder calendarViewHolder, int position, @NonNull CalendarData calendarData) {
        final DatabaseReference postRef = getRef(position);

        int image = context.getResources().getIdentifier("ic_splash", "drawable", context.getPackageName());
        ImageView imageCalendarView = calendarViewHolder.calImageTxt;
        Log.i(TAG, String.valueOf(calendarViewHolder.dateTxt));
        Log.i(TAG, String.valueOf(calendarData.date));

        Glide.with(context)
                .load(image)
                .fitCenter()
                .placeholder(R.drawable.ic_splash)
                .into(imageCalendarView);

        calendarViewHolder.dateTxt.setText(calendarData.date);
        calendarViewHolder.typeTxt.setText(calendarData.type);
        calendarViewHolder.placeTxt.setText(calendarData.place);
        calendarViewHolder.timeTxt.setText(calendarData.time);
        calendarViewHolder.detailsTxt.setText(Html.fromHtml(
                calendarData.details));
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CalendarViewHolder(inflater.inflate(R.layout.item_calendar, parent, false));
    }
}
