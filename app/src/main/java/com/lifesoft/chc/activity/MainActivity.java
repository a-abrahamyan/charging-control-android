package com.lifesoft.chc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lifesoft.chc.chargingcontrol.R;
import com.lifesoft.chc.sms.model.SmsModel;
import com.lifesoft.chc.sms.model.SmsObject;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__receive);
        Log.i(TAG, "onCreate:---------> successfully");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.i(TAG, "onCreate:---------> bundle not null successfully");
            //Todo : need get sms model from singleton class
            if (getSmsModel() != null) {
                Log.i(TAG, "onCreate:---------> " + getSmsModel().toString());
            }
        }
    }

    public SmsModel getSmsModel() {
        return SmsObject.INSTANCE().getSmsModel();
    }

}
