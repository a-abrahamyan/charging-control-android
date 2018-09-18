package com.lifesoft.chc.api;

import com.lifesoft.chc.model.CCTransactions;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("transaction/list/")
    Call<CCTransactions> getData();
}