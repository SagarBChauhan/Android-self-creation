package com.example.selfcreation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class selfcreation1 extends AppCompatActivity {

    EditText f_name,m_name,l_name,C_NO;
    Button btnsave,btnshow;
    DatabaseHelper dBhelper;
    TextView val1,val2,val3,val4,val5,val6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfcreation1);
        controlInitialization();
        setButtonClick();


    }
    private void controlInitialization() {
        f_name=(EditText)findViewById(R.id.fname1);
        m_name = (EditText) findViewById(R.id.mname1);
        l_name = (EditText) findViewById(R.id.lastName1);
        C_NO = (EditText) findViewById(R.id.cno1);

        btnsave = (Button) findViewById(R.id.btnsave);
        btnshow = (Button) findViewById(R.id.btnshow);
        val1=(TextView)findViewById(R.id.val1);
        val2=(TextView)findViewById(R.id.val2);
        val3=(TextView)findViewById(R.id.val3);
        val4=(TextView)findViewById(R.id.val4);
        dBhelper=new DatabaseHelper(this);

    }
    private void setButtonClick() {
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cname=f_name.getText().toString();
                String pdate=m_name.getText().toString();
                String btime=l_name.getText().toString();
                String p_feature=C_NO.getText().toString();

                if(cname.equals("") || pdate.equals("") || btime.equals("") || p_feature.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter all Details", Toast.LENGTH_LONG).show();
                    val1.setVisibility(View.VISIBLE);
                    val2.setVisibility(View.VISIBLE);
                    val3.setVisibility(View.VISIBLE);
                    val4.setVisibility(View.VISIBLE);

                }
                else {
                    Boolean ins = dBhelper.insert(cname, pdate, btime, p_feature);
                    if (ins == true) {
                        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_LONG).show();
//                        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext())
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                .setContentTitle("Added")
//                                .setContentText("New Record Added")
//                                .setAutoCancel(true);
//
//                        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//                        notificationManager.notify(0,builder.build());

//                        Intent intent=new Intent(getApplicationContext(),view_data.class);
//                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), viewdata.class);
                startActivity(intent);
            }
        });
    }

}
