package com.example.alexandrosandreadis.diary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;



public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Diary.Entry.db";
    public static final String ENTRIES_TABLE_NAME="entries";
    public static final String ENTRIES_COLUMN_ID="id";
    public static final String ENTRIES_COLUMN_DATE="date";
    public static final String ENTRIES_COLUMN_ENTRY="entry";
    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+ ENTRIES_TABLE_NAME+" ("+ENTRIES_COLUMN_ID+" INTEGER PRIMARY KEY, "+ENTRIES_COLUMN_DATE+" TEXT, "+ENTRIES_COLUMN_ENTRY+" TEXT"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int olderVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ENTRIES_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertEntry(String date,String entry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENTRIES_COLUMN_DATE,date);
        contentValues.put(ENTRIES_COLUMN_ENTRY,entry);
        db.insert(ENTRIES_TABLE_NAME,null,contentValues);
        return true;
    }

   public ArrayList<ArrayList<String>>getAllEntries(){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor result=db.rawQuery("SELECT * FROM "+ENTRIES_TABLE_NAME,null);
        ArrayList<ArrayList<String>> headerDate = new ArrayList<>();
        ArrayList<String> tempDate = new ArrayList<>();
        ArrayList<String> tempTitle = new ArrayList<>();

        result.moveToFirst();
        while(result.isAfterLast()==false){
            String date=result.getString(result.getColumnIndex(ENTRIES_COLUMN_DATE));
            String title=result.getString(result.getColumnIndex(ENTRIES_COLUMN_ENTRY));
            title=title.substring(0,Math.min(title.length(),10));
            tempDate.add(date);
            tempTitle.add(title);
            result.moveToNext();
        }
        headerDate.add(tempTitle);
        headerDate.add(tempDate);
        return headerDate;
    }

   public Cursor readDatabase(){
       SQLiteDatabase db = this.getReadableDatabase();
       return db.rawQuery("SELECT * FROM "+ENTRIES_TABLE_NAME,null);
   }

   public String findFromDatabase(int id,String columnName){

       SQLiteDatabase db=this.getReadableDatabase();
       Cursor result = db.rawQuery("SELECT * FROM "+ENTRIES_TABLE_NAME+" WHERE "+ENTRIES_COLUMN_ID+"="+id,null);
       result.moveToFirst();
       return result.getString(result.getColumnIndex(columnName));

   }
   public void updateEntry(String newText,int id){
       SQLiteDatabase db=this.getReadableDatabase();
       db.execSQL("UPDATE "+ENTRIES_TABLE_NAME+" SET "+ENTRIES_COLUMN_ENTRY+" = '"+newText+"' WHERE "+ENTRIES_COLUMN_ID+" = "+id);
   }
}
