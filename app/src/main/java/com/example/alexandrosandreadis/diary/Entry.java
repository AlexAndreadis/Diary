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
import android.widget.Toast;


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
        Intent intent= getIntent();
        if(intent.hasExtra("entryId")){ //Check if was chosen from the list or is a new entry
            saveButton.setEnabled(false);
            final int id =intent.getIntExtra("entryId",0);
            populatedFieldsFromDB(id);
            entryText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveButton.setEnabled(true);
                }
            });
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateEntry(id);
                }
            });
        }else{// new Entry
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

    }

    void populatedFieldsFromDB(int id){
        DBHelper mydb=new DBHelper(this);
        date.setText(mydb.findFromDatabase(id,DBHelper.ENTRIES_COLUMN_DATE));
        entryText.setText(mydb.findFromDatabase(id,DBHelper.ENTRIES_COLUMN_ENTRY));
    }
    void saveEntry(){
        DBHelper mydb=new DBHelper(this);
        String text=entryText.getText().toString();
        if(!checkIfOnlySpaces(text)){
            mydb.insertEntry(currentDate,entryText.getText().toString());
            startActivity(new Intent(Entry.this, EntryList.class));
            Entry.super.finish();
        }else{
            Toast.makeText(this, "You didn't write anything", Toast.LENGTH_SHORT).show();
        }

    }
    void updateEntry(int id){
        DBHelper mydb = new DBHelper(this);
        String text=entryText.getText().toString();
        if(!checkIfOnlySpaces(text)){
            mydb.updateEntry(entryText.getText().toString(),id);
            startActivity(new Intent(Entry.this, EntryList.class));
            Entry.super.finish();
        }else{
            Toast.makeText(this, "You didn't write anything", Toast.LENGTH_SHORT).show();
        }
    }

    boolean checkIfOnlySpaces(String input){
        for (char c : input.toCharArray()){
            if (c!=' ') return false;
        }
        return true;
    }
}
