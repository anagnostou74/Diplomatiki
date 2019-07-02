package gr.mobap.organosi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import gr.mobap.mps.MpsListFragment;

public class KoinOmPrFragment extends MpsListFragment {
    public String TAG = getClass().getSimpleName();

    public KoinOmPrFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("mps").orderByChild("titlos").equalTo("Κ.Ο.");
    }
}