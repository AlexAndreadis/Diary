package com.example.alexandrosandreadis.diary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Entry extends AppCompatActivity {
    TextView date;
    EditText entryText;
    SimpleDateFormat sdf;
    String currentDate;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        date = (TextView) findViewById(R.id.date);
        saveButton= (Button) findViewById(R.id.saveButton);
        entryText=(EditText) findViewById(R.id.entry);
        sdf = new SimpleDateFormat("EEEE dd MMMM yyyy", Locale.getDefault());
        currentDate = sdf.format(new Date());
        date.setText(currentDate);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEntry();
            }
        });


    }

    void saveEntry(){
        DBHelper mydb=new DBHelper(this);
        mydb.insertEntry(currentDate,entryText.getText().toString());
        startActivity(new Intent(Entry.this, EntryList.class));
        Entry.super.finish();
    }
}
