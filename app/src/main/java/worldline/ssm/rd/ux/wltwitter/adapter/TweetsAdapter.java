package worldline.ssm.rd.ux.wltwitter.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;
import worldline.ssm.rd.ux.wltwitter.async.DownloadImageAsyncTask;
import worldline.ssm.rd.ux.wltwitter.components.ImageMemoryCache;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by cassar on 17/12/15.
 */
public class TweetsAdapter extends BaseAdapter {

    private List<Tweet> mTweets;
    private LayoutInflater mInflater;
    private final ImageMemoryCache mImageMemoryCache;


    public TweetsAdapter(List<Tweet> tweets) {
        this.mTweets = tweets;
        mInflater = LayoutInflater.from(WLTwitterApplication.getContext());

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemory /16;
        mImageMemoryCache = new ImageMemoryCache(cacheSize);
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
        ViewHolder holder;
        if(convertView == null){
            convertView=mInflater.inflate(R.layout.custom_tweet_layout, null);

            // Instantiate the ViewHolder
            holder = new ViewHolder(convertView);

            // Set as tag to the convertView to retrieve it easily
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
//        final View view = mInflater.inflate(R.layout.custom_tweet_layout, null);
        final Tweet tweet = (Tweet) getItem(position);

        holder.text.setText(tweet.text);

        holder.alias.setText(tweet.user.screenName);

        holder.name.setText(tweet.user.name);

        final Bitmap image = mImageMemoryCache.getBitmapFromMemoryCache(tweet.user.profileImageUrl);

        if (image == null){
            new DownloadImageAsyncTask(holder.image, mImageMemoryCache).execute(tweet.user.profileImageUrl);
        }else {
            holder.image.setImageBitmap(image);
        }


        return convertView;

    }

    private class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView alias;
        public TextView text;
        public Button button;

        public ViewHolder(View convertView) {
            image = (ImageView) convertView.findViewById(R.id.imageProfil);
            name = (TextView) convertView.findViewById(R.id.tweetListItemNameTextView);
            alias = (TextView) convertView.findViewById(R.id.tweetListItemAliasTextView);
            text = (TextView) convertView.findViewById(R.id.tweetListItemTextView);
            button = (Button) convertView.findViewById(R.id.button2);


        }
    }
}
