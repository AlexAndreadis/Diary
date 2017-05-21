package com.example.alexandrosandreadis.diary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EntryList extends AppCompatActivity {


    ListView listView;
    FloatingActionButton newEntryButton;
    FloatingActionButton deleteEntryButton;
    Cursor data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrylist);
        listView=(ListView) findViewById(R.id.listView);
        newEntryButton= (FloatingActionButton) findViewById(R.id.newEntryFloatingButton);
        deleteEntryButton = (FloatingActionButton) findViewById(R.id.deleteEntryFloatingButton);
        initializeList();
        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryList.this, Entry.class));
                EntryList.super.finish();
            }
        });
        if (data!=null){
            deleteEntryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(EntryList.this, EntryListDelete.class));
                    EntryList.super.finish();
                }
            });
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EntryList.this,Entry.class);
                Log.v("Position", Integer.toString(position));
                int entryId= findIdFromPosition(position);
                intent.putExtra("entryId",entryId);
                startActivity(intent);
                EntryList.super.finish();
            }
        });

    }

    int findIdFromPosition(int position){
        data.moveToPosition(position);
        int id =data.getInt(data.getColumnIndex(DBHelper.ENTRIES_COLUMN_ID));

        return id;

    }

    void initializeList(){
        DBHelper mydb=new DBHelper(this);
        data= mydb.readDatabase();
        final ArrayList<ArrayList<String>> valuesList=mydb.getAllEntries();
        ArrayAdapter<String> adapter =new ArrayAdapter<String> (this, android.R.layout.simple_list_item_2, android.R.id.text1,valuesList.get(0))
        {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(valuesList.get(0).get(position));
                text2.setText(valuesList.get(1).get(position));
                return view;
            }
        };

        listView.setAdapter(adapter);


    }


}
