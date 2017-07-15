package com.example.vithika.myresume;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//We need delete this class. So whatever work you have added here, please add it to StartActivity.java

public class CreateAccount extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener {

    EditText etEmail, etUserName, etPhoneNo, etPassword, etConfPass;
    String valEmail, valUser, valPhoneNo, valPassword;
    TextView signInText;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;

    public void signUpAccount(View view) {
        etEmail = (EditText)findViewById(R.id.etEmail);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etPhoneNo = (EditText)findViewById(R.id.etPhoneNo);
        etPassword = (EditText)findViewById(R.id.etPass);
        etConfPass = (EditText)findViewById(R.id.etConfPass);

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        setTitle("Sign Up");

        relativeLayout = (RelativeLayout)findViewById(R.id.backgroundRelative);
        linearLayout = (LinearLayout)findViewById(R.id.backgroundLinear);

        relativeLayout.setOnClickListener(this);
        linearLayout.setOnClickListener(this);
        }

    protected void signIn(View view) {
        signInText = (TextView) findViewById(R.id.signInText);
        signInText.setTextColor(Color.BLUE);

        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            signUpAccount(v);
        }
        return false;
    }

    @Override
    public void onClick(View v) throws NullPointerException
    {
        if (v.getId()==R.id.backgroundRelative || v.getId()==R.id.backgroundLinear)
        {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }
}