package rentpath.teamscores.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import rentpath.teamscores.R;
import rentpath.teamscores.fragment.SessionFragment;

/**
 * Created by jmoralez on 7/22/15.
 */
public class SessionActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        Bundle sessionInfo = new Bundle();

        SessionFragment sessionFragment = new SessionFragment();
        sessionFragment.setArguments(sessionInfo);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.session_content, sessionFragment);
        ft.commit();
    }
}
