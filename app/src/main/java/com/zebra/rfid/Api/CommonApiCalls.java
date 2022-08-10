package com.zebra.rfid.Api;

import android.content.Context;

import com.zebra.rfid.ApiModel.LoginApiResponse;
import com.zebra.rfid.ApiModel.StationApiResponse;
import com.zebra.rfid.ApiModel.SyncWorkOrderApiRequest;
import com.zebra.rfid.ApiModel.SyncWorkOrderApiResponse;
import com.zebra.rfid.CallBack.APICommonCallback;
import com.zebra.rfid.CommonFunctions.CommonFunctions;
import com.zebra.rfid.Util.CustomProgressDialog;

import org.json.JSONArray;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonApiCalls {
    private static CommonApiCalls ourInstance;

    public static CommonApiCalls getInstance() {
        ourInstance = new CommonApiCalls();
        return ourInstance;
    }

    public void login(Context context, final String body, final APICommonCallback.Listener listener) {
        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context);
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        RequestBody mRequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), (body));
        Call<LoginApiResponse> call = apiInterface.login(mRequestBody);
        call.enqueue(new Callback<LoginApiResponse>() {
            @Override
            public void onResponse(Call<LoginApiResponse> call, Response<LoginApiResponse> response) {
                if (response.isSuccessful()) {
                    CustomProgressDialog.getInstance().dismiss();
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
            }
        });
    }

    public void stationsList(Context context, final APICommonCallback.Listener listener) {
        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context);
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        Call<StationApiResponse> call = apiInterface.stationList();
        call.enqueue(new Callback<StationApiResponse>() {
            @Override
            public void onResponse(Call<StationApiResponse> call, Response<StationApiResponse> response) {
                if (response.isSuccessful()) {
                    CustomProgressDialog.getInstance().dismiss();
                    listener.onSuccess(response.body());

                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<StationApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
            }
        });
    }

    public void SyncWorkOrders(Context context, JSONArray body,
                               APICommonCallback.Listener listener){
        if (!CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().show(context);
        }
        ApiInterface apiInterface = ApiConfiguration.getInstance().getApiBuilder().create(ApiInterface.class);
        RequestBody mRequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(body));
        Call<SyncWorkOrderApiResponse> call = apiInterface.SyncWorkOrders(mRequestBody);
        call.enqueue(new Callback<SyncWorkOrderApiResponse>() {
            @Override
            public void onResponse(Call<SyncWorkOrderApiResponse> call, Response<SyncWorkOrderApiResponse> response) {
                if (response.isSuccessful()) {
                    CustomProgressDialog.getInstance().dismiss();
                    listener.onSuccess(response.body());
                    CommonFunctions.getInstance().ShowSnackBar1(context,"Data Push SuccessFully");
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<SyncWorkOrderApiResponse> call, Throwable t) {
                CustomProgressDialog.getInstance().dismiss();
                t.printStackTrace();
            }
        });


    }


}