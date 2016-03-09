package gr.mobap.mps;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import gr.mobap.R;

public class MpsAdapter extends ArrayAdapter<MpsData> {

    Context context;
    int layoutResourceId;
    MpsData data[] = null;

    public MpsAdapter(Context context, int layoutResourceId, MpsData[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MpsDataHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new MpsDataHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.onoma);

            row.setTag(holder);
        } else {
            holder = (MpsDataHolder) row.getTag();
        }

        MpsData mps = data[position];
        holder.txtTitle.setText(mps.epitheto);

        return row;
    }

    static class MpsDataHolder {
        TextView txtTitle;
    }
}