package gr.mobap.komma;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import gr.mobap.mps.MpsListFragment;

public class ProedrosFragment extends MpsListFragment {
    public String TAG = getClass().getSimpleName();

    public ProedrosFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("mps").orderByChild("titlos").equalTo("ΠΡΟΕΔΡΟΣ ΤΗΣ ΒΟΥΛΗΣ");
    }
}