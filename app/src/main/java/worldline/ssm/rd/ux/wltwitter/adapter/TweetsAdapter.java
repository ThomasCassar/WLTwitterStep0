package worldline.ssm.rd.ux.wltwitter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by cassar on 17/12/15.
 */
public class TweetsAdapter extends BaseAdapter {

    private List<Tweet> mTweets;
    LayoutInflater mInflater = LayoutInflater.from(WLTwitterApplication.getContext());


    public TweetsAdapter(List<Tweet> tweets) {
        mTweets = tweets;
    }

    @Override
    public int getCount() {
        return null != mTweets ? mTweets.size(): 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mTweets ? mTweets.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.custom_tweet_layout, null);
        final Tweet tweet = (Tweet) getItem(position);
        final TextView userName = (TextView) view.findViewById(R.id.tweetListItemNameTextView);
        userName.setText(tweet.user.name);
        final TextView userAlias =(TextView) view.findViewById(R.id.tweetListItemAliasTextView);
        userAlias.setText("@" + tweet.user.screenName);
        final TextView text = (TextView) view.findViewById(R.id.tweetListItemTextView);
        text.setText(tweet.text);
        return view;

    }
}
