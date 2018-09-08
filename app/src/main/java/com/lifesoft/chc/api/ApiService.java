package com.lifesoft.chc.api;

import com.lifesoft.chc.model.CCPostRequest;
import com.lifesoft.chc.model.CCTransactions;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("transaction/list/")
    Call<CCTransactions> getData();

    @POST("transaction/create")
    @FormUrlEncoded
    Call<CCPostRequest> postData(@Field("type") String type,
                                 @Field("text") String text,
                                 @Field("date") String data,
                                 @Field("id") String id);
}