package com.lifesoft.chc.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lifesoft.chc.chargingcontrol.R;
import com.lifesoft.chc.model.CCTransactions;
import com.lifesoft.chc.view.sms.model.SmsModel;
import com.lifesoft.chc.view.sms.model.SmsObject;
import com.lifesoft.chc.viewmodel.CreatedTransactionVM;
import com.lifesoft.chc.viewmodel.PostSmsDataVM;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getName();
    private CreatedTransactionVM createdTransactionVM;
    private PostSmsDataVM smsDataVM;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__receive);
        createdTransactionVM = ViewModelProviders.of(this).get(CreatedTransactionVM.class);
        smsDataVM = ViewModelProviders.of(this).get(PostSmsDataVM.class);
        createdTransactionVM.setDataDetails("AAA");
        getNetworkData();
        Log.i(TAG, "onCreate:---------> successfully");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.i(TAG, "onCreate:---------> bundle not null successfully");
            //Todo : need get sms model from singleton class
            if (getSmsModel() != null) {
                Log.i(TAG, "onCreate:---------> " + getSmsModel().toString());
            }
        }
        SmsObject.INSTANCE().setSmsModel(new SmsModel("A","A","A"));
        smsDataVM.setSmsModel(SmsObject.INSTANCE().getSmsModel());
    }

    public SmsModel getSmsModel() {
        return SmsObject.INSTANCE().getSmsModel();
    }

    private void createViewModels() {

    }

    private void getNetworkData() {
      //  createdTransactionVM.setDataDetails("AAA");
        createdTransactionVM.getModelTrasactionsMutableLiveData().observe(MainActivity.this, new Observer<CCTransactions>() {
            @Override
            public void onChanged(@Nullable CCTransactions appResponse) {
                if (appResponse!=null){
                    Log.i(TAG, "onChanged: Successfully");
                }else {
                    Log.i(TAG, "onChanged: Successfully");
                }


            }
        });

    }


}
