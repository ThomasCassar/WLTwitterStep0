package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;


import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
/**
 * Created by cassar on 10/12/15.
 */

public class RetrieveTweetsAsyncTask extends AsyncTask<String, Integer, List<Tweet>> {

    public String appName = "TestTweet";

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        if(tweets != null){
            super.onPostExecute(tweets);
            int i;
            for ( i=0 ; i< tweets.size() ; i++) {
                System.out.println("[" +appName+"]" + tweets.get(i).text);
            }
        }else{
            Log.e("AsyncTask", "Pas de tweets");
        }

    }

    @Override
    protected List<Tweet> doInBackground(String... params) {
        Log.d("AsyncTask", "Démarrage async task");
        if (TextUtils.isEmpty(params[0])){
            return null;
        }
        else{
            return TwitterHelper.getTweetsOfUser(params[0]);
        }


    }
}
