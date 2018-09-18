package com.lifesoft.chc;

import android.app.Application;

import com.google.gson.Gson;
import com.lifesoft.chc.api.ApiService;
import com.lifesoft.chc.constants.AppConstants;
import com.lifesoft.chc.view.sms.model.SmsModel;


import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApplication extends Application {

    public static AppApplication appApplication;
    private static Retrofit retrofit=null;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication =  this;
    }
    public ApiService getData() {
        return initRetrofit(AppConstants.GET_BASE_URL).create(ApiService.class);
    }
    private Retrofit initRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Request configurationObject(String baseUrl, SmsModel smsModel){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Gson gson = new Gson();
        String json = gson.toJson(smsModel);
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(baseUrl)
                .post(body)
                .build();
        return request;
    }
}
