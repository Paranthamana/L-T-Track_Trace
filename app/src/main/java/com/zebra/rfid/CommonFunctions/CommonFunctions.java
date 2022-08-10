package com.zebra.rfid.CommonFunctions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.zebra.rfid.demo.sdksample.R;

import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CommonFunctions {


    //public static String fontPath = "fonts/font_regular.ttf";

    private static CommonFunctions ourInstance = new CommonFunctions();

    public static CommonFunctions getInstance() {
        return ourInstance;
    }

    public static Socket socket;

    private CommonFunctions() {
    }

    public void newIntent(Context context, Class destinationClass, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
    }

    public void ShowSnackBar(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(MyApplication.context.getResources().getColor(R.color.Black));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(MyApplication.context.getResources().getColor(R.color.White));
        snackbar.show();
    }

    public void ShowSnackBar1(Context context, String message) {
        View parent = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar;
        snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(MyApplication.context.getResources().getColor(R.color.Black));
        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(MyApplication.context.getResources().getColor(R.color.White));
        snackbar.show();
    }

    public boolean CheckInternetConnection() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void HideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    public static String formatDate (String date, String initDateFormat, String endDateFormat)
    {
        Date initDate = null;
        String parsedDate = "";
        try {
            initDate = new SimpleDateFormat(initDateFormat).parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
            parsedDate = formatter.format(initDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    private String getDate(String ourDate)
    {
        try
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(ourDate);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm"); //this format changeable
            dateFormatter.setTimeZone(TimeZone.getDefault());
            ourDate = dateFormatter.format(value);

            //Log.d("ourDate", ourDate);
        }
        catch (Exception e)
        {
            ourDate = "00-00-0000 00:00";
        }
        return ourDate;
    }

}
