package com.zebra.rfid.Activity;

import android.Manifest;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.zebra.rfid.Api.CommonApiCalls;
import com.zebra.rfid.ApiModel.LoginApiResponse;
import com.zebra.rfid.CallBack.APICommonCallback;
import com.zebra.rfid.CommonFunctions.CommonFunctions;
import com.zebra.rfid.CommonFunctions.Constants;
import com.zebra.rfid.CommonFunctions.MyApplication;
import com.zebra.rfid.CommonFunctions.SessionManager;
import com.zebra.rfid.Model.LoginModel;
import com.zebra.rfid.RealmDB.CommonCallback;
import com.zebra.rfid.RealmDB.LoginDB;
import com.zebra.rfid.Util.CustomProgressDialog;
import com.zebra.rfid.demo.sdksample.R;

import java.util.List;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    ImageView ivBanner;
    TextView tvLoginTOAccount;
    EditText edtUserName;
    EditText edtPassword;
    CheckBox cbRememberMe;
    Button btnLogin;
    TextView tvRememberMe;
    TextView tvErrorUserName;
    TextView tvErrorPassword;
    TextView tvUSER;
    boolean isRemember = false;
    Boolean doubleBackToExitPressedOnce = false;
    Realm realm;
    private String user;
    private String pass;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance();
        if (MyApplication.context == null) {
            MyApplication.context = getApplicationContext();
        }
        Constants.getInstance().languageConstants();
        initView();
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
         /*Admin- Admin@4321,Superadmin- Superadmin@321,Operator1- Operator1@111,Operator2- Operator2@2222*/
    }

    private void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(LoginActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{permission}, requestCode);
        } else {
            System.out.println("Permission already granted");
        }
    }

    private void initView() {

        ivBanner = findViewById(R.id.ivBanner);
        tvLoginTOAccount = findViewById(R.id.tvLoginTOAccount);
        tvRememberMe = findViewById(R.id.tvRememberMe);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        btnLogin = findViewById(R.id.btnLogin);
        tvErrorUserName = findViewById(R.id.tvErrorUserName);
        tvErrorPassword = findViewById(R.id.tvErrorPassword);
        tvUSER = findViewById(R.id.tvUSER);

        if (!CommonFunctions.getInstance().CheckInternetConnection()) {
            CommonFunctions.getInstance().ShowSnackBar1(LoginActivity.this,
                    Constants.CheckInternet);
        } else {
            System.out.println("Internet Connected");
            edtUserName.setText(user);
            edtPassword.setText(pass);
        }

        user = SessionManager.getData(SessionManager.USERNAME, "");
        pass = SessionManager.getData(SessionManager.PASSWORD, "");
        if (!user.equals("") && !pass.equals("")) {
            edtUserName.setText(user);
            edtPassword.setText(pass);
            isRemember = true;
        }

        cbRememberMe.setOnClickListener(v -> {
            if (!isRemember) {
                cbRememberMe.setChecked(true);
            } else {
                cbRememberMe.setChecked(false);
            }
            isRemember = !isRemember;
        });

        tvRememberMe.setOnClickListener(v ->
                CommonFunctions.getInstance().ShowSnackBar1(LoginActivity.this,
                        Constants.RememberMe));

        btnLogin.setOnClickListener(v -> {
            CommonFunctions.getInstance().HideSoftKeyboard(LoginActivity.this);
            if (isRemember) {
                SessionManager.setData(SessionManager.USERNAME, edtUserName.
                        getText().toString());
                SessionManager.setData(SessionManager.PASSWORD, edtPassword.
                        getText().toString());
            } else {
                SessionManager.setData(SessionManager.USERNAME, "");
                SessionManager.setData(SessionManager.PASSWORD, "");
            }

            if (edtUserName.getText().toString().trim().length() == 0 &&
                    edtPassword.getText().toString().trim().length() == 0) {
                tvErrorUserName.setVisibility(View.VISIBLE);
                tvErrorUserName.setText(Constants.UserNameEmpty);
                tvErrorPassword.setVisibility(View.VISIBLE);
                tvErrorPassword.setText(Constants.PasswordEmpty);
            }
            if (!edtUserName.getText().toString().isEmpty()) {
                tvErrorUserName.setVisibility(View.INVISIBLE);
            }
            if (!edtPassword.getText().toString().trim().isEmpty()) {
                tvErrorPassword.setVisibility(View.INVISIBLE);
            }

            if (edtUserName.getText().toString().trim().length() == 0) {
                tvErrorUserName.setVisibility(View.VISIBLE);
                tvErrorUserName.setText(Constants.UserNameEmpty);
            } else if (edtUserName.getText().toString().trim().isEmpty()) {
                tvErrorUserName.setVisibility(View.VISIBLE);
                tvErrorUserName.setText(Constants.UserNameEmpty);
            } else if (edtPassword.getText().toString().trim().isEmpty()) {
                CommonFunctions.getInstance().ShowSnackBar(
                        LoginActivity.this, Constants.PasswordEmpty);
                tvErrorPassword.setVisibility(View.VISIBLE);
                tvErrorPassword.setText(Constants.PasswordEmpty);
            } else {
                if (!CommonFunctions.getInstance().CheckInternetConnection()) {
                    if (edtUserName.getText().toString().trim().equalsIgnoreCase("Admin") &&
                            edtPassword.getText().toString().equals("Admin@4321")) {
                        CommonFunctions.getInstance().newIntent(LoginActivity.this,
                                ScanLocationActivity.class, Bundle.EMPTY, true);
                    } else if (edtUserName.getText().toString().trim().equalsIgnoreCase("Superadmin") &&
                            edtPassword.getText().toString().equals("Superadmin@321")) {
                        CommonFunctions.getInstance().newIntent(LoginActivity.this,
                                ScanLocationActivity.class, Bundle.EMPTY, true);
                    } else if (edtUserName.getText().toString().trim().equalsIgnoreCase("Operator1") &&
                            edtPassword.getText().toString().equals("Operator1@111")) {
                        CommonFunctions.getInstance().newIntent(LoginActivity.this,
                                ScanLocationActivity.class, Bundle.EMPTY, true);
                    } else if (edtUserName.getText().toString().trim().equalsIgnoreCase("Operator2") &&
                            edtPassword.getText().toString().equals("Operator2@2222")) {
                        CommonFunctions.getInstance().newIntent(LoginActivity.this,
                                ScanLocationActivity.class, Bundle.EMPTY, true);
                    } else {
                        CommonFunctions.getInstance().ShowSnackBar1(LoginActivity.this,
                                Constants.checkUserNameAndPassword);
                    }
                } else {
                    CommonFunctions.getInstance().ShowSnackBar1(LoginActivity.this,
                            Constants.YouAreInOnline);
                }
                    /*else {
                    //realm = Realm.getDefaultInstance();
                    // Realm realm = Realm.getDefaultInstance();
                    tvErrorUserName.setVisibility(View.INVISIBLE);
                    tvErrorPassword.setVisibility(View.INVISIBLE);
                    CommonFunctions.getInstance().HideSoftKeyboard(LoginActivity.this);
                    LoginApiResponse apiResponse = new LoginApiResponse();
                    apiResponse.setUsername(edtUserName.getText().toString().trim());
                    apiResponse.setPassword(edtPassword.getText().toString().trim());
                    Gson gson = new Gson();
                    String InputData = gson.toJson(apiResponse);
                    System.out.println("Input ==> " + InputData);
                    final LoginModel model = new LoginModel();
                    CommonApiCalls.getInstance().login(LoginActivity.this,
                            InputData, new APICommonCallback.Listener() {
                                @Override
                                public void onSuccess(Object object) {
                                    CustomProgressDialog.getInstance().dismiss();
                                    LoginApiResponse body = (LoginApiResponse) object;
                                    realm.executeTransaction(realm -> {
                                        SessionManager.getInstance().userDetails(body.getUsername(),
                                                body.getToken(),
                                                body.getId().toString());

                                        LoginDB.getInstance().addLogin("1", body.getUsername(),
                                                edtPassword.getText().toString(),
                                                SessionManager.getInstance().getAccessToken(),
                                                String.valueOf(body.getStations()), new
                                                        CommonCallback.Listener() {
                                                            @Override
                                                            public void onSuccess() {
                                                            }

                                                            @Override
                                                            public void onFailure() {
                                                            }
                                                        });
                                        model.setPassword(edtPassword.getText().toString());
                                        model.setAccessToken(body.getToken());
                                        model.setUserName(edtUserName.getText().toString());
                                        model.setStations(String.valueOf(body.getStations()));

                                        //showData();
                                        realm.insertOrUpdate(model);

                                        if (edtUserName.getText().toString().equals(model.getUserName()) &&
                                                edtPassword.getText().toString().equals(model.getPassword())) {

                                            CommonFunctions.getInstance().newIntent(LoginActivity.this,
                                                    ScanLocationActivity.class, Bundle.EMPTY, true);
                                        } else if (body.getUsername().equals(edtUserName.getText().toString())) {
                                            CommonFunctions.getInstance().newIntent(LoginActivity.this,
                                                    ScanLocationActivity.class, Bundle.EMPTY, true);
                                        } else {
                                            System.out.println("No more Data provide for Login");
                                        }
                                        System.out.println("UserName:" + model.getUserName());
                                        System.out.println("Password:" + model.getPassword());
                                        System.out.println("Token:" + model.getAccessToken());
                                        System.out.println("Stations:" + model.getStations());
                                    });

                                }

                                @Override
                                public void onFailure(String reason) {
                                    CustomProgressDialog.getInstance().dismiss();
                                    CommonFunctions.getInstance().ShowSnackBar(LoginActivity.this,
                                            "Check UserName and Password");
                                }
                            });
                }*/
                //}

            }

            //Realm realm = Realm.getDefaultInstance();
            List<LoginModel> item = realm.where(LoginModel.class)
                    .equalTo("userName", edtUserName.getText().toString())
                    .equalTo("Password", edtPassword.getText().toString())
                    .findAll();
            for (int count = 0; count < item.size(); count++) {
                if (count <= 3) {
                    if (item.get(count).getUserName().isEmpty()) {
                        CommonFunctions.getInstance().ShowSnackBar(LoginActivity.this,
                                Constants.checkUserNameAndPassword);
                    } else if (item.get(count).getPassword().isEmpty()) {
                        CommonFunctions.getInstance().ShowSnackBar(LoginActivity.this,
                                Constants.checkUserNameAndPassword);
                    }

                    if (!item.get(count).getUserName().equals(edtUserName.getText().toString())) {
                        CommonFunctions.getInstance().ShowSnackBar(LoginActivity.this,
                                Constants.checkUserNameAndPassword);
                    } else if (!item.get(count).getPassword().equals(edtPassword.getText().toString())) {
                        CommonFunctions.getInstance().ShowSnackBar(LoginActivity.this,
                                Constants.checkUserNameAndPassword);
                    }/* else {
                        CommonFunctions.getInstance().newIntent(LoginActivity.this,
                                ScanLocationActivity.class, Bundle.EMPTY, true);
                    }*/
                } else {
                    CommonFunctions.getInstance().ShowSnackBar(LoginActivity.this,
                            Constants.checkUserNameAndPassword);
                    LoginDB.getInstance().DeleteUsers(item.get(count).getUserName());
                }
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                System.out.println("Storage Permission Granted");
            } else {
                System.out.println("Storage Permission Denied");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().dismiss();
        } else {
            System.out.println("progress Dialog");
        }
        if (isRemember) {
            cbRememberMe.setChecked(true);
        } else {
            cbRememberMe.setChecked(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (CustomProgressDialog.getInstance().isShowing()) {
            CustomProgressDialog.getInstance().dismiss();
        } else {
            System.out.println("progress Dialog");
        }
            moveTaskToBack(true);

        /*FragmentManager manager = getFragmentManager();
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
            CommonFunctions.getInstance().ShowSnackBar(LoginActivity.this,
                    Constants.pleaseClickBackAgainToExit);
        }*/
    }
}