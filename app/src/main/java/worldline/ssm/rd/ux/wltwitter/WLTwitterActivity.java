package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;


public class WLTwitterActivity extends Activity  {

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creation of the subtitle
        //Get string store in intent extras
        String login = getIntent().getExtras().getString(Constants.Preferences.PREF_LOGIN);

        Intent intent = getIntent();
        TextView loginDisplay = (TextView) findViewById(R.id.HelloTexte);

        if (intent != null) {
            loginDisplay.setText("Welcome " + login + " to your Twitter App! Let's tweet!");


        }


        if(login != null){
            //Set the sub to be a String
            getActionBar().setSubtitle((login));


            //Thread
            RetrieveTweetsAsyncTask task = new RetrieveTweetsAsyncTask();
            task.execute(login);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); // Selection of item ID in the menu

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) { //Check if the ID is ok
            logout();                     //call the logout
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logout() {
        //Delete previous log in preferences

        Context context = getApplication();
        SharedPreferences prefs = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  prefs.edit();
        editor.clear();
        editor.commit();

        finish(); // Close the activity and go to the login one.
    }
}



