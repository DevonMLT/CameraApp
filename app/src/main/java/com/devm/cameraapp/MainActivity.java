package com.devm.cameraapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;


public class MainActivity extends AppCompatActivity {
    //create a private variable that will create an object
    //from manifest file and it will help with permissions
    Button camButt;
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //#1
        //prompt the user for camera permission
        //check if the permission has been granted
        //if yes, enable camera:otherwise, ask for permission

        camButt = findViewById(R.id.enableCamera);
        camButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraPermission()) {
                    enableCamera();
                }
                else {
                    requestPermission();
                }
            }
        });
    }

    //#2
    //returns a voolean value
    //depending on whether the user has given camera permission to our app
    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    //#3
    //REquests permission from
    //the user so that our app can access the
    //device's camera and perform the image analysis
    private void requestPermission() {
        ActivityCompat.requestPermissions(this,CAMERA_PERMISSION,CAMERA_REQUEST_CODE);
    }

    //#4
    //create CameraActivity ACTIVITY then do this code
    //creates a new Intent object to start a CameraActivity
    private void enableCamera(){
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

}