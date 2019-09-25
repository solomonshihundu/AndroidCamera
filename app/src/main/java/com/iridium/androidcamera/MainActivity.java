package com.iridium.androidcamera;

// Not necessary
/*
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "CAMERA_APP";
    private ImageView imageView;
    private Button captureBtn;
    private String currentPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        captureBtn = findViewById(R.id.capture_btn);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.d(TAG,"CAPTURE BUTTON WAS CLIKED");

                // checks if camera permissions have been granted
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {

                    Log.d(TAG,"PERMISSIONS WERE NOT GRANTED");

                    // Permission is not granted

                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.CAMERA)) {

                        //tell user why camera not working
                       Toast.makeText(getApplicationContext(),"Access to camera denied",Toast.LENGTH_LONG).show();

                    } else
                        {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                REQUEST_IMAGE_CAPTURE);

                    }
                } else {

                    Log.d(TAG,"PROCEED TO LAUCH CAMERA INTENT");
                    // Permission has already been granted
                    capturePhoto();
                }

            }
        }
        );

    }

    private void capturePhoto()
    {
        Log.d(TAG,"START INTENT CAPTURE IMAGE");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null)
        {

            // Create the File where the photo should go
            File photoFile = null;

            try
            {
                photoFile = createImageFile();
            } catch (IOException ex)
            {

                // Error occurred while creating the File
                Log.i(TAG, "IOException");
                Log.d(TAG,"STORAGE FILE PATH COULD NOT BE CREATED");
            }

            // Continue only if the File was successfully created
            if (photoFile != null)
            {


                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),getPackageName()+".provider", photoFile);
                Log.d(TAG,"PHOTO URI CREATED");

                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                Log.d(TAG,"EXTRAS ADDED TO INTENT");

                startActivityForResult(cameraIntent,REQUEST_IMAGE_CAPTURE);
                Log.d(TAG,"CAMERA INTENT LAUNCHED");

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUEST_IMAGE_CAPTURE:
                    Log.d(TAG,"LOAD TAKEN PHOTO INTO IMAGEVIEW");
                    Uri uri = data.getData();
                    Glide.with(this).load(uri).into(imageView);
                    currentPath = uri.toString();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission was granted
                    Log.d(TAG, "PERMISSIONS GRANTED");
             //       capturePhoto();

                }
                else
                    {
                    // permission denied
                    Log.d(TAG, "PERMISSIONS DENIED");
                    }
                return;
            }
        }
    }
}


*/
