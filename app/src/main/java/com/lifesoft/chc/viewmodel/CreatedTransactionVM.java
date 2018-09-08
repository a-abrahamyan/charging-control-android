package com.lifesoft.chc.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.lifesoft.chc.AppApplication;
import com.lifesoft.chc.model.CCTransactions;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class CreatedTransactionVM extends AndroidViewModel {

    private MutableLiveData<CCTransactions> modelTrasactionsMutableLiveData = new MutableLiveData<>();
    private String dataDetails;

    public void setDataDetails(String data) {
        this.dataDetails = data;
    }

    public CreatedTransactionVM(@NonNull Application application) {
        super(application);
        jsonResponse();
    }
    private void jsonResponse(){
        AppApplication
                .appApplication
                .getData()
                .getData()
                .enqueue(new Callback<CCTransactions>() {
                    @Override
                    public void onResponse(Call<CCTransactions> call, Response<CCTransactions> response) {
                        if (response.body()!=null) {
                            Observable.just(response.body())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(subscribeData());
                        }
                    }

                    @Override
                    public void onFailure(Call<CCTransactions> call, Throwable t) {
                        Log.i("scswcdwsdwsxd", "onFailure: ");
                    }
                });
    }

    private Observer<CCTransactions> subscribeData() {
        return new Observer<CCTransactions>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CCTransactions appResponse) {
                modelTrasactionsMutableLiveData.setValue(appResponse);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public MutableLiveData<CCTransactions> getModelTrasactionsMutableLiveData() {
        return modelTrasactionsMutableLiveData;
    }
}
