package com.example.alexandrosandreadis.diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES="MyPrefs";
    public static final String Password="password";
    public static final String BroadcastFin="WeGotCaught";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if( sharedPref.contains(Password)){
            startActivity(new Intent(MainActivity.this, Unlock.class));
        } else {
            startActivity(new Intent(MainActivity.this, CreatePassword.class));
        }
        super.finish();
    }
}
