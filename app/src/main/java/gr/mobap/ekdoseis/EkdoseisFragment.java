package gr.mobap.ekdoseis;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class EkdoseisFragment extends EkdoseisListFragment {

    public EkdoseisFragment() {
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {

        return dbReference.child("ekdoseis").orderByChild("type").startAt("Εκδόσεις").endAt("Εκδόσεις");


    }
}
