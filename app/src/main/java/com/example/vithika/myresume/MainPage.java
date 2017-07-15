package com.example.vithika.myresume;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    Button signUp;
    Button signIn;

    public void func_SignUp(View view) {
        signUp.animate()
                .translationYBy(-150f)
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent up = new Intent(MainPage.this, CreateAccount.class);
                startActivity(up);
            }
        }, 600);
    }

    public void func_SignIn(View view) {
        Intent in = new Intent(this, LoginPage.class);
        startActivity(in);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //redirectIfLoggedIn();

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

    /*public void redirectIfLoggedIn()
    {
        // if statement to check to Logged In user

        Intent intent = new Intent(this,Details.class);
        //Enter some details to pass if required

        startActivity(intent);
    }*/
}