package gr.mobap.organosi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import gr.mobap.mps.MpsListFragment;

public class GrammateisFragment extends MpsListFragment {
    public String TAG = getClass().getSimpleName();

    public GrammateisFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("mps").orderByChild("titlos").equalTo("ΓΡΑΜΜΑΤΕΑΣ ΤΗΣ ΒΟΥΛΗΣ");
    }
}
