package com.example.alexandrosandreadis.diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by darkb on 12/5/2017.
 */

public class Unlock extends AppCompatActivity {
    Button unlockButton;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
        unlockButton =(Button) findViewById(R.id.unlockButton);
        passwordText=(EditText) findViewById(R.id.unlockPasswordField);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPasswordInput(passwordText.getText().toString());
            }
        });
    }
    boolean checkPasswordInput(String passIn){
        String pref=MainActivity.MyPREFERENCES;
        String pass=MainActivity.Password;
        SharedPreferences sharedPref=getSharedPreferences(pref, Context.MODE_PRIVATE);
        if(sharedPref.contains(pass)){
            if(passIn.equals( sharedPref.getString(pass,null))){
                startActivity(new Intent(this,EntryList.class));
                startService(new Intent(this,AccelerometerService.class));
                super.finish();
            }else{
                Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
            }

        }else {

        }

        return true;
    }
}