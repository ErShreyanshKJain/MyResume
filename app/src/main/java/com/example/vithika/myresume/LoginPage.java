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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

//We need delete this class. So whatever work you have added here, please add it to StartActivity.java

public class LoginPage extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    TextView signUpText;
    EditText userName,password;
    Button signIN;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;

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
        setContentView(R.layout.activity_login_page);

        setTitle("Sign In");

        userName = (EditText)findViewById(R.id.etUser);
        password = (EditText)findViewById(R.id.etPassword);
        signIN = (Button)findViewById(R.id.btnSingIn);

        relativeLayout = (RelativeLayout)findViewById(R.id.activity_login_page);
        linearLayout = (LinearLayout)findViewById(R.id.activity_linear);

        relativeLayout.setOnClickListener(this);
        linearLayout.setOnClickListener(this);

    }

    protected void signUp(View view) {
        signUpText = (TextView) findViewById(R.id.signUpText);
        signUpText.setTextColor(Color.BLUE);

        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    protected void signInAccount (View view)
    {
        String user = userName.getText().toString();
        String pass = password.getText().toString();

        password.setOnKeyListener(this);

        // Enter to code to take check these strings from the database
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            signInAccount(v);
        }

        return false;
    }

    @Override
    public void onClick(View v) throws NullPointerException
    {
        if (v.getId()==R.id.activity_login_page || v.getId()==R.id.activity_linear)
        {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }
}
