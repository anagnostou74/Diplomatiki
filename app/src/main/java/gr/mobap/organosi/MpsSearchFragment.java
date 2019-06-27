package gr.mobap.organosi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import gr.mobap.AndroidNetworkUtility;
import gr.mobap.R;
import gr.mobap.mps.ListViewAdapter;
import gr.mobap.mps.MpsData;
import gr.mobap.mps.MpsImageLoader;

public class MpsSearchFragment extends Fragment {
    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    MpsImageLoader mpsImageLoader = new MpsImageLoader(getActivity());
    private List<MpsData> worldpopulationlist = null;

    public MpsSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_search_mps, container, false);

        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(getActivity())) {
            // Execute RemoteDataTask AsyncTask
            new RemoteDataTask().execute("ΕΠΙΚΡΑΤΕΙΑΣ");
        } else {
            Toast.makeText(getActivity(), getString(R.string.aneu_diktiou),
                    Toast.LENGTH_SHORT).show();
        }
        // This will get the radiogroup
        RadioGroup rGroup = view.findViewById(R.id.radio);
        // This will get the radiobutton in the radiogroup that is checked
        RadioButton checkedRadioButton = rGroup.findViewById(rGroup.getCheckedRadioButtonId());
        //checkedRadioButton.setChecked(true);
        // This overrides the radiogroup onCheckListener
        rGroup.setOnCheckedChangeListener((rGroup1, checkedId) -> {
            // This will get the radiobutton that has changed in its check state
            RadioButton checkedRadioButton1 = rGroup1.findViewById(checkedId);
            // This puts the value (true/false) into the variable
            boolean isChecked = checkedRadioButton1.isChecked();
            // If the radiobutton that has changed in check state is now checked...
            if (isChecked) {
                String radiovalue = ((RadioButton) getView().findViewById(rGroup1.getCheckedRadioButtonId())).getText().toString();
                new RemoteDataTask().execute(radiovalue);
            }
        });
        return view;
    }

    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Παρακαλώ, περιμένετε...");
            // Set progressdialog message
            mProgressDialog.setMessage("Φορτώνει η σελίδα");
            mProgressDialog.setIndeterminate(true);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            String s = params[0]; // here's youre string
            worldpopulationlist = new ArrayList<>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<>("mps");
                // Locate the column named "ranknum" in Parse.com and order list
                // by ascending
                query.orderByAscending("Epitheto");
                query.whereContains("Perifereia", s);
                ob = query.find();
                for (ParseObject mps : ob) {
                    // Locate images in flag column
                    ParseFile imageLoader = (ParseFile) mps.get("Image");
                    MpsData map = new MpsData();
                    map.setRank((String) mps.get("Rank"));
                    map.setEpitheto((String) mps.get("Epitheto"));
                    map.setOnoma((String) mps.get("Onoma"));
                    map.setOnomaPatros((String) mps.get("OnomaPatros"));
                    map.setTitlos((String) mps.get("Titlos"));
                    map.setGovPosition((String) mps.get("GovPosition"));
                    map.setKomma((String) mps.get("Komma"));
                    map.setPerifereia((String) mps.get("Perifereia"));
                    map.setBirth((String) mps.get("Birth"));
                    map.setFamily((String) mps.get("Family"));
                    map.setEpaggelma((String) mps.get("Epaggelma"));
                    map.setParliamentActivities((String) mps.get("ParliamentActivities"));
                    map.setSocialActivities((String) mps.get("SocialActivities"));
                    map.setSpoudes((String) mps.get("Spoudes"));
                    map.setAddress((String) mps.get("Address"));
                    map.setSite((String) mps.get("Site"));
                    map.setEmail((String) mps.get("Email"));
                    map.setPhone((String) mps.get("Phone"));
                    map.setFlag(imageLoader.getUrl());
                    worldpopulationlist.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_mpsxml
            listview = getView().findViewById(R.id.search);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(getActivity(), worldpopulationlist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

}