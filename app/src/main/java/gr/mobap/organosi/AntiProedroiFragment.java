package gr.mobap.organosi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import gr.mobap.R;
import gr.mobap.mps.MpsListFragment;

public class AntiProedroiFragment extends MpsListFragment {
    public String TAG = getClass().getSimpleName();

    public AntiProedroiFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("mps").child("antipr").equalTo("ΑΝΤΙΠΡΟΕΔΡΟΣ").orderByChild("antipr_rank");
    }
}
