package com.lifesoft.chc.viewmodel;

import android.util.Log;

import com.lifesoft.chc.AppApplication;
import com.lifesoft.chc.constants.AppConstants;
import com.lifesoft.chc.view.sms.model.SmsModel;

import java.io.IOException;

import okhttp3.OkHttpClient;

public class PostSmsDataVM {
    private static String TAG = PostSmsDataVM.class.getName();
    public void postRequset(SmsModel smsModel){
        OkHttpClient client = new OkHttpClient();
        client.newCall(AppApplication.configurationObject(AppConstants.POST_BASE_URL, smsModel)).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.i(TAG, "onFailure: " + e.getMessage());
            }
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.body() != null) {
                    Log.i(TAG, "onResponse: ");
                }
            }
        });
    }

}
