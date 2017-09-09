package com.example.eldhoittangeorge.datacollector;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterActivity extends AppCompatActivity {

    DatabaseHelper myData;
    String lattitude,longitude;
    public EditText editTextLong,editTextLat,editTextLandmark,editTextCapacity,editTextOwner,editTextlocation;
    public Button enter,buttonLocation;
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

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
        buttonLocation = (Button) findViewById(R.id.buttonLoc);

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMessageNoGps();

                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getLocation();
                }
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Boolean value =  myData.insertValue(editTextLong.getText().toString(),editTextLat.getText().toString(),editTextLandmark.getText().toString(),editTextCapacity.getText().toString(),editTextlocation.getText().toString(),editTextOwner.getText().toString());
                if (value)
                    Toast.makeText(EnterActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EnterActivity.this, "Didn't Insert", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

                editTextLat.setText(lattitude);
                editTextLong.setText(longitude);
            }else{
                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_LOCATION:
                getLocation();
                break;
        }
    }

    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

}













