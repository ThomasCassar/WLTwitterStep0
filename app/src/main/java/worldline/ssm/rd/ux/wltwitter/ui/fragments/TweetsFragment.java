package worldline.ssm.rd.ux.wltwitter.ui.fragments;


import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class TweetsFragment extends Fragment implements TweetChangeListener, AdapterView.OnItemClickListener {

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

        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);

        mListView.setEmptyView(progressBar);

        ViewGroup root = (ViewGroup) rootView.findViewById(R.id.tweetRootRelativeLayout);
        root.addView(progressBar);

        mListView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        final String login = Constants.Preferences.PREF_LOGIN;

        if (!TextUtils.isEmpty(login)) {
            mRetrieveTweetsAsyncTask = new RetrieveTweetsAsyncTask(this);
            mRetrieveTweetsAsyncTask.execute(login);
        }

    }

    @Override
    public void onTweetRetrived(List<Tweet> tweets) {

        final ArrayAdapter<Tweet> arrayAdapter = new ArrayAdapter<Tweet>(getActivity(), android.R.layout.simple_expandable_list_item_1, tweets);
        mListView.setAdapter(arrayAdapter);

    }

    // Keep a reference to our activity;

    private TweetListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof TweetListener) {
            mListener = (TweetListener) activity;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final Tweet tweet = (Tweet) parent.getItemAtPosition(position);

        if (mListener != null) {
            mListener.onViewTweet(tweet);
        }
    }


}
