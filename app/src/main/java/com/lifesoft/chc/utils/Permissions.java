package com.lifesoft.chc.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.lifesoft.chc.constants.AppConstants;

public class Permissions {
    private Activity activity;
    private Context context;

    public Permissions(Context context) {
        this.context = context;
        activity= (Activity) context;
    }
    private String TAG = Permissions.class.getName();
    public void connectSMSPermissions(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {


                } else {
                    activity.requestPermissions(new String[]{Manifest.permission.READ_SMS,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_SMS}, AppConstants.SMS_REQUEST_CODE);
                }

            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{ Manifest.permission.READ_SMS}, 1);
            }
        }catch (Exception e){
            Log.i(TAG, "connectSMSPermissions: "+e.getMessage());
        }
    }
}
