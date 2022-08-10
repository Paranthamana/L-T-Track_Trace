package com.zebra.rfid.RealmDB;

import com.zebra.rfid.Model.RFIDAndTimeListModel;

import java.util.List;

import io.realm.Realm;

public class RFIDAndTimeListDB {

    private static RFIDAndTimeListDB ourInstance = new RFIDAndTimeListDB();


    public static RFIDAndTimeListDB getInstance() {
        return ourInstance;
    }

    public void addIdTimeList(String Rfid,String Time, boolean isSelected, boolean sync, final CommonCallback.Listener listener){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              RFIDAndTimeListModel timeListDB = new RFIDAndTimeListModel(Rfid, Time,isSelected,sync);
                                              realm.insertOrUpdate(timeListDB);
                                          }
                                      }, listener::onSuccess, error -> listener.onFailure()
        );
    }

    public void DeleteItem(final String key) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              RFIDAndTimeListModel item = realm.where(RFIDAndTimeListModel.class)
                                                      .equalTo("Rfid", key)
                                                      .findFirst();

                                              if (item != null) {
                                                  item.deleteFromRealm();
                                                  System.out.println ("remove items" +item);
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

    public void deleteIdList(final String id) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RFIDAndTimeListModel item = realm.where(RFIDAndTimeListModel.class).equalTo("Rfid", id).findFirst();
        if(item!=null){
            item.deleteFromRealm();
        }
        realm.commitTransaction();
    }


    public List<RFIDAndTimeListModel> getRFidTimeList() {
        Realm realm = Realm.getDefaultInstance();
        List<RFIDAndTimeListModel> item = realm.where(RFIDAndTimeListModel.class).findAll();
        return item;
    }

    public List<RFIDAndTimeListModel> getIDAndTime(String mRfid, String mTime) {
        Realm realm = Realm.getDefaultInstance();
        List<RFIDAndTimeListModel> item = realm.where(RFIDAndTimeListModel.class)
                .equalTo("Rfid", mRfid)
                .equalTo("Time", mTime)
                .findAll();
        return item;
    }

    public int getIDSize() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(RFIDAndTimeListModel.class).findAll().size();
    }
}
