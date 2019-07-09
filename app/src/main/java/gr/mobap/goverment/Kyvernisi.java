package gr.mobap.goverment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import gr.mobap.mps.MpsListFragment;

public class Kyvernisi extends MpsListFragment {
    public String TAG = getClass().getSimpleName();

    public Kyvernisi() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("goverment").orderByChild("govRank");
    }
}
