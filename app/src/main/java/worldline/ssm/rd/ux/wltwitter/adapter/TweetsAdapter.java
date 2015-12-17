package worldline.ssm.rd.ux.wltwitter.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by cassar on 17/12/15.
 */
public class TweetsAdapter extends BaseAdapter {

    private List<Tweet> mTweets;

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
        return null;
    }
}
