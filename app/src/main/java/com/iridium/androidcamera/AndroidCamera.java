package com.iridium.androidcamera;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AndroidCamera  extends AppCompatActivity
{
    private static final String EXTRA_FILENAME= "CameraIntentPhoto";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String TAG = "CAMERA_APP";
    private File output=null;
    private Button captureBtn;
    private ImageView imageView;
    private Bundle mySavedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mySavedInstanceState = savedInstanceState;

        imageView = findViewById(R.id.imageView);
        captureBtn = findViewById(R.id.capture_btn);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Log.d(TAG,"CAPTURE BUTTON WAS CLIKED");
                capturePhoto();
            }
        });

    }

    private File createImageFile()
    {
        Log.d(TAG,"CREATING FILE PATH");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        //   currentPath = image.getAbsolutePath();
        return image;
    }

    private void capturePhoto()
    {
        if (mySavedInstanceState == null)
        {
            output =  createImageFile();

            if (output.exists()) {
                output.delete();
            }
            else {
                output.getParentFile().mkdirs();
            }

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri outputUri= FileProvider.getUriForFile(getApplicationContext(), getPackageName()+".provider", output);

            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            {
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN)
            {
                ClipData clip = ClipData.newUri(getContentResolver(), "A photo", outputUri);

                cameraIntent.setClipData(clip);
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            else
            {
                List<ResolveInfo> resInfoList= getPackageManager().queryIntentActivities(cameraIntent, PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName, outputUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
            }

            try {
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
            catch (ActivityNotFoundException e) {
                finish();
            }
        }
        else {
            output=(File)mySavedInstanceState.getSerializable(EXTRA_FILENAME);
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_FILENAME, output);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {

                Uri outputUri=FileProvider.getUriForFile(this, getPackageName()+".provider", output);
                Glide.with(this).load(outputUri).into(imageView);
            }
        }
    }
}
