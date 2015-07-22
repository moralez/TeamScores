package rentpath.teamscores.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rentpath.teamscores.R;

/**
 * Created by jmoralez on 7/22/15.
 */
public class SessionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.session_fragment, container, false);
        return view;
    }
}
