package com.zebra.rfid.Api;


import com.zebra.rfid.ApiModel.LoginApiResponse;
import com.zebra.rfid.ApiModel.StationApiResponse;
import com.zebra.rfid.ApiModel.SyncWorkOrderApiResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(Urls.Login)
    Call<LoginApiResponse> login(@Body RequestBody body);

    @GET(Urls.Stations)
    Call<StationApiResponse> stationList();

    @POST(Urls.SyncWorkOrders)
    Call<SyncWorkOrderApiResponse> SyncWorkOrders(@Body RequestBody body) ;
}