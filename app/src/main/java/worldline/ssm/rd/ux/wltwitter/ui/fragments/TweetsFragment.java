package worldline.ssm.rd.ux.wltwitter.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetsFragment extends Fragment implements TweetChangeListener {

    private ListView mListView;

    private RetrieveTweetsAsyncTask mRetrieveTweetsAsyncTask;

    public TweetsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_wltwitter, container, false);

        // Get the list view
        mListView = (ListView) rootView.findViewById(R.id.tweetsListView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        final String login = Constants.Preferences.PREF_LOGIN;

        if (!TextUtils.isEmpty(login)){
            mRetrieveTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
            mRetrieveTweetsAsyncTask.execute(login);
        }

    }

    @Override
    public void onTweetRetrived(List<Tweet> tweets) {

        final ArrayAdapter<Tweet> arrayAdapter = new ArrayAdapter<Tweet>(getActivity(), android.R.layout.simple_expandable_list_item_1,tweets);
        mListView.setAdapter(arrayAdapter);

    }
}
