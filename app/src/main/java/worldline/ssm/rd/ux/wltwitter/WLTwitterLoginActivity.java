package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.utils.Constants;


/**
 * Created by cassar on 03/12/15.
 */
public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Create instance
        super.onCreate(savedInstanceState);
        //Display layout
        setContentView(R.layout.layout);

        //Action when click
        findViewById(R.id.button).setOnClickListener(this);

        //Launch main activity if login and password are not NULL
        Context context = getApplication();
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        if ((sharedPref.getString(Constants.Preferences.PREF_LOGIN, "null") != "null") && (sharedPref.getString(Constants.Preferences.PREF_PASSWORD, "null") != "null")) {
            Intent intent = new Intent(getApplicationContext(), WLTwitterActivity.class);
            Bundle extras = new Bundle();
            extras.putString(Constants.Preferences.PREF_LOGIN, sharedPref.getString(Constants.Preferences.PREF_LOGIN, "null"));
            intent.putExtras(extras);
            startActivity(intent);
        }



    }


    @Override
    public void onClick(View v) {

        EditText logText = (EditText) findViewById(R.id.loginEditText);
        EditText passwdText = (EditText) findViewById(R.id.passwordEditText);

        // If no password and/or login send message error
        if (TextUtils.isEmpty(passwdText.getText()) || TextUtils.isEmpty(logText.getText()) ){
            Toast.makeText(this,"Error", Toast.LENGTH_LONG).show();
        }
        else{

            //Creation of the main application
            Context context = getApplication();
            SharedPreferences sharedPref = context.getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPref.edit();

            //Creation login and password
            editor.putString(Constants.Preferences.PREF_PASSWORD,passwdText.getText().toString());
            editor.putString(Constants.Preferences.PREF_LOGIN, logText.getText().toString());
            editor.commit();


            //Generate intent and send it
            Intent intent = new Intent(getApplicationContext(), WLTwitterActivity.class);
            Bundle extras = new Bundle();
            extras.putString(Constants.Preferences.PREF_LOGIN, logText.getText().toString());
            intent.putExtras(extras);
            startActivity(intent);


        }

    }
}
