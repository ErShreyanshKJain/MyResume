package com.example.vithika.myresume;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {

    public void welcome(View view)
    {
        Button signUp=(Button)findViewById(R.id.btnSignUp);
        signUp.animate()
                .translationYBy(-150f)
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(2000);

        Button signIn=(Button)findViewById(R.id.btnSignIn);
        signIn.animate()
                .translationYBy(150f)
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(2000);

    }

    public void func_SignUp(View view)
    {
        //Button signUp=(Button)findViewById(R.id.btnSignUp);
        welcome(view);
        //end(view);
        Intent up=new Intent(this,CreateAccount.class);
        startActivity(up);
    }

    public void func_SignIn(View view)
    {
        welcome(view);
        Intent in=new Intent(this,LoginPage.class);
        startActivity(in);
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

    }
}
