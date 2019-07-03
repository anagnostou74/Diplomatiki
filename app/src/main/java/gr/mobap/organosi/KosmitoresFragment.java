package gr.mobap.organosi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import gr.mobap.mps.MpsListFragment;

public class KosmitoresFragment extends MpsListFragment {
    public String TAG = getClass().getSimpleName();

    public KosmitoresFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("mps").orderByChild("titlos").equalTo("ΚΟΣΜΗΤΟΡΑΣ ΤΗΣ ΒΟΥΛΗΣ");
    }
}