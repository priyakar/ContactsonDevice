package com.example.piyu004.contactdetails;

/**
 * Created by Piyu004 on 4/4/2015.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLExample extends Activity implements OnClickListener{

    Button sqlUpdate, sqlView;
    EditText sqlName, sqlFriend;
    String extra;
    TextView tvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlexample);
        sqlUpdate = (Button) findViewById(R.id.bsqlUpdate);
        sqlView = (Button) findViewById(R.id.bview);
        sqlName = (EditText) findViewById (R.id.etsqlName);
        sqlFriend = (EditText) findViewById(R.id.etsqlfrnd);
        tvContacts = (TextView) findViewById(R.id.tvCon);
        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        boolean worked=false;
        switch (v.getId()){
            case R.id.bsqlUpdate:
                try {
                    String name = sqlName.getText().toString();
                    String frnd = sqlFriend.getText().toString();

                    dbTest entry = new dbTest (SQLExample.this);
                    entry.open();
                    entry.createEntry(name, frnd);
                    entry.Close();
                  worked = true;
                } catch (SQLiteException e) {
                    worked= false;
                } finally {
                    if (worked){
                        Dialog d = new Dialog(this);
                        d.setTitle("success!");
                        TextView tv = new TextView(this);
                        tv.setText("done!");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.bview:
                //sqlView.setText("done");
                tvContacts.setText("clicked view");
                Intent i = new Intent ("com.example.piyu004.contactdetails.SQLView");
                startActivity(i);
                break;
        }
    }
}
