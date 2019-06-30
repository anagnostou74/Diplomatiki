package gr.mobap.mps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import gr.mobap.R;

public class SearchMps extends MpsListFragment {
    private FirebaseRecyclerAdapter<MpsData, MpsViewHolder> mAdapter;
    Query mpsSearchQuery;
    private FirebaseAnalytics mFirebaseAnalytics;
    private Tracker mTracker;
    private AlertDialog.Builder builder;
    private Button perifereiaButton;
    private Button kommaButton;
    private Button searchButton;
    private Button clearButton;
    private DatabaseReference mDatabase;
    public String TAG = getClass().getSimpleName();
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    FirebaseRecyclerOptions options;

    public SearchMps() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.search_mps, container, false);

        mRecycler = rootView.findViewById(R.id.mpsList);
        mRecycler.setHasFixedSize(true);
        // Initialize Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = rootView.findViewById(R.id.mpsList);
        mRecycler.setHasFixedSize(true);
        // Initialize Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        perifereiaButton = (Button) rootView.findViewById(R.id.perifereiaButton);
        perifereiaButton.setOnClickListener(this::perifereiaChoiceDialog);

        kommaButton = (Button) rootView.findViewById(R.id.kommaButton);
        kommaButton.setOnClickListener(this::kommaChoiceDialog);

        searchButton = (Button) rootView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this::searchMps);

        clearButton = (Button) rootView.findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this::clear);

        return rootView;
    }

    @Override
    public Query getQuery(DatabaseReference dbReference) {
        return dbReference.child("search_mps");
    }

    private void perifereiaChoiceDialog(View view) {
        builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.perif_label);

        final String[] items = {"Α΄ ΑΘΗΝΩΝ",
                "Α΄ ΑΝΑΤΟΛΙΚΗΣ ΑΤΤΙΚΗΣ",
                "Α΄ ΘΕΣΣΑΛΟΝΙΚΗΣ",
                "Α΄ ΠΕΙΡΑΙΩΣ",
                "ΑΙΤΩΛΟΑΚΑΡΝΑΝΙΑΣ",
                "ΑΡΓΟΛΙΔΟΣ",
                "ΑΡΚΑΔΙΑΣ",
                "ΑΡΤΗΣ",
                "ΑΧΑΪΑΣ",
                "Β΄ ΔΥΤΙΚΗΣ ΑΤΤΙΚΗΣ",
                "Β΄ ΘΕΣΣΑΛΟΝΙΚΗΣ",
                "Β΄ ΠΕΙΡΑΙΩΣ",
                "Β1΄ΒΟΡΕΙΟΥ ΤΟΜΕΑ ΑΘΗΝΩΝ",
                "Β2΄ ΔΥΤΙΚΟΥ ΤΟΜΕΑ ΑΘΗΝΩΝ",
                "Β3΄ ΝΟΤΙΟΥ ΤΟΜΕΑ ΑΘΗΝΩΝ",
                "ΒΟΙΩΤΙΑΣ",
                "ΓΡΕΒΕΝΩΝ",
                "ΔΡΑΜΑΣ",
                "ΔΩΔΕΚΑΝΗΣΟΥ",
                "ΕΒΡΟΥ",
                "ΕΠΙΚΡΑΤΕΙΑΣ",
                "ΕΥΒΟΙΑΣ",
                "ΕΥΡΥΤΑΝΙΑΣ",
                "ΖΑΚΥΝΘΟΥ",
                "ΗΛΕΙΑΣ",
                "ΗΜΑΘΙΑΣ",
                "ΗΡΑΚΛΕΙΟΥ",
                "ΘΕΣΠΡΩΤΙΑΣ",
                "ΙΩΑΝΝΙΝΩΝ",
                "ΚΑΒΑΛΑΣ",
                "ΚΑΡΔΙΤΣΗΣ",
                "ΚΑΣΤΟΡΙΑΣ",
                "ΚΕΡΚΥΡΑΣ",
                "ΚΕΦΑΛΛΗΝΙΑΣ",
                "ΚΙΛΚΙΣ",
                "ΚΟΖΑΝΗΣ",
                "ΚΟΡΙΝΘΙΑΣ",
                "ΚΥΚΛΑΔΩΝ",
                "ΛΑΚΩΝΙΑΣ",
                "ΛΑΡΙΣΗΣ",
                "ΛΑΣΙΘΙΟΥ",
                "ΛΕΣΒΟΥ",
                "ΛΕΥΚΑΔΟΣ",
                "ΜΑΓΝΗΣΙΑΣ",
                "ΜΕΣΣΗΝΙΑΣ",
                "ΞΑΝΘΗΣ",
                "ΠΕΛΛΗΣ",
                "ΠΙΕΡΙΑΣ",
                "ΠΡΕΒΕΖΗΣ",
                "ΡΕΘΥΜΝΗΣ",
                "ΡΟΔΟΠΗΣ",
                "ΣΑΜΟΥ",
                "ΣΕΡΡΩΝ",
                "ΤΡΙΚΑΛΩΝ",
                "ΦΘΙΩΤΙΔΟΣ",
                "ΦΛΩΡΙΝΗΣ",
                "ΦΩΚΙΔΟΣ",
                "ΧΑΛΚΙΔΙΚΗΣ",
                "ΧΑΝΙΩΝ",
                "ΧΙΟΥ"

        };
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                perifereiaButton.setText(items[i]);
                dialogInterface.dismiss();
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void kommaChoiceDialog(View view) {
        builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.komma);

        final String[] items = {"ΝΕΑ ΔΗΜΟΚΡΑΤΙΑ",
                "ΣΥΝΑΣΠΙΣΜΟΣ ΡΙΖΟΣΠΑΣΤΙΚΗΣ ΑΡΙΣΤΕΡΑΣ",
                "ΔΗΜΟΚΡΑΤΙΚΗ ΣΥΜΠΑΡΑΤΑΞΗ",
                "ΚΟΜΜΟΥΝΙΣΤΙΚΟ ΚΟΜΜΑ ΕΛΛΑΔΑΣ",
        };

        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                kommaButton.setText(items[i]);
                dialogInterface.dismiss();
            }
        });

        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void searchMps(View view) {

        String perifereia = getString(R.string.perif_label);
        String komma = getString(R.string.komma);

        if (perifereia == perifereiaButton.getText()) {
            if (komma == kommaButton.getText()) {
                Toast.makeText(getContext(), "Παρακαλώ κάνετε μία επιλογή", Toast.LENGTH_SHORT).show();
            } else {
                Query perifereiaQuery = getQuery(mDatabase).orderByChild("perifereia").equalTo(String.valueOf(perifereiaButton.getText()));

                options = new FirebaseRecyclerOptions.Builder<MpsData>()
                        .setQuery(perifereiaQuery, MpsData.class)
                        .build();

                mAdapter = new FirebaseRecyclerAdapter<MpsData, MpsViewHolder>(options) {

                    @Override
                    public MpsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                        return new MpsViewHolder(inflater.inflate(R.layout.item_mps, viewGroup, false));
                    }

                    @Override
                    protected void onBindViewHolder(MpsViewHolder viewHolder, int position, final MpsData model) {
                        final DatabaseReference postRef = getRef(position);

                        String url = "https://photoshellas.s3.amazonaws.com/" + model.image;
                        ImageView imageView = viewHolder.mpsImage;

                        Glide
                                .with(SearchMps.this)
                                .load(url)
                                .centerCrop()
                                .placeholder(R.drawable.mps)
                                .into(imageView);

                        viewHolder.epitheto.setText(model.epitheto);
                        viewHolder.onoma.setText(model.onoma);
                        if (model.titlos != null) {
                            viewHolder.titlos.setText(model.titlos);
                        } else {
                            viewHolder.titlos.setText(" ");
                        }
                        if (model.govPosition != null) {
                            viewHolder.govPosition.setText(model.govPosition);
                        } else {
                            viewHolder.govPosition.setText(" ");
                        }

                        if (model.perifereia != null) {
                            viewHolder.perifereia.setText(model.perifereia);
                        } else {
                            viewHolder.perifereia.setText(" ");
                        }

                        // Set click listener for the whole post view
                        final String postKey = postRef.getKey();
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Launch PostDetailActivity
                                Intent intent = new Intent(getActivity(), SingleItemView.class);
                                intent.putExtra(SingleItemView.EXTRA_POST_KEY, postKey);
                                startActivity(intent);
                            }
                        });

                    }
                };
                mAdapter.startListening();
                mRecycler.setAdapter(mAdapter);
            }
        } else if (komma == kommaButton.getText()) {
            if (perifereia == perifereiaButton.getText()) {
                Toast.makeText(getContext(), "Παρακαλώ κάνετε μία επιλογή", Toast.LENGTH_SHORT).show();
            } else {
                Query kommaQuery = getQuery(mDatabase).orderByChild("perifereia").equalTo(String.valueOf(perifereiaButton.getText()));

                options = new FirebaseRecyclerOptions.Builder<MpsData>()
                        .setQuery(kommaQuery, MpsData.class)
                        .build();
                mAdapter = new FirebaseRecyclerAdapter<MpsData, MpsViewHolder>(options) {

                    @Override
                    public MpsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                        return new MpsViewHolder(inflater.inflate(R.layout.item_mps, viewGroup, false));
                    }

                    @Override
                    protected void onBindViewHolder(MpsViewHolder viewHolder, int position, final MpsData model) {
                        final DatabaseReference postRef = getRef(position);

                        String url = "https://photoshellas.s3.amazonaws.com/" + model.image;
                        ImageView imageView = viewHolder.mpsImage;

                        Glide
                                .with(SearchMps.this)
                                .load(url)
                                .centerCrop()
                                .placeholder(R.drawable.mps)
                                .into(imageView);

                        viewHolder.epitheto.setText(model.epitheto);
                        viewHolder.onoma.setText(model.onoma);
                        if (model.titlos != null) {
                            viewHolder.titlos.setText(model.titlos);
                        } else {
                            viewHolder.titlos.setText(" ");
                        }
                        if (model.govPosition != null) {
                            viewHolder.govPosition.setText(model.govPosition);
                        } else {
                            viewHolder.govPosition.setText(" ");
                        }

                        if (model.perifereia != null) {
                            viewHolder.perifereia.setText(model.perifereia);
                        } else {
                            viewHolder.perifereia.setText(" ");
                        }

                        // Set click listener for the whole post view
                        final String postKey = postRef.getKey();
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Launch PostDetailActivity
                                Intent intent = new Intent(getActivity(), SingleItemView.class);
                                intent.putExtra(SingleItemView.EXTRA_POST_KEY, postKey);
                                startActivity(intent);
                            }
                        });

                    }
                };
                mAdapter.startListening();
                mRecycler.setAdapter(mAdapter);
            }
        } else {
            Query bothQuery = getQuery(mDatabase).orderByChild("perifereia_komma").equalTo(String.valueOf(perifereiaButton.getText() + " " + String.valueOf(kommaButton.getText())));

            options = new FirebaseRecyclerOptions.Builder<MpsData>()
                    .setQuery(bothQuery, MpsData.class)
                    .build();
            mAdapter = new FirebaseRecyclerAdapter<MpsData, MpsViewHolder>(options) {

                @Override
                public MpsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                    LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                    return new MpsViewHolder(inflater.inflate(R.layout.item_mps, viewGroup, false));
                }

                @Override
                protected void onBindViewHolder(MpsViewHolder viewHolder, int position, final MpsData model) {
                    final DatabaseReference postRef = getRef(position);

                    String url = "https://photoshellas.s3.amazonaws.com/" + model.image;
                    ImageView imageView = viewHolder.mpsImage;

                    Glide
                            .with(SearchMps.this)
                            .load(url)
                            .centerCrop()
                            .placeholder(R.drawable.mps)
                            .into(imageView);

                    viewHolder.epitheto.setText(model.epitheto);
                    viewHolder.onoma.setText(model.onoma);
                    if (model.titlos != null) {
                        viewHolder.titlos.setText(model.titlos);
                    } else {
                        viewHolder.titlos.setText(" ");
                    }
                    if (model.govPosition != null) {
                        viewHolder.govPosition.setText(model.govPosition);
                    } else {
                        viewHolder.govPosition.setText(" ");
                    }

                    if (model.perifereia != null) {
                        viewHolder.perifereia.setText(model.perifereia);
                    } else {
                        viewHolder.perifereia.setText(" ");
                    }

                    // Set click listener for the whole post view
                    final String postKey = postRef.getKey();
                    viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Launch PostDetailActivity
                            Intent intent = new Intent(getActivity(), SingleItemView.class);
                            intent.putExtra(SingleItemView.EXTRA_POST_KEY, postKey);
                            startActivity(intent);
                        }
                    });

                }
            };
            mAdapter.startListening();
            mRecycler.setAdapter(mAdapter);
        }
    }

    private void clear(View view) {
        perifereiaButton.setText(R.string.perif_label);
        kommaButton.setText(R.string.komma);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query mpsQuery = getQuery(mDatabase);

        options = new FirebaseRecyclerOptions.Builder<MpsData>()
                .setQuery(mpsQuery, MpsData.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<MpsData, MpsViewHolder>(options) {

            @Override
            public MpsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new MpsViewHolder(inflater.inflate(R.layout.item_mps, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(MpsViewHolder viewHolder, int position, final MpsData model) {
                final DatabaseReference postRef = getRef(position);

                String url = "https://photoshellas.s3.amazonaws.com/" + model.image;
                ImageView imageView = viewHolder.mpsImage;

                Glide
                        .with(SearchMps.this)
                        .load(url)
                        .centerCrop()
                        .placeholder(R.drawable.mps)
                        .into(imageView);

                viewHolder.epitheto.setText(model.epitheto);
                viewHolder.onoma.setText(model.onoma);
                if (model.titlos != null) {
                    viewHolder.titlos.setText(model.titlos);
                } else {
                    viewHolder.titlos.setText(" ");
                }
                if (model.govPosition != null) {
                    viewHolder.govPosition.setText(model.govPosition);
                } else {
                    viewHolder.govPosition.setText(" ");
                }

                if (model.perifereia != null) {
                    viewHolder.perifereia.setText(model.perifereia);
                } else {
                    viewHolder.perifereia.setText(" ");
                }

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        Intent intent = new Intent(getActivity(), SingleItemView.class);
                        intent.putExtra(SingleItemView.EXTRA_POST_KEY, postKey);
                        startActivity(intent);
                    }
                });

            }
        };
        mAdapter.startListening();
        mRecycler.setAdapter(mAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mAdapter != null) {
            mAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAdapter != null) {
            mAdapter.stopListening();
        }
    }

}
