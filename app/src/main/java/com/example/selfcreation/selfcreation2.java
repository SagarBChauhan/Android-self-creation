package com.example.selfcreation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class selfcreation2 extends AppCompatActivity {


    dobDataHelper dobobj;
    ListView listdob;
    Button btnsave,btnshow,btndob,btnshowalldetaials;
    EditText fname,sdob;
    private int mYear,mMonth,mDay;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfcreation2);
        controlInitialization();
        setbuttonclick();

    }
    private void controlInitialization() {
        btnsave=(Button)findViewById(R.id.btnsave);
        btnshow=(Button)findViewById(R.id.btnshow);
        btnshowalldetaials=(Button)findViewById(R.id.btnshowalldetaials);
        fname=(EditText)findViewById(R.id.fname);
        sdob=(EditText)findViewById(R.id.dob);
        listdob=(ListView)findViewById(R.id.listdob);
        arrayList=new ArrayList<>();
        dobobj=new dobDataHelper(this);


    }
    private void setbuttonclick() {

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=fname.getText().toString();
                String datofbirth=sdob.getText().toString();
                boolean ins=dobobj.insert(name,datofbirth);
                if(ins==true){
                    Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Not Inserted",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                Cursor result=dobobj.showDetail();
                while (result.moveToNext()){
                    arrayList.add("Name: "+result.getString(1)+"\n"+"Date Of Birth: "+result.getString(2));
                }
                adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                listdob.setAdapter(adapter);
            }
        });
        btnshowalldetaials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                Cursor result=dobobj.   showDetails();
                while (result.moveToNext()){
                    arrayList.add("Name: "+result.getString(1)+"\n"+"Date Of Birth: "+result.getString(2));
                }
                adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                listdob.setAdapter(adapter);
            }
        });
    }

    public class dobDataHelper extends SQLiteOpenHelper {


        public dobDataHelper(Context context) {
            super(context,"dob.db",null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("Create Table tbl_dob(did INTEGER primary key autoincrement,fname TEXT," +
                    " dob TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE if exists tbl_stud");
            onCreate(db);
        }
        public Boolean insert(String name,String dob){
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put("fname",name);
            contentValues.put("dob",dob);
            long ins=db.insert("tbl_dob",null,contentValues);
            if(ins==-1)
                return false;
            else
                return true;
        }
        public Cursor showDetail(){
            SQLiteDatabase db=this.getWritableDatabase();
//            DateFormat dateFormat = new SimpleDateFormat("dd");
//            Date date = new Date();
//            System.out.println(dateFormat.format(date));
//            Calendar c=Calendar.getInstance();
//            Toast.makeText(getApplicationContext(),"Date: "+dateFormat.format(date),Toast.LENGTH_LONG).show();

            Cursor result=db.query("tbl_dob",null,"dob>? and dob<?",new String[]{"2019-10-21","2019-10-27"},null,null,null);
            return result;
        }
        public Cursor showDetails(){
            SQLiteDatabase db=this.getWritableDatabase();
            DateFormat dateFormat = new SimpleDateFormat("dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            Calendar c=Calendar.getInstance();
            Toast.makeText(getApplicationContext(),"Date: "+dateFormat.format(date),Toast.LENGTH_LONG).show();
            Cursor result=db.query("tbl_dob",null,null,null,null,null,null);
            return result;
        }
    }
}


