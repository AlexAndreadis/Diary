package com.example.alexandrosandreadis.diary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EntryList extends AppCompatActivity {


    ListView listView;
    FloatingActionButton newEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrylist);
        listView=(ListView) findViewById(R.id.listView);
        newEntryButton= (FloatingActionButton) findViewById(R.id.newEntryFloatingButton);
        initializeList();
        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryList.this, Entry.class));
                EntryList.super.finish();
            }
        });

    }

    void initializeList(){
        DBHelper mydb=new DBHelper(this);
        ArrayList<String> dateEntries=mydb.getAllDatesFromEntries();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,dateEntries
        );
        listView.setAdapter(arrayAdapter);
    }
}
