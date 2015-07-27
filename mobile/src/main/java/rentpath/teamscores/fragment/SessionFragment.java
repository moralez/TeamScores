package rentpath.teamscores.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rentpath.teamscores.R;
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
    private TextView mStoryDescription;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.session_fragment, container, false);

        mSubmittedScoreTV = (TextView)view.findViewById(R.id.submitted_score_textview);
        mStoryTitle = (TextView)view.findViewById(R.id.story_title);
        mStoryRequester = (TextView)view.findViewById(R.id.story_requester);
        mStoryLabels = (TextView)view.findViewById(R.id.story_labels);
        mStoryDescription = (TextView)view.findViewById(R.id.story_description);

        SeekBar seekBar = (SeekBar)view.findViewById(R.id.story_estimate_slider);
        seekBar.setProgress(0);
        seekBar.incrementProgressBy(1);
        seekBar.setMax(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    case 0:
                    default:
                        mSubmittedScoreTV.setText("?");
                        break;
                    case 1:
                        mSubmittedScoreTV.setText("0");
                        break;
                    case 2:
                        mSubmittedScoreTV.setText("1");
                        break;
                    case 3:
                        mSubmittedScoreTV.setText("2");
                        break;
                    case 4:
                        mSubmittedScoreTV.setText("3");
                        break;
                    case 5:
                        mSubmittedScoreTV.setText("5");
                        break;
                    case 6:
                        mSubmittedScoreTV.setText("8");
                        break;
                    case 7:
                        mSubmittedScoreTV.setText("13");
                        break;
                    case 8:
                        mSubmittedScoreTV.setText("20");
                        break;
                    case 9:
                        mSubmittedScoreTV.setText("40");
                        break;
                    case 10:
                        mSubmittedScoreTV.setText("100");
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

//            String url = "http://teamscor.es/scoring/jQaEYGyur87LtuUduajtFg.json";
//            String url = "http://teamscor.es/scoring/tV0QtgFtO-qSy--4cSWe0Q.json";
            String url = "http://teamscor.es/scoring/JVZuC1VbQBerXyGxZhDT0w.json";

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
                        "      \"requested_by\":\"Dustin Guinee\",\n" +
                        "      \"description\":\"Given that I'm on Map View or SRP\\nI need to see the properties ratings\\nSo I decide better on my selection\\n\\nAC\\n\\n- Include the star rating per listed property\\n- If there are no star ratings, then this feature should no be displayed \\n- Star assets should be 2px apart and on the same row as the beds info.\n\n\n\n\n\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"\n" +
                        "   }\n" +
                        "}";
                Gson gson = new GsonBuilder().create();
//                final SessionInfo sessionInfo = gson.fromJson(blah, SessionInfo.class);
                final SessionInfo sessionInfo = gson.fromJson(response, SessionInfo.class);
                Log.i(TAG, sessionInfo.toString());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mStoryTitle.setText(sessionInfo.getStory().getName());
                        mStoryRequester.setText(sessionInfo.getStory().getRequested_by());
                        mStoryLabels.setText(sessionInfo.getStory().getLabels());
                        mStoryDescription.setText(sessionInfo.getStory().getDescription());
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
