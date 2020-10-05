package com.example.selfcreation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewdata extends AppCompatActivity {
    ListView listView;
    ArrayList<String> records;
    ArrayAdapter<String> adapter;
    DatabaseHelper dBhelper;
    String details;
    String num1, name1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);
        controlIntitialization();
        showDEtails();
    }


    private void controlIntitialization() {
        dBhelper = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.info1);
        records = new ArrayList<String>();

    }

    private void showDEtails() {
        try {
            Cursor result = dBhelper.showData();
            while (result.moveToNext()) {
                records.add("First Name: " + result.getString(1) + "\nmiddle Name: " + result.getString(2) +
                        "\nLast Name: " + result.getString(3) + "\nContact Number: " + result.getString(4));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, records);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int a = position + 1;
                //Toast.makeText(getApplicationContext(),position+" "+a,Toast.LENGTH_LONG).show();
                Cursor result = dBhelper.showDetails(a);

                while (result.moveToNext()) {
                    num1 = result.getString(4);
                }
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + num1));

//                if(checkSelfPermission(Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
//                {
//                    return;
//                }


                startActivity(intent);
//                SmsManager smsManager=SmsManager.getDefault();
//                smsManager.sendTextMessage(num1,null,"Thank you",null,null);
                Toast.makeText(getApplicationContext(),"Thank You",Toast.LENGTH_LONG).show();

            }
        });

    }
}


