package com.example.eldhoittangeorge.datacollector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterActivity extends AppCompatActivity {

    DatabaseHelper myData;
    public EditText editTextLong,editTextLat,editTextLandmark,editTextCapacity,editTextOwner,editTextlocation;
    public String longi,lat,capacity,landmark,owner,location;
    public Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        myData = new DatabaseHelper(this);

        editTextLong = (EditText) findViewById(R.id.editTextLong);
        editTextLat = (EditText) findViewById(R.id.editTextLat);
        editTextLandmark = (EditText) findViewById(R.id.editTextMark);
        editTextCapacity = (EditText) findViewById(R.id.editTextCap);
        editTextOwner = (EditText) findViewById(R.id.editTextOwner);
        editTextlocation = (EditText) findViewById(R.id.editTextLoc);
        enter = (Button) findViewById(R.id.buttonEnterData);
        

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EnterActivity.this, longi, Toast.LENGTH_SHORT).show();
               Boolean value =  myData.insertValue(editTextLong.getText().toString(),editTextLat.getText().toString(),editTextLandmark.getText().toString(),editTextCapacity.getText().toString(),editTextOwner.getText().toString(),editTextlocation.getText().toString());
                if (value)
                    Toast.makeText(EnterActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EnterActivity.this, "Didn't Insert", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
