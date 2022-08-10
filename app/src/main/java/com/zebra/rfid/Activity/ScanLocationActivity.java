package com.zebra.rfid.Activity;


import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.app.FragmentManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.zebra.rfid.Api.CommonApiCalls;
import com.zebra.rfid.ApiModel.StationApiResponse;
import com.zebra.rfid.ApiModel.SyncWorkOrderApiRequest;
import com.zebra.rfid.ApiModel.SyncWorkOrderApiResponse;
import com.zebra.rfid.CallBack.APICommonCallback;
import com.zebra.rfid.CommonFunctions.CommonFunctions;
import com.zebra.rfid.CommonFunctions.Constants;
import com.zebra.rfid.CommonFunctions.SessionManager;
import com.zebra.rfid.Model.RFIDAndTimeListModel;
import com.zebra.rfid.Model.StationModel;
import com.zebra.rfid.RealmDB.StationAndLocationDB;
import com.zebra.rfid.Util.CustomProgressDialog;
import com.zebra.rfid.api3.TagData;
import com.zebra.rfid.demo.sdksample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ScanLocationActivity extends AppCompatActivity implements
        RFIDHandlerLocation.ResponseHandlerInterface {

    public TextView tvConnectionStatus = null;
    private static final long SPLASH_DISPLAY_LENGTH = 3000;
    TextView tvOnline;
    TextView tvLogout;
    View vDotColors;
    Button btnScanLocation;
    TextView tvSavedStation;
    ImageView ivMenu;
    RFIDHandlerLocation rfidHandler;
    private String TagID = null;
    Realm realmNew;
    boolean its_detail = false;
    InputStream isBt;
    OutputStream osBt;
    private static final String TAG = "BtTest";
    BluetoothSocket mBtSocket = null;
    final int SCANNING_TIMEOUT = 1500; //ms
    private static int operationType = 0;
    private static final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private SyncWorkOrderApiResponse responseSync;
    private StationApiResponse response;
    private List<StationApiResponse.Station> stationApiResponseList;
    private Realm realm;
    private RealmResults<StationModel> stationWhere;
    Boolean doubleBackToExitPressedOnce = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_location);
        initView();
        realmNew = Realm.getDefaultInstance();
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("sharedpreferences", 0);
        boolean prefsBoolean = prefs.getBoolean("select_client", false);
        its_detail = prefsBoolean;

        CommonFunctions.getInstance().ShowSnackBar1(ScanLocationActivity.this,
                "Please Wait until Beep Sound connect with reader");

        // RFID Handler
        rfidHandler = new RFIDHandlerLocation();
        rfidHandler.onCreate(this);

        if (CommonFunctions.getInstance().CheckInternetConnection()) {
            tvOnline.setText(R.string.ONLINE);
            vDotColors.setBackgroundResource(R.drawable.dot_view_green);
        } else {
            tvOnline.setText(R.string.OFF_LINE);
            tvOnline.setTextColor(getResources().getColor(R.color.Red));
            vDotColors.setVisibility(View.GONE);
        }

        tvLogout.setOnClickListener(v -> {
            CustomProgressDialog.getInstance().show(ScanLocationActivity.this);
            if (CommonFunctions.getInstance().CheckInternetConnection()) {
                new Handler().postDelayed(() -> {
                    tvOnline.setText(R.string.ONLINE);
                    vDotColors.setBackgroundResource(R.drawable.dot_view_green);
                    SessionManager.getInstance().Logout();
                    CommonFunctions.getInstance().newIntent(ScanLocationActivity.this,
                            LoginActivity.class, Bundle.EMPTY, true);
                    CustomProgressDialog.getInstance().dismiss();
                }, SPLASH_DISPLAY_LENGTH);
            } else {
                CustomProgressDialog.getInstance().show(ScanLocationActivity.this);
                new Handler().postDelayed(() -> {
                    tvOnline.setText(R.string.OFF_LINE);
                    tvOnline.setTextColor(getResources().getColor(R.color.Red));
                    vDotColors.setVisibility(View.GONE);
                    SessionManager.getInstance().Logout();

                    CommonFunctions.getInstance().ShowSnackBar1(
                            ScanLocationActivity.this, Constants.CheckInternet);

                    CommonFunctions.getInstance().newIntent(ScanLocationActivity.this,
                            LoginActivity.class, Bundle.EMPTY, true);
                    CustomProgressDialog.getInstance().dismiss();
                }, SPLASH_DISPLAY_LENGTH);
            }
        });

        btnScanLocation.setOnClickListener(v -> {
            if (CommonFunctions.getInstance().CheckInternetConnection()) {
                tvOnline.setText(R.string.ONLINE);
                tvOnline.setTextColor(getResources().getColor(R.color.Green));
                vDotColors.setBackgroundResource(R.drawable.dot_view_green);
                vDotColors.setVisibility(View.VISIBLE);
            } else {
                CommonFunctions.getInstance().ShowSnackBar1(
                        ScanLocationActivity.this, Constants.CheckInternet);
                tvOnline.setText(R.string.OFF_LINE);
                tvOnline.setTextColor(getResources().getColor(R.color.Red));
                vDotColors.setVisibility(View.GONE);
            }
        });

        if (CommonFunctions.getInstance().CheckInternetConnection()) {
            stationApiCall();
        } else {
            readData();
        }

        try {
            Realm realmStation = Realm.getDefaultInstance();
            stationWhere = realmStation.where(StationModel.class)
                    .findAll();

            StationApiResponse stationApiObj = new StationApiResponse();
            ArrayList<StationApiResponse.Station> stationList = new ArrayList<>();
            for (int i = 0; i < stationWhere.size(); i++) {
                StationModel data = stationWhere.get(i);
                StationApiResponse.Station stationObj = stationApiObj.new Station();
                stationObj.setName(data.getName());
                stationList.add(stationObj);
                if (stationWhere.get(i).getScanCount() > 0) {
                    tvSavedStation.append(data.getName() + " " + "," + " ");
                } else {
                    System.out.println("scan count not available");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void readData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(Environment.getExternalStorageDirectory() + "/Download/TrackTrace/Stations.json")));
            Gson gson = new Gson();
            response = gson.fromJson(content, StationApiResponse.class);
            try {
                realmNew.executeTransaction(realm -> {
                    realm.delete(StationModel.class);
                    for (int count2 = 0; count2 < response.getStations().size(); count2++) {
                        StationAndLocationDB.getInstance().addStationLocation(
                                response.getStations().get(count2).getId(),     //--- id
                                response.getStations().get(count2).getRfid(),   //--- RFID
                                response.getStations().get(count2).getName(),   //--- StationName
                                0);  //--- StationCount

                    }
                });
                File fileObj = new File(Environment.getExternalStorageDirectory() +
                        "/Download/TrackTrace/Stations.json");
                boolean deleteResult = fileObj.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        btnScanLocation = findViewById(R.id.btnScanLocation);
        vDotColors = findViewById(R.id.vDotColors);
        tvLogout = findViewById(R.id.tvLogout);
        tvOnline = findViewById(R.id.tvOnline);
        ivMenu = findViewById(R.id.ivMenu);
        tvConnectionStatus = findViewById(R.id.tvConnectionStatus);
        tvSavedStation = findViewById(R.id.tvSavedStation);

        ivMenu.setOnClickListener(v -> {
            final Dialog dialog = new Dialog(ScanLocationActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.alert_push);
            dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
            TextView tvPush = dialog.findViewById(R.id.tvPush);
            TextView tvClear = dialog.findViewById(R.id.tvClear);
            tvPush.setOnClickListener(v1 -> {
                tvSavedStation.setText("");
                if (!CommonFunctions.getInstance().CheckInternetConnection()) {
                    CustomProgressDialog.getInstance().show(ScanLocationActivity.this);
                    new Handler().postDelayed(() -> {
                        List<RFIDAndTimeListModel> items = realmNew.where(RFIDAndTimeListModel.class)
                                .equalTo("isSynced", false)
                                .findAll();
                        //Save in file
                        try {
                            save(items);
                            //Change Synced
                            ChangeSynced(items);
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                        CustomProgressDialog.getInstance().dismiss();
                    }, SPLASH_DISPLAY_LENGTH);
                } else {
                    CustomProgressDialog.getInstance().dismiss();
                    SyncWorkOrdersApi();
                }
                dialog.dismiss();
            });
            tvClear.setOnClickListener(v12 -> {
                CustomProgressDialog.getInstance().show(ScanLocationActivity.this);
                new Handler().postDelayed(() -> {
                    Realm realmStation = Realm.getDefaultInstance();
                    stationWhere = realmStation.where(StationModel.class)
                            .findAll();
                    realmNew.executeTransaction(realm -> {
                        for (int k = 0; k < stationWhere.size(); k++) {
                            tvSavedStation.setText("");
                            Integer ScanCount = 0;
                            stationWhere.get(k).setScanCount(ScanCount);
                            StationAndLocationDB.getInstance().addStationLocation(
                                    stationWhere.get(k).getId(),     //--- id
                                    stationWhere.get(k).getRFid(),   //--- RFID
                                    stationWhere.get(k).getName(),
                                    stationWhere.get(k).getScanCount());
                        }
                    });
                    CustomProgressDialog.getInstance().dismiss();
                    CommonFunctions.getInstance().ShowSnackBar(ScanLocationActivity.this,
                            "Data Clear Successfully");
                }, SPLASH_DISPLAY_LENGTH);
                dialog.dismiss();
            });

            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
            wmlp.gravity = Gravity.TOP | Gravity.START;
            wmlp.x = 1;   //x position
            wmlp.y = 100;   //y position
            dialog.show();
        });

    }

    private void stationApiCall() {
        CommonApiCalls.getInstance().stationsList(ScanLocationActivity.this, new APICommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                CustomProgressDialog.getInstance().dismiss();
                response = (StationApiResponse) body;
                stationApiResponseList = response.getStations();
                Log.d("Station Size==>", String.valueOf(response.getStations().size()));
                if (stationApiResponseList != null) {
                    realmNew.executeTransaction(realm -> {
                        //realm.delete(StationModel.class);
                        Realm realmStation = Realm.getDefaultInstance();
                        stationWhere = realmStation.where(StationModel.class)
                                .findAll();

                        for (int count2 = 0; count2 < response.getStations().size(); count2++) {
                            try {
                                if (stationWhere.size() > 0 && stationWhere.get(count2).getScanCount() == 0) {
                                    StationAndLocationDB.getInstance().addStationLocation(
                                            response.getStations().get(count2).getId(),
                                            response.getStations().get(count2).getRfid(),
                                            response.getStations().get(count2).getName(),
                                            0);
                                } else {
                                    StationAndLocationDB.getInstance().addStationLocation(
                                            response.getStations().get(count2).getId(),
                                            response.getStations().get(count2).getRfid(),
                                            response.getStations().get(count2).getName(),
                                            stationWhere.get(count2).getScanCount());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    });
                }
            }

            @Override
            public void onFailure(String reason) {
                Toast.makeText(ScanLocationActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        rfidHandler.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        String status = rfidHandler.onResume();
        tvConnectionStatus.setText(status);
        if (CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (its_detail == true) {
            finish();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        }

        if (CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        if (CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().dismiss();
        } else {
            System.out.println("progress Dialog");
        }
        FragmentManager manager = getFragmentManager();
        int count = manager.getBackStackEntryCount();
        if (count == 1) {
            super.onBackPressed();
        }
        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            CommonFunctions.getInstance().newIntent(ScanLocationActivity.this,
                    LoginActivity.class, Bundle.EMPTY, true);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        rfidHandler.onDestroy();
    }

    @Override
    public void handleTagdata(TagData[] tagData) {
        runOnUiThread(() -> {
            if (TagID == null) {
                for (int index = 0; index < tagData.length; index++) {
                    realm = Realm.getDefaultInstance();
                    StationModel item = realm.where(StationModel.class)
                            .equalTo("RFid", tagData[index].getTagID())
                            .findFirst();
                    if (item != null && item.getId() >= 0) {
                        TagID = tagData[index].getTagID();
                        Intent intent = new Intent(ScanLocationActivity.this,
                                MainActivity.class);
                        intent.putExtra("TagId", tagData[index].getTagID());
                        startActivity(intent);
                        new TimeoutTask().execute();
                        ScanLocationActivity.this.finish();
                        if (rfidHandler.performInventoryStop()) {
                            rfidHandler.stopInventory();
                            disconnect();
                        }
                        rfidHandler.stopInventory();
                        break;
                    }
                }
            }
        });
    }

    @Override
    public void handleTriggerPress(boolean pressed) {
        if (pressed) {
            runOnUiThread(() -> tvConnectionStatus.setText(""));
            rfidHandler.performInventory();
        } else
            rfidHandler.stopInventory();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void SyncWorkOrdersApi() {
        try {
            Realm realm = Realm.getDefaultInstance();
            List<RFIDAndTimeListModel> items = realm.where(RFIDAndTimeListModel.class)
                    .equalTo("isSynced", false)
                    .findAll();

            if (items.size() > 0) {
                JSONArray jsonArray = new JSONArray();
                //start for
                for (int count = 0; count < items.size(); count++) {
                    SyncWorkOrderApiRequest apiResponse = new SyncWorkOrderApiRequest();
                    apiResponse.setRfid(items.get(count).getRfid());
                    apiResponse.setScanTime(changeDateFormatFromAnother(items.get(count).getTime()));
                    apiResponse.setStationId(items.get(count).getStationId());
                    Gson gson = new Gson();
                    String InputData = gson.toJson(apiResponse);
                    JSONObject obj = new JSONObject(InputData);

                    jsonArray.put(obj);
                }
                // end for
                System.out.println("Input Data ==>" + jsonArray);

                CommonApiCalls.getInstance().SyncWorkOrders(ScanLocationActivity.this,
                        jsonArray, new APICommonCallback.Listener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onSuccess(Object body) {
                                responseSync = (SyncWorkOrderApiResponse) body;
                                if (!responseSync.getStatus().equals("Success")) {
                                    //Save in file
                                    try {
                                        save(items);
                                        ChangeSynced(items);
                                    } catch (IOException | JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    ChangeSynced(items);
                                    Realm realm;
                                    realm = Realm.getDefaultInstance();
                                    RealmResults<StationModel> allStations = realm.where(StationModel.class).findAll();
                                    realm.executeTransaction(realm1 -> {
                                        for (int i = 0; i < allStations.size(); i++) {
                                            allStations.get(i).setScanCount(0);
                                            realm1.insertOrUpdate(allStations.get(i));
                                        }
                                    });
                                }
                            }

                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onFailure(String reason) {
                                //Save in file
                                try {
                                    save(items);
                                    ChangeSynced(items);
                                } catch (IOException | JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String changeDateFormatFromAnother(String date) {
        @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String resultDate = "";
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                resultDate = outputFormat.format(inputFormat.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

    public String OfflineChangeDateFormatFromAnother(String date) {
        @SuppressLint("SimpleDateFormat") DateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a");
        @SuppressLint("SimpleDateFormat") DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String resultDate = "";
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                resultDate = outputFormat.format(inputFormat.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void save(List<RFIDAndTimeListModel> items) throws IOException, JSONException {
        //File rootFolder = context.getExternalFilesDir(null);
        String uniqueValue = String.valueOf(new java.sql.Timestamp(System.currentTimeMillis()).getTime());
        File jsonFile = new File(Environment.getExternalStorageDirectory() + "/Download/TrackTrace", "SyncData" + uniqueValue + ".json");
        FileWriter writer = new FileWriter(jsonFile, false);
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < items.size(); i++) {
            JSONObject obj = new JSONObject();
            obj.put("Rfid", items.get(i).getRfid());
            obj.put("stationId", items.get(i).getStationId());
            obj.put("scanTime", OfflineChangeDateFormatFromAnother(items.get(i).getTime()));

            jsonArray.put(obj);
        }
        writer.write(String.valueOf(jsonArray));
        writer.close();

        realm = Realm.getDefaultInstance();
        RealmResults<StationModel> allStations = realm.where(StationModel.class).findAll();
        realm.executeTransaction(realm -> {
            for (int i = 0; i < allStations.size(); i++) {
                allStations.get(i).setScanCount(0);
                realm.insertOrUpdate(allStations.get(i));
            }
        });
        CommonFunctions.getInstance().ShowSnackBar(ScanLocationActivity.this,
                "Push Data Successfully");
    }

    public void ChangeSynced(List<RFIDAndTimeListModel> items) {
        while (items.size() > 0) {
            final RFIDAndTimeListModel syncData = items.get(0);
            Realm realm;
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(realm1 -> {
                syncData.setSynced(true);
                realm1.insertOrUpdate(items);
            });
        }

    }

    @SuppressLint("StaticFieldLeak")
    private class TimeoutTask extends AsyncTask<Object, Void, String> {

        public TimeoutTask() {
        }

        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Object... params) {
            String errorCode = null;
            try {
                Thread.sleep(SCANNING_TIMEOUT);
                abortOperation();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return (errorCode);
        }

        @Override
        protected void onPostExecute(String errorCode) {
        }

    }

    public int abortOperation() {
        if (operationType == 0) {
            mBluetoothAdapter.cancelDiscovery();
        }

        return (0);
    }

    private void disconnect() {
        Log.d(TAG, "closing");

        if (isBt != null) {
            try {
                isBt.close();
            } catch (IOException e) {
                Log.e(TAG, "isBt IOE", e);
            }
            isBt = null;
        }
        if (osBt != null) {
            try {
                osBt.close();
            } catch (IOException e) {
                Log.e(TAG, "osBt IOE", e);
            }
            osBt = null;
        }
        if (mBtSocket != null) {
            try {
                mBtSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "socket IOE", e);
            }
            mBtSocket = null;
        }
        Log.d(TAG, "closed");
    }


    @Override
    public void unregisterActivityLifecycleCallbacks
            (@NonNull Application.ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
        rfidHandler.stopInventory();
    }


}
