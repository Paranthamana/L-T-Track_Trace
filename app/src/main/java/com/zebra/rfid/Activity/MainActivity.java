package com.zebra.rfid.Activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zebra.rfid.Adapter.RFIdTimeAdapter;
import com.zebra.rfid.Adapter.WorkStationsAdapter;
import com.zebra.rfid.ApiModel.StationApiResponse;
import com.zebra.rfid.CommonFunctions.CommonFunctions;
import com.zebra.rfid.Model.RFIDAndTimeListModel;
import com.zebra.rfid.Model.StationModel;
import com.zebra.rfid.Util.CustomProgressDialog;
import com.zebra.rfid.api3.TagData;
import com.zebra.rfid.demo.sdksample.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RFIDHandler.ResponseHandlerInterface,
        View.OnClickListener {

    public TextView statusTextViewRFID = null;
    private TextView textRfid;
    private TextView testStatus;
    private RecyclerView rvRfIDTagView;
    private RecyclerView rvStations;
    private TextView tvOnline;
    private View vDotColors;
    private CheckBox cbSelectAll;
    private ImageView IvDelete;
    private TextView tvIdCount;
    LinearLayout llSave;
    private ArrayList<String> RFidTagValueList = new ArrayList<>();
    private final ArrayList<String> RFidList = new ArrayList<>();
    private RFIdTimeAdapter mTimeAdapter;
    private WorkStationsAdapter mWorkStationsAdapter;
    Realm realm;
    private final ArrayList<RFIDAndTimeListModel> timeListModels = new ArrayList<>();
    public int position = -1;
    private int counter = 0;

    RFIDHandler rfidHandler;
    private String listCount;
    public boolean isActionMode = false;

    private StationModel item;
    private RealmResults<StationModel> stationWhere;
    private static final long SPLASH_DISPLAY_LENGTH = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();

        Realm realm = Realm.getDefaultInstance();
        item = realm.where(StationModel.class)
                .equalTo("RFid", getIntent().getStringExtra("TagId"))
                .findFirst();

        System.out.println("Scan Tag details " + item);
        CommonFunctions.getInstance().ShowSnackBar1(MainActivity.this,
                "Please Wait until Beep Sound connect with reader");

        // UI
        initView();

        // RFID Handler
        rfidHandler = new RFIDHandler();
        rfidHandler.onCreate(this);

        IvDelete.setBackground(getResources().getDrawable(R.drawable.bg_stations));
        if (CommonFunctions.getInstance().CheckInternetConnection()) {
            tvOnline.setText(R.string.ONLINE);
            tvOnline.setTextColor(getResources().getColor(R.color.Orange));
            vDotColors.setVisibility(View.VISIBLE);
            vDotColors.setBackgroundResource(R.drawable.dot_view_green);
        } else {
            tvOnline.setText(R.string.OFF_LINE);
            tvOnline.setTextColor(getResources().getColor(R.color.Red));
            vDotColors.setVisibility(View.GONE);
        }

        /*Button test = findViewById(R.id.button);
        test.setOnClickListener(v -> {
            String result = rfidHandler.Test1();
            testStatus.setText(result);
        });

        Button test2 = findViewById(R.id.button2);
        test2.setOnClickListener(v -> {
            String result = rfidHandler.Test2();
            testStatus.setText(result);
        });

        Button defaultButton = findViewById(R.id.button3);
        defaultButton.setOnClickListener(v -> {
            String result = rfidHandler.Defaults();
            testStatus.setText(result);
        });*/

        try {
            Realm realmStation = Realm.getDefaultInstance();
            stationWhere = realmStation.where(StationModel.class)
                    .findAll();

            StationApiResponse stationApiObj = new StationApiResponse();
            ArrayList<StationApiResponse.Station> stationList = new ArrayList<>();
            for (int i = 0; i < stationWhere.size(); i++) {
                StationModel data = stationWhere.get(i);
                StationApiResponse.Station stationObj = stationApiObj.new Station();
                stationObj.setRfid(data.getRFid());
                stationObj.setName(data.getName());
                stationObj.setId(data.getId());
                stationList.add(stationObj);

            }

            mWorkStationsAdapter = new WorkStationsAdapter(
                    MainActivity.this, stationWhere, item, listCount);

            RecyclerView.LayoutManager manager = new LinearLayoutManager
                    (MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
            rvStations.setLayoutManager(manager);
            rvStations.setAdapter(mWorkStationsAdapter);
            GridLayoutManager layoutManager = new GridLayoutManager(
                    MainActivity.this, 2,
                    GridLayoutManager.HORIZONTAL, false);
            rvStations.setLayoutManager(layoutManager);

        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvRfIDTagView.setLayoutManager(llm);

        mTimeAdapter = new RFIdTimeAdapter(MainActivity.this, RFidList,
                RFidTagValueList, realm, item);
        rvRfIDTagView.setAdapter(mTimeAdapter);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void initView() {
        tvIdCount = findViewById(R.id.tvIdCount);
        TextView tvBack = findViewById(R.id.tvBack);
        tvOnline = findViewById(R.id.tvOnline);
        vDotColors = findViewById(R.id.vDotColors);
        rvStations = findViewById(R.id.rvStations);
        cbSelectAll = findViewById(R.id.cbSelectAll);
        IvDelete = findViewById(R.id.IvDelete);
        llSave = findViewById(R.id.llSave);
        statusTextViewRFID = findViewById(R.id.textStatus);
        textRfid = findViewById(R.id.textViewdata);
        testStatus = findViewById(R.id.testStatus);
        rvRfIDTagView = findViewById(R.id.rvRfIDTagView);
        rvStations = findViewById(R.id.rvStations);
        tvIdCount.setText("(0)");

        tvBack.setOnClickListener(v -> {
            if (RFidTagValueList.size() == 0) {
                backAlertDialog();
            } else {
                withoutSaveBackAlertDialog();
                System.out.println("Count==>" + "scan count available");
            }
        });

        cbSelectAll.setOnClickListener(v -> {
            if (cbSelectAll.isChecked()) {
                IvDelete.setClickable(true);
                IvDelete.setBackground(getResources().getDrawable(R.drawable.delete_highlight_work_order));
            } else {
                //IvDelete.setClickable(false);
                IvDelete.setBackground(getResources().getDrawable(R.drawable.bg_stations));
            }
            mTimeAdapter.notifyDataSetChanged();
        });

        IvDelete.setOnClickListener(v -> {
            if (CommonFunctions.getInstance().CheckInternetConnection()) {
                tvOnline.setText(R.string.ONLINE);
                tvOnline.setTextColor(getResources().getColor(R.color.Orange));
                vDotColors.setVisibility(View.VISIBLE);
                vDotColors.setBackgroundResource(R.drawable.dot_view_green);
            } else {
                tvOnline.setText(R.string.OFF_LINE);
                tvOnline.setTextColor(getResources().getColor(R.color.Red));
                vDotColors.setVisibility(View.GONE);
            }
            btnDeleteAlertDialog();
        });


        llSave.setOnClickListener(v -> {
            CustomProgressDialog.getInstance().show(MainActivity.this);
            if (timeListModels.size() > 0) {
                new Handler().postDelayed(() -> {
                    for (int i = 0; i < timeListModels.size(); i++) {
                        final RFIDAndTimeListModel syncData = timeListModels.get(i);
                        //syncData.setScanCount(timeListModels.size());
                        Realm realm;
                        realm = Realm.getDefaultInstance();
                        realm.executeTransaction(realm1 -> {
                            //syncData.setScanCount(syncData.getScanCount());
                            realm1.insertOrUpdate(syncData);
                        });
                    }

                    CommonFunctions.getInstance().newIntent(MainActivity.this,
                            ScanLocationActivity.class, Bundle.EMPTY, true);
                    CustomProgressDialog.getInstance().dismiss();
                }, SPLASH_DISPLAY_LENGTH);

                Realm realm;
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(realm12 -> {
                    final StationModel currentStation = realm12.where(StationModel.class)
                            .equalTo("RFid", item.getRFid()).findFirst();
                    currentStation.setScanCount(timeListModels.size());
                    realm12.insertOrUpdate(currentStation);
                });

            } else {
                CommonFunctions.getInstance().ShowSnackBar1(MainActivity.this,
                        "Please scan and proceed Next action");
                CustomProgressDialog.getInstance().dismiss();
            }
        });
    }

    private void withoutSaveBackAlertDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_back);
        dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        TextView text = dialog.findViewById(R.id.tvBackAlertText);
        text.setText(R.string.ScanNotSaved);
        Button btnCancel = dialog.findViewById(R.id.btnBackCancel);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        dialog.show();
        btnCancel.setOnClickListener(v1 -> dialog.dismiss());
        btnOk.setOnClickListener(v ->
                CommonFunctions.getInstance().newIntent
                        (MainActivity.this, ScanLocationActivity.class,
                        Bundle.EMPTY, true));

    }

    private void backAlertDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_back);
        dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        TextView text = dialog.findViewById(R.id.tvBackAlertText);
        text.setText(R.string.BackAlertMessage);
        Button btnCancel = dialog.findViewById(R.id.btnBackCancel);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        dialog.show();
        btnCancel.setOnClickListener(v1 -> dialog.dismiss());
        btnOk.setOnClickListener(v ->
                CommonFunctions.getInstance().newIntent(
                        MainActivity.this, ScanLocationActivity.class,
                        Bundle.EMPTY, true));

    }


    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    private void btnDeleteAlertDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.alert_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        TextView text = dialog.findViewById(R.id.tvAreYouSure);
        text.setText(R.string.Are_Sure_to_delete);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnDelete = dialog.findViewById(R.id.btnDelete);
        btnCancel.setOnClickListener(v1 -> dialog.dismiss());
        dialog.show();
        btnDelete.setOnClickListener(v -> {
            CustomProgressDialog.getInstance().show(MainActivity.this);

            if (RFidList.size() > 0) {
                for (String s : RFidList) {
                    RFidTagValueList.remove(s);
                    timeListModels.remove(s);
                }
                RFidList.removeAll(RFidList);
                timeListModels.removeAll(RFidList);
                ArrayList<String> TempRFidTagValueList = new ArrayList<>();
                for (int i = 0; i < RFidTagValueList.size(); i++) {
                    TempRFidTagValueList.add(RFidTagValueList.get(i));
                }
                RFidTagValueList.removeAll(RFidTagValueList);
                mTimeAdapter.notifyDataSetChanged();
                RFidTagValueList = TempRFidTagValueList;
                mTimeAdapter.notifyDataSetChanged();

                LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                mTimeAdapter = new RFIdTimeAdapter(MainActivity.this, RFidList,
                        RFidTagValueList, realm, item);
                rvRfIDTagView.setAdapter(mTimeAdapter);
                listCount = String.valueOf(RFidTagValueList.size());
                tvIdCount.setText("(" + listCount + ")");
                mTimeAdapter.notifyDataSetChanged();

                mWorkStationsAdapter = new WorkStationsAdapter(
                        MainActivity.this, stationWhere, item, listCount
                );
                RecyclerView.LayoutManager manager = new LinearLayoutManager
                        (MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                rvStations.setLayoutManager(manager);
                rvStations.setAdapter(mWorkStationsAdapter);
                GridLayoutManager layoutManager = new GridLayoutManager(
                        MainActivity.this, 2,
                        GridLayoutManager.HORIZONTAL, false);
                rvStations.setLayoutManager(layoutManager);
                mWorkStationsAdapter.notifyDataSetChanged();

            }

            mTimeAdapter.notifyDataSetChanged();
            dialog.dismiss();

            CustomProgressDialog.getInstance().dismiss();
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        statusTextViewRFID.setText(status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rfidHandler.onDestroy();
    }


    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void handleTagdata(TagData[] tagData) {
        runOnUiThread(() -> {
            for (int index = 0; index < tagData.length; index++) {
                if (!tagData[index].getTagID().equals(null) &&
                        !tagData[index].getTagID().equals("") &&
                        !RFidTagValueList.contains(tagData[index].getTagID()) &&
                        !IsStationRFID(tagData[index].getTagID())) {


                    RFidTagValueList.add(tagData[index].getTagID());
                    RFIDAndTimeListModel model = new RFIDAndTimeListModel();
                    model.setRfid(tagData[index].getTagID());
                    model.setStationId(item.getId());
                    model.setSynced(false);
                    model.setTime(SimpleDateFormat.getDateTimeInstance().format(new Date()));
                    timeListModels.add(model);

                    LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    mTimeAdapter = new RFIdTimeAdapter(MainActivity.this, RFidList,
                            RFidTagValueList, realm, item);
                    rvRfIDTagView.setAdapter(mTimeAdapter);
                    listCount = String.valueOf(RFidTagValueList.size());
                    tvIdCount.setText("(" + listCount + ")");
                    mTimeAdapter.notifyDataSetChanged();


                    mWorkStationsAdapter = new WorkStationsAdapter(
                            MainActivity.this, stationWhere, item, listCount
                    );
                    RecyclerView.LayoutManager manager = new LinearLayoutManager
                            (MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    rvStations.setLayoutManager(manager);
                    rvStations.setAdapter(mWorkStationsAdapter);
                    GridLayoutManager layoutManager = new GridLayoutManager(
                            MainActivity.this, 2,
                            GridLayoutManager.HORIZONTAL, false);
                    rvStations.setLayoutManager(layoutManager);
                    mWorkStationsAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    public boolean IsStationRFID(String RFid) {
        for (int i = 0; i < stationWhere.size(); i++) {
            if (stationWhere.get(i).getRFid() != null && stationWhere.get(i).getRFid().equals(RFid))
                return true;
        }
        return false;
    }


    @Override
    public void handleTriggerPress(boolean pressed) {
        if (pressed) {
            runOnUiThread(() -> textRfid.setText(""));
            rfidHandler.performInventory();
        } else
            rfidHandler.stopInventory();
    }

    @Override
    public void onBackPressed() {
        try {
            if (RFidTagValueList.size() == 0) {
                backAlertDialog();
            } else {
                withoutSaveBackAlertDialog();
                System.out.println("Count==>" + "scan count available");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void unregisterActivityLifecycleCallbacks(@NonNull Application.ActivityLifecycleCallbacks callback) {
        super.unregisterActivityLifecycleCallbacks(callback);
        rfidHandler.stopInventory();
    }

    @Override
    public void onClick(View v) {
        isActionMode = true;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void startSelection(int index) {
        if (!isActionMode) {
            isActionMode = true;
            RFidList.add(RFidTagValueList.get(index));
            counter++;
            position = index;
            mTimeAdapter.notifyDataSetChanged();
        }
    }

    public void check(View view, int index) {
        if (((CheckBox) view).isChecked()) {
            IvDelete.setBackground(getResources().getDrawable(R.drawable.delete_highlight_work_order));
            RFidList.add(RFidTagValueList.get(index));
            // counter++;
        } else {
            IvDelete.setBackground(getResources().getDrawable(R.drawable.bg_stations));
            RFidList.remove(RFidTagValueList.get(index));
            //counter--;

        }
    }
}
