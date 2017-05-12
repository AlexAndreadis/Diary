package com.example.alexandrosandreadis.diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by darkb on 12/5/2017.
 */

public class Login extends AppCompatActivity {
    Button loginButton;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginButton =(Button) findViewById(R.id.loginButton);
        passwordText=(EditText) findViewById(R.id.loginPasswordField);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPasswordInput(passwordText.toString());
            }
        });
    }
    boolean checkPasswordInput(String passIn){
        String pref=MainActivity.MyPREFERENCES;
        String pass=MainActivity.Password;
        SharedPreferences sharedPref=getSharedPreferences(pref, Context.MODE_PRIVATE);

        if(sharedPref.contains(pass)){
            if(passIn== sharedPref.getString(pass,null)){
                startActivity(new Intent(Login.this, Notes.class));
            }else{
                Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
            }

        }else {
            //This shouldn't happen
        }
        return true;
    }
}