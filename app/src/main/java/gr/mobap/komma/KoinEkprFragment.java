package gr.mobap.komma;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import gr.mobap.R;
import gr.mobap.mps.MpsListFragment;

public class KoinEkprFragment extends MpsListFragment {
    public String TAG = getClass().getSimpleName();

    public KoinEkprFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("mps").orderByChild("titlos").equalTo("Κοινοβουλευτικός Εκπρόσωπος");
    }
}
