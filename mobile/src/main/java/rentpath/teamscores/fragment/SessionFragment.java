package rentpath.teamscores.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import rentpath.teamscores.R;
import rentpath.teamscores.adapter.ScoreAdapter;

/**
 * Created by jmoralez on 7/22/15.
 */
public class SessionFragment extends Fragment {

    private TextView mSubmittedScoreTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.session_fragment, container, false);

        mSubmittedScoreTV = (TextView)view.findViewById(R.id.submitted_score_textview);

        final GridView scoreGridView = (GridView)view.findViewById(R.id.score_gridview);
        scoreGridView.setAdapter(new ScoreAdapter(getActivity()));

        scoreGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();

                int scoreValue = ScoreAdapter.mScoreValues[position];
                if ((scoreValue == Integer.MIN_VALUE) ||
                    (scoreValue == Integer.MAX_VALUE)) {
                    mSubmittedScoreTV.setText("?");
                } else {
                    mSubmittedScoreTV.setText(Integer.toString(scoreValue));
                }

                mSubmittedScoreTV.setText(Integer.toString(ScoreAdapter.mScoreValues[position]));
            }
        });

        return view;
    }
}
