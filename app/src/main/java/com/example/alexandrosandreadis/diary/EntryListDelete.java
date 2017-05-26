package com.example.alexandrosandreadis.diary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EntryListDelete extends AppCompatActivity {

    Button cancel;
    Button delete;
    ListView listView;
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list_delete);
        cancel = (Button) findViewById(R.id.buttonCancel);
        delete = (Button) findViewById(R.id.buttonDelete);
        listView=(ListView) findViewById(R.id.listViewDelete);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntryListDelete.this, EntryList.class));
                EntryListDelete.super.finish();
            }
        });
        initializeListDel();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEntries();
                startActivity(new Intent(EntryListDelete.this, EntryList.class));
                EntryListDelete.super.finish();
            }
        });

    }


    void initializeListDel(){
        DBHelper mydb=new DBHelper(this);
        data= mydb.readDatabase();
        final ArrayList<ArrayList<String>> valuesList=mydb.getAllEntries();
        ArrayAdapter<String> adapter =new ArrayAdapter<String> (this, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1,valuesList.get(0))
        {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setText(valuesList.get(0).get(position));
                text1.setTextColor(Color.WHITE);
                return view;
            }
        };
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

    }

    void deleteEntries(){
        DBHelper mydb=new DBHelper(this);
        List<Integer> idsToDel=new ArrayList<>();
        for (int i=0;i<listView.getAdapter().getCount();i++){
            if (listView.isItemChecked(i)){
               idsToDel.add(findIdFromPosition(i));
            }
        }
        for(int id:idsToDel){
           mydb.deleteByID(id);
        }


    }

    int findIdFromPosition(int dataId){
        data.moveToPosition(dataId);
        int id =data.getInt(data.getColumnIndex(DBHelper.ENTRIES_COLUMN_ID));
        return id;

    }
}
