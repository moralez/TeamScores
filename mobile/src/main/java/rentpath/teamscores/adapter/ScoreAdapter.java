package rentpath.teamscores.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import rentpath.teamscores.R;

/**
 * Created by jmoralez on 7/22/15.
 */
public class ScoreAdapter extends BaseAdapter {

    private Context mContext;

    // references to our images
    public static Integer[] mScoreValues = {
            Integer.MIN_VALUE,
            0, 1, 2, 3, 5,
            8, 13, 20, 40, 100,
            Integer.MAX_VALUE
    };

    public ScoreAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mScoreValues.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.score_grid_value_view, parent, false);
        } else {
            textView = (TextView) convertView;
        }

        if (position == 0) {
            textView.setText("?");
        } else if (position == (getCount() - 1)) {
            textView.setText("Delete");
        } else {
            textView.setText(mScoreValues[position].toString());
        }

        if (mScoreValues[position] <= 0) {
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.score_gray));
        } else if (mScoreValues[position] <= 5) {
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.score_green));
        } else {
            textView.setBackgroundColor(mContext.getResources().getColor(R.color.score_red));
        }

        return textView;
    }
}
