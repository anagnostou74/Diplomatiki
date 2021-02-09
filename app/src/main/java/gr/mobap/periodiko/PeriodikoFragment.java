package gr.mobap.periodiko;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class PeriodikoFragment extends PeriodikoListFragment {

    public PeriodikoFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("ekdoseis").orderByChild("type").startAt("Περιοδικό").endAt("Περιοδικό");
    }
}
