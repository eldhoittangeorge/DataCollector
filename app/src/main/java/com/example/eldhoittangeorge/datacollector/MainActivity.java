package com.example.eldhoittangeorge.datacollector;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button viewButton,enterButton;
    DatabaseHelper myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewButton = (Button) findViewById(R.id.buttonV);
        enterButton = (Button) findViewById(R.id.buttonC);
        myData = new DatabaseHelper(this);
        final Intent eIntent = new Intent(MainActivity.this,EnterActivity.class);


        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(eIntent);
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myData.showData();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        Toast.makeText(MainActivity.this, res.getString(0), Toast.LENGTH_SHORT).show();
                        buffer.append("Longnitude :"+res.getString(0)+"\n");
                        buffer.append("Latitude :"+res.getString(1)+"\n");
                        buffer.append("Landmark :"+res.getString(2)+"\n");
                        buffer.append("Capacity :"+res.getString(3)+"\n");
                        buffer.append("Location :"+res.getString(4)+"\n");
                        buffer.append("Owner :"+res.getString(5)+"\n\n");

                    }
                    showMessage("Parking Data",buffer.toString());

                }
            }
        });
    }

    public void showMessage(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}










