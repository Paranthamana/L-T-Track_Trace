package com.zebra.rfid.RealmDB;

import static io.realm.internal.SyncObjectServerFacade.getApplicationContext;

import android.content.Context;
import android.widget.Toast;

import com.zebra.rfid.Model.LoginModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class LoginDB {
    private static LoginDB ourInstance = new LoginDB();

    public static LoginDB getInstance() {
        return ourInstance;
    }

    public void addLogin(String sNo, String UserName, String Password, String accessToken, String stations,
                         final CommonCallback.Listener callback) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              Number currentSNo = realm.where(LoginModel.class).max("sNo");
                                              int nextId;
                                              if (currentSNo == null) {
                                                  nextId = 1;
                                              } else {
                                                  nextId = currentSNo.intValue() + 1;
                                              }
                                              Integer sNO = Integer.parseInt(sNo);
                                              LoginModel loginModel = new LoginModel(sNO, UserName, Password, accessToken, stations);
                                              realm.insertOrUpdate(loginModel);
                                          }
                                      }, callback::onSuccess, error -> callback.onFailure()
        );
    }


    public void DeleteUsers(final String Users) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              LoginModel item = realm.where(LoginModel.class)
                                                      .equalTo("Users", Users)
                                                      .findFirst();
                                              if (item != null) {
                                                  item.deleteFromRealm();
                                              }
                                          }
                                      }, new Realm.Transaction.OnSuccess() {
                                          @Override
                                          public void onSuccess() {
                                          }
                                      }, new Realm.Transaction.OnError() {
                                          @Override
                                          public void onError(Throwable error) {
                                          }
                                      }
        );
    }

    public List<LoginModel> getAllItems() {
        Realm realm = Realm.getDefaultInstance();
        List<LoginModel> item = realm.where(LoginModel.class).findAll();
        return item;
    }

    public List<LoginModel> getParticularUser(String mUsername, String mPassword) {
        Realm realm = Realm.getDefaultInstance();
        List<LoginModel> item = realm.where(LoginModel.class)
                .equalTo("userName", mUsername)
                .equalTo("Password", mPassword)
                .findAll();
        return item;
    }

    public void deleteItem(final String id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        LoginModel item = realm.where(LoginModel.class).equalTo("Users", id).findFirst();
        if (item != null) {
            item.deleteFromRealm();
        }
        realm.commitTransaction();
    }

}
