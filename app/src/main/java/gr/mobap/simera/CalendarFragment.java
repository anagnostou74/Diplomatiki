package gr.mobap.simera;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class CalendarFragment extends CalendarListFragment {
    public String TAG = getClass().getSimpleName();

    public CalendarFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("calendar").orderByChild("date");
    }
}
