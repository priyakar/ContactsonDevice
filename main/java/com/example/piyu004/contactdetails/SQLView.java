package com.example.piyu004.contactdetails;

/**
 * Created by Piyu004 on 4/4/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
        dbTest info = new dbTest(this);
        info.open();
        String data = info.getData();
        info.Close();
        tv.setText(data);
    }

}
