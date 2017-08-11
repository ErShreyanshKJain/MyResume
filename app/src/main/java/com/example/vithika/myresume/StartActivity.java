package com.example.vithika.myresume;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StartActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {


    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds

    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    Button signUp, signIn, signIN, signUP;
    EditText etEmail, etUserName, etPhoneNo, etPassword, etConfPass, userName, password;
    String valEmail, valUser, valPhoneNo, valPassword;
    TextView signInText, signUpText;
    LinearLayout mainLinear, createAccLinear, loginLinear;
    Animation fadeIn, fadeOut, slideRight, slideLeft;
    RelativeLayout background;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        DatabaseHelper myDB = new DatabaseHelper(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mainLinear = (LinearLayout) findViewById(R.id.mainLinear);
        createAccLinear = (LinearLayout) findViewById(R.id.createAccLinear);
        loginLinear = (LinearLayout) findViewById(R.id.loginLinear);
        background = (RelativeLayout) findViewById(R.id.backgroundRelative);

        mainLinear.setOnClickListener(this);
        createAccLinear.setOnClickListener(this);
        loginLinear.setOnClickListener(this);
        background.setOnClickListener(this);


        fadeIn = AnimationUtils.loadAnimation(StartActivity.this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(StartActivity.this, R.anim.fade_out);
        slideRight = AnimationUtils.loadAnimation(StartActivity.this, R.anim.slide_right);
        slideLeft = AnimationUtils.loadAnimation(StartActivity.this, R.anim.slide_left);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPhoneNo = (EditText) findViewById(R.id.etPhoneNo);
        etPassword = (EditText) findViewById(R.id.etPass);
        etConfPass = (EditText) findViewById(R.id.etConfPass);

        signUp = (Button) findViewById(R.id.SignUp);
        signUp.setTranslationY(-150f);
        signUp.setScaleY(0f);
        signUp.setScaleX(0f);
        signUp.animate()
                .translationYBy(150f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(1000);

        signIn = (Button) findViewById(R.id.SignIn);
        signIn.setTranslationY(150f);
        signIn.setScaleY(0f);
        signIn.setScaleX(0f);
        signIn.animate()
                .translationYBy(-150f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(1000);


    }

    public void func_SignUp(View view) {
        signUp.animate()
                .translationYBy(-150f)
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(1000);

        signIn.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainLinear.setVisibility(View.INVISIBLE);
                createAccLinear.setVisibility(View.VISIBLE);
                createAccLinear.startAnimation(fadeIn);
            }
        }, 1000);

        setTitle("Sign Up");
    }

    public void func_SignIn(View view) {
        signIn.animate()
                .translationYBy(150f)
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(1000);

        signUp.setVisibility(View.INVISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mainLinear.setVisibility(View.INVISIBLE);
                loginLinear.setVisibility(View.VISIBLE);
                loginLinear.startAnimation(fadeIn);
            }
        }, 1000);

        setTitle("Sign In");

    }

    public void signUpAccount(View view) {
        progressBar.animate().start();

        etConfPass.setOnKeyListener(this);

        valPassword = etPassword.getText().toString();

        if (etConfPass.getText().toString() == valPassword) {
            valEmail = etEmail.getText().toString();
            valUser = etUserName.getText().toString();
            valPhoneNo = etPhoneNo.getText().toString();

            Log.i("Result", "Passwords matched");

            //Add code to push the values to Firebase
//            try {
//                DatabaseHelper DatabaseHelper = new DatabaseHelper(this);
//                SQLiteDatabase db = DatabaseHelper.getWritableDatabase();
//
//                DatabaseHelper.insertData(db,valUser ,valEmail,valPassword,valPhoneNo );
//                Toast t1 = Toast.makeText(getApplicationContext(),"successfully entered in the database",Toast.LENGTH_SHORT);
//                db.close();
//            } catch (SQLiteException e) {
//                Toast toast = Toast.makeText(getApplicationContext(), "Unavailable to Write in the Database", Toast.LENGTH_SHORT);
//                toast.show();
//            }

            Intent intent = new Intent(this, Details.class);
            //Add extra arguments to pass
            startActivity(intent);

        }
        else
            {
            Toast.makeText(getApplicationContext(), "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Please Try again", Toast.LENGTH_SHORT).show();

            etPassword.setText("");
            etConfPass.setText("");
        }
    }

    protected void signIn(View view) {
        signInText = (TextView) findViewById(R.id.signInText);
        signInText.setTextColor(Color.BLUE);

        setTitle("Sign In");

        createAccLinear.startAnimation(slideLeft);
        createAccLinear.setVisibility(View.INVISIBLE);
        loginLinear.setVisibility(View.VISIBLE);
        loginLinear.startAnimation(slideRight);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            signUpAccount(v);
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_details, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i("Message", "Action Settings chosen");
//                Intent intent = new Intent(this, SettingsActivity.class);
                return true;

            case R.id.action_help:
                Log.i("Message", "Actions Help chosen");
                return true;

            default:
                Log.i("Message", "Nothing is chosen");
                return false;
        }

    }

    @Override
    public void onClick(View v) throws NullPointerException {
        if (v.getId() == R.id.backgroundRelative || v.getId() == R.id.mainLinear || v.getId() == R.id.createAccLinear || v.getId() == R.id.loginLinear) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected void signInAccount(View view) {
        String user = userName.getText().toString();
        String pass = password.getText().toString();
        new AsyncLogin().execute(user,pass);

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    signInAccount(v);

                }
                return false;
                    }
                });
         }

        // Enter to code to take check these strings from the database
//        try{
//            SQLiteOpenHelper DatabaseHelper = new DatabaseHelper(this);
//            SQLiteDatabase db = DatabaseHelper.getReadableDatabase();
//
//            Cursor cursor = db.query("Login_table",
//                    new String[] {"NAME","PASSWORD"},
//                    null,
//                    null,
//                    null,null,null);
//            if(cursor.moveToFirst()) {
//                do{
//                    String Name = cursor.getString(0);
//                    String Pass = cursor.getString(1);
//                   if (user.equals(Name) && pass.equals(Pass)) {
//
//                        Intent statement to be exceuted after it is correct ;
//                    }
//
//                }while(cursor.moveToNext());
//            }
//            else
//            {
//                Toast t = Toast.makeText(getApplicationContext(), "WRONG USERNAME OR PASSWORD", Toast.LENGTH_SHORT);
//                t.show();
//            }
//            cursor.close();
//            db.close();
//
//        }
//        catch(SQLiteException e){
//            Toast toast = Toast.makeText(getApplicationContext(),"Database unavailable", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }

    protected void signUp(View view) {
        signUpText = (TextView) findViewById(R.id.signUpText);
        signUpText.setTextColor(Color.BLUE);

        setTitle("Sign Up");

        loginLinear.startAnimation(slideLeft);
        loginLinear.setVisibility(View.INVISIBLE);
        createAccLinear.setVisibility(View.VISIBLE);
        createAccLinear.startAnimation(slideRight);
    }


    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(StartActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://localhost/android_connect/login.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true"))
            {
                Intent intent = new Intent(StartActivity.this,Details.class);
                startActivity(intent);
                StartActivity.this.finish();

            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(StartActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(StartActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }
        }

    }

}
