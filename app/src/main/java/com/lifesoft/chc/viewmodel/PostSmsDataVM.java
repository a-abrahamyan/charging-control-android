package com.lifesoft.chc.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.lifesoft.chc.api.ApiService;
import com.lifesoft.chc.api.ApiUtils;
import com.lifesoft.chc.model.CCPostRequest;
import com.lifesoft.chc.view.sms.model.SmsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostSmsDataVM extends AndroidViewModel {
    private static String TAG = PostSmsDataVM.class.getName();
    private MutableLiveData<CCPostRequest> mutableLiveData = new MutableLiveData<>();
    private ApiService apiService;
    private SmsModel smsModel;

    public void setSmsModel(SmsModel smsModel) {
        this.smsModel = smsModel;
        postRequset();
    }

    public PostSmsDataVM(@NonNull Application application) {
        super(application);
    }
    private void postRequset(){
        apiService = ApiUtils.getAPIService();
        apiService.postData("AAA","AAA","AAA","AAA").enqueue(new Callback<CCPostRequest>() {
            @Override
            public void onResponse(Call<CCPostRequest> call, Response<CCPostRequest> response) {

            }

            @Override
            public void onFailure(Call<CCPostRequest> call, Throwable t) {

            }
        });
    }

}
