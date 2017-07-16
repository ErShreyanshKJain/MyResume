package com.example.vithika.myresume;

import android.content.Intent;
import android.graphics.Color;
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

public class StartActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

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
            Intent intent = new Intent(this, Details.class);
            //Add extra arguments to pass
            startActivity(intent);

        } else {
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
                Intent intent = new Intent(this, SettingsActivity.class);
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

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    signInAccount(v);
                }
                return false;
            }
        });

        // Enter to code to take check these strings from the database
    }

    protected void signUp(View view) {
        signUpText = (TextView) findViewById(R.id.signUpText);
        signUpText.setTextColor(Color.BLUE);

        setTitle("Sign Up");

        loginLinear.startAnimation(slideLeft);
        loginLinear.setVisibility(View.INVISIBLE);
        createAccLinear.setVisibility(View.VISIBLE);
        createAccLinear.startAnimation(slideRight);
    }

}
