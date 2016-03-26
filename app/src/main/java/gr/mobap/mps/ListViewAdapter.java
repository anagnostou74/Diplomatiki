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
		TextView epitheto;
		TextView onoma;
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
			view = inflater.inflate(R.layout.listview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.rank = (TextView) view.findViewById(R.id.rank);
			holder.epitheto = (TextView) view.findViewById(R.id.country);
			holder.onoma = (TextView) view.findViewById(R.id.population);
			// Locate the ImageView in listview_item.xml
			holder.flag = (ImageView) view.findViewById(R.id.flag);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.rank.setText(worldpopulationlist.get(position).getRank());
		holder.epitheto.setText(worldpopulationlist.get(position).getEpitheto());
		holder.onoma.setText(worldpopulationlist.get(position).getOnoma());
		// Set the results into ImageView
		mpsImageLoader.DisplayImage(worldpopulationlist.get(position).getFlag(),
				holder.flag);
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass all data rank
				intent.putExtra("rank", (worldpopulationlist.get(position).getRank()));
				// Pass all data epitheto
				intent.putExtra("epitheto", (worldpopulationlist.get(position).getEpitheto()));
				// Pass all data onoma
				intent.putExtra("onoma", (worldpopulationlist.get(position).getOnoma()));
				// Pass all data flag
				intent.putExtra("flag", (worldpopulationlist.get(position).getFlag()));
				// Start SingleItemView Class
				context.startActivity(intent);
			}
		});
		return view;
	}
}