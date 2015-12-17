package worldline.ssm.rd.ux.wltwitter.interfaces;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by cassar on 14/12/15.
 */
public interface TweetChangeListener {
    void  onTweetRetrived(List<Tweet> tweets);
}
