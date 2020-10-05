package com.example.selfcreation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "stud.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table tbl_stud(sid INTEGER primary key autoincrement,fname TEXT," +
                " mname TEXT,lname TEXT,cno TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists tbl_stud");
        onCreate(db);

    }
    public Boolean insert(String fname,String mname,String lname,String cno){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("fname",fname);
        contentValues.put("mname",mname);
        contentValues.put("lname",lname);
        contentValues.put("cno",cno);
        long ins=db.insert("tbl_stud",null,contentValues);
        if(ins==-1)
            return false;
        else
            return true;
    }
    public Cursor showData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.query("tbl_stud",null,null,null,null,null,null);
        return result;
    }
    public Cursor showDetails(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.query("tbl_stud",null,"sid=?",new String[]{String.valueOf(id)},null,null,null);
        return result;
    }
}


