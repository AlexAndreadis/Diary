package com.example.alexandrosandreadis.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by darkb on 12/5/2017.
 */

public class Notes extends AppCompatActivity {
    Button createPasswordButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);

        createPasswordButton = (Button) findViewById(R.id.createNewPasswordButton);
        createPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Notes.this, CreatePassword.class));
            }
        });
    }
}