package gr.mobap.mps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gr.mobap.R;

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    MpsImageLoader mpsImageLoader;
    private List<MpsData> worldpopulationlist = null;
    private ArrayList<MpsData> arraylist;

    public ListViewAdapter(Context context,
                           List<MpsData> worldpopulationlist) {
        this.context = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(context);
        this.arraylist = new ArrayList<MpsData>();
        this.arraylist.addAll(worldpopulationlist);
        mpsImageLoader = new MpsImageLoader(context);
    }

    public class ViewHolder {
        TextView rank;
        TextView position;
        TextView epitheto;
        TextView onoma;
        TextView onomaPatros;
        TextView titlos;
        TextView govPosition;
        TextView komma;
        TextView perifereia;
        TextView birth;
        TextView family;
        TextView epaggelma;
        TextView parliamentActivities;
        TextView socialActivities;
        TextView spoudes;
        TextView languages;
        TextView address;
        TextView site;
        TextView email;
        ImageView flag;
    }

    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public Object getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_mps_item, null);
            // Locate the TextViews in listview_mps_item.xml.xml
            holder.epitheto = (TextView) view.findViewById(R.id.epitheto);
            holder.onoma = (TextView) view.findViewById(R.id.onoma);
            holder.onomaPatros = (TextView) view.findViewById(R.id.onomaPatros);
            holder.titlos = (TextView) view.findViewById(R.id.titlos);
            holder.govPosition = (TextView) view.findViewById(R.id.govPosition);
            holder.komma = (TextView) view.findViewById(R.id.komma);
            holder.perifereia = (TextView) view.findViewById(R.id.perifereia);
            holder.birth = (TextView) view.findViewById(R.id.birth);
            holder.family = (TextView) view.findViewById(R.id.family);
            holder.epaggelma = (TextView) view.findViewById(R.id.epaggelma);
            holder.parliamentActivities = (TextView) view.findViewById(R.id.parliamentActivities);
            holder.socialActivities = (TextView) view.findViewById(R.id.socialActivities);
            holder.spoudes = (TextView) view.findViewById(R.id.spoudes);
            holder.languages = (TextView) view.findViewById(R.id.languages);
            holder.address = (TextView) view.findViewById(R.id.address);
            holder.site = (TextView) view.findViewById(R.id.site);
            holder.email = (TextView) view.findViewById(R.id.email);
            // Locate the ImageView in listview_mps_item.xml.xml
            holder.flag = (ImageView) view.findViewById(R.id.flag);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.epitheto.setText(worldpopulationlist.get(position).getEpitheto());
        holder.onoma.setText(worldpopulationlist.get(position).getOnoma());
//        holder.onomaPatros.setText(worldpopulationlist.get(position).getOnomaPatros());
        holder.titlos.setText(worldpopulationlist.get(position).getTitlos());
        holder.govPosition.setText(worldpopulationlist.get(position).getGovPosition());
//        holder.komma.setText(worldpopulationlist.get(position).getKomma());
        holder.perifereia.setText(worldpopulationlist.get(position).getPerifereia());
//        holder.birth.setText(worldpopulationlist.get(position).getBirth());
//        holder.family.setText(worldpopulationlist.get(position).getFamily());
//        holder.epaggelma.setText(worldpopulationlist.get(position).getEpaggelma());
//        holder.parliamentActivities.setText(worldpopulationlist.get(position).getParliamentActivities());
//        holder.socialActivities.setText(worldpopulationlist.get(position).getSocialActivities());
//        holder.spoudes.setText(worldpopulationlist.get(position).getSpoudes());
//        holder.languages.setText(worldpopulationlist.get(position).getLanguages());
        //       holder.address.setText(worldpopulationlist.get(position).getAddress());
//        holder.site.setText(worldpopulationlist.get(position).getSite());
//        holder.email.setText(worldpopulationlist.get(position).getEmail());
        // Set the results into ImageView
        mpsImageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
                holder.flag);
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data epitheto
                intent.putExtra("epitheto", (worldpopulationlist.get(position).getEpitheto()));
                intent.putExtra("onoma", (worldpopulationlist.get(position).getOnoma()));
                intent.putExtra("onomaPatros", (worldpopulationlist.get(position).getOnomaPatros()));
                intent.putExtra("titlos", (worldpopulationlist.get(position).getTitlos()));
                intent.putExtra("govPosition", (worldpopulationlist.get(position).getGovPosition()));
                intent.putExtra("komma", (worldpopulationlist.get(position).getKomma()));
                intent.putExtra("perifereia", (worldpopulationlist.get(position).getPerifereia()));
                intent.putExtra("birth", (worldpopulationlist.get(position).getBirth()));
                intent.putExtra("family", (worldpopulationlist.get(position).getFamily()));
                intent.putExtra("epaggelma", (worldpopulationlist.get(position).getEpaggelma()));
                intent.putExtra("parliamentActivities", (worldpopulationlist.get(position).getParliamentActivities()));
                intent.putExtra("socialActivities", (worldpopulationlist.get(position).getSocialActivities()));
                intent.putExtra("spoudes", (worldpopulationlist.get(position).getSpoudes()));
                intent.putExtra("languages", (worldpopulationlist.get(position).getLanguages()));
                intent.putExtra("address", (worldpopulationlist.get(position).getAddress()));
                intent.putExtra("site", (worldpopulationlist.get(position).getSite()));
                intent.putExtra("email", (worldpopulationlist.get(position).getEmail()));
                intent.putExtra("flag", (worldpopulationlist.get(position).getFlag()));

                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });
        return view;
    }
}