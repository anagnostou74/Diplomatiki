package gr.mobap.rss;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import gr.mobap.R;

public class Adapter extends BaseAdapter {

	private final List<Item> items;
	private final Context context;

	public Adapter(Context context, List<Item> items) {
		this.items = items;
		this.context = context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_rss, null);
			holder = new ViewHolder();
			holder.itemTitle = convertView.findViewById(R.id.itemTitle);
			holder.itemDescription = convertView.findViewById(R.id.itemDescription);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.itemTitle.setText(items.get(position).getTitle());
		holder.itemDescription.setText(items.get(position).getDescription());
		holder.itemDescription.setText(Html.fromHtml(items.get(position).getDescription()));
		return convertView;
	}

	static class ViewHolder {
		TextView itemTitle;
		TextView itemDescription;
	}
}
