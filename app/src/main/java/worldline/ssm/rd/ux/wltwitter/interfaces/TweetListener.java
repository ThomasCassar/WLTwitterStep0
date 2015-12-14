package worldline.ssm.rd.ux.wltwitter.interfaces;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by cassar on 14/12/15.
 */
public interface TweetListener {

    public void onRetweet(Tweet tweet);
    public void onViewTweet(Tweet tweet);

}
