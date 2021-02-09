package gr.mobap.ekdoseis;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class PraktikaFragment extends EkdoseisListFragment {

    public PraktikaFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("ekdoseis").orderByChild("type").startAt("Ευρετήρια").endAt("Ευρετήρια");
    }
}
