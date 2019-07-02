package gr.mobap.mps;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class AllMpsFragment extends MpsListFragment {
    public String TAG = getClass().getSimpleName();

    public AllMpsFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("mps");
    }
}
