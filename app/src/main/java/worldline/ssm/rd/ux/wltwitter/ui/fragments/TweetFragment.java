package worldline.ssm.rd.ux.wltwitter.ui.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetFragment extends Fragment {


    public TweetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.fragment_wltwitter_tweet,container,false);

        final String name = getArguments().getString(Constants.Preferences.PREF_LOGIN);
        ((TextView) view.findViewById(R.id.tweetNameTextView)).setText(name);

        final String alias = getArguments().getString(Constants.Twitter.DEFAULT_USERNAME);
        ((TextView) view.findViewById(R.id.tweetAliasTextView)).setText(alias);

        final String text = getArguments().getString(Constants.Preferences.PREF_TEXTE);
        ((TextView) view.findViewById(R.id.tweetNameTextView)).setText(text);

        return view;
    }

}
