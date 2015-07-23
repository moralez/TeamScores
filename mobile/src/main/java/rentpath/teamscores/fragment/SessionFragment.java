package rentpath.teamscores.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rentpath.teamscores.R;
import rentpath.teamscores.adapter.ScoreAdapter;
import rentpath.teamscores.model.SessionInfo;

/**
 * Created by jmoralez on 7/22/15.
 */
public class SessionFragment extends Fragment {

    private static final String TAG = SessionFragment.class.toString();
    private TextView mSubmittedScoreTV;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    private TextView mStoryTitle;
    private TextView mStoryRequester;
    private TextView mStoryLabels;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        scoring/jQaEYGyur87LtuUduajtFg.json
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.session_fragment, container, false);

        mSubmittedScoreTV = (TextView)view.findViewById(R.id.submitted_score_textview);
        mStoryTitle = (TextView)view.findViewById(R.id.story_title);
        mStoryRequester = (TextView)view.findViewById(R.id.story_requester);
        mStoryLabels = (TextView)view.findViewById(R.id.story_labels);

        final GridView scoreGridView = (GridView)view.findViewById(R.id.score_gridview);
        scoreGridView.setAdapter(new ScoreAdapter(getActivity()));

        scoreGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int scoreValue = ScoreAdapter.mScoreValues[position];
                if ((scoreValue == Integer.MIN_VALUE) ||
                    (scoreValue == Integer.MAX_VALUE)) {
                    mSubmittedScoreTV.setText("?");
                } else {
                    mSubmittedScoreTV.setText(Integer.toString(scoreValue));
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new SessionInfoTask().execute();
    }

    public class SessionInfoTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            String url = "http://teamscor.es/scoring/jQaEYGyur87LtuUduajtFg.json";

            Request request = new Request.Builder()
                    .url(url).get().build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(final String response) {
//            showProgress(false);

            if (response.length() > 0) {
                Log.i(TAG, response);

                String blah = "{\n" +
                        "   \"session\":{\n" +
                        "      \"id\":1,\n" +
                        "      \"session_key\":\"jQaEYGyur87LtuUduajtFg\",\n" +
                        "      \"pivotal_project_id\":1054874,\n" +
                        "      \"pivotal_project_label\":\"cr3\",\n" +
                        "      \"password\":\"ULeRQCX8uVfEViRfdJxz2g\",\n" +
                        "      \"current_story_id\":98673690,\n" +
                        "      \"pivotal_project_name\":\"Mobile Pod C\"\n" +
                        "   },\n" +
                        "   \"story\":{\n" +
                        "      \"id\":98673690,\n" +
                        "      \"url\":\"https://www.pivotaltracker.com/story/show/98673690\",\n" +
                        "      \"project_id\":1054874,\n" +
                        "      \"name\":\"Adding Star Ratings to Listed properties on SRP and Map View\",\n" +
                        "      \"labels\":\"ag-ios,cr3,rent-ios\",\n" +
                        "      \"requested_by\":\"Dustin Guinee\"\n" +
                        "   }\n" +
                        "}";
                Gson gson = new GsonBuilder().create();
                final SessionInfo sessionInfo = gson.fromJson(blah, SessionInfo.class);
                Log.i(TAG, sessionInfo.toString());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mStoryTitle.setText(sessionInfo.getStory().getName());
                        mStoryRequester.setText(sessionInfo.getStory().getRequested_by());
                        mStoryLabels.setText(sessionInfo.getStory().getLabels());
                    }
                });
            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
//            showProgress(false);
        }
    }
}
