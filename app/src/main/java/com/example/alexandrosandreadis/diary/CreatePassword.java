package com.example.alexandrosandreadis.diary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePassword extends AppCompatActivity {

    Button newPasswordButton;
    EditText pass1,pass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        newPasswordButton= (Button) findViewById(R.id.createPasswordButton);
        pass1=(EditText) findViewById(R.id.pass1);
        pass2=(EditText) findViewById(R.id.pass2);
        newPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePassWord(pass1.toString(),pass2.toString());
            }
        });
    }

    void savePassWord (String pass1,String pass2){

        if (!pass1.equals(pass2)){
            Toast.makeText(getApplicationContext(),"Passwords Don't Match!",Toast.LENGTH_SHORT).show();
        }else{
            String pref=MainActivity.MyPREFERENCES;
            String pass=MainActivity.Password;
            SharedPreferences.Editor editor = getSharedPreferences(pref,MODE_PRIVATE).edit();
            editor.putString(pass,pass1);
            editor.commit();
            startActivity(new Intent(CreatePassword.this, Notes.class));
        }

    }
}
