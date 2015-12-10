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
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if ((sharedPref.getString("login", "null") != "null") && (sharedPref.getString("password", "null") != "null")) {
            Intent intent = new Intent(getApplicationContext(), WLTwitterActivity.class);
            Bundle extras = new Bundle();
            extras.putString("login", sharedPref.getString("login", "null"));
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
            SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPref.edit();

            //Creation login and password
            editor.putString("password",passwdText.getText().toString());
            editor.putString("login", logText.getText().toString());
            editor.commit();


            //Generate intent and send it
            Intent intent = new Intent(getApplicationContext(), WLTwitterActivity.class);
            Bundle extras = new Bundle();
            extras.putString("login", logText.getText().toString());
            intent.putExtras(extras);
            startActivity(intent);


        }

    }
}
