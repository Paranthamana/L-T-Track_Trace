package com.zebra.rfid.RealmDB;

import com.zebra.rfid.Model.StationModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class StationAndLocationDB {

    private static StationAndLocationDB ourInstance = new StationAndLocationDB();


    public static StationAndLocationDB getInstance() {
        return ourInstance;
    }

    public void addStationLocation(Integer id, String Rfid, String Name, Integer ScanCount) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              StationModel stationModel = new StationModel(
                                                      id, Rfid, Name,ScanCount);
                                              realm.insertOrUpdate(stationModel);
                                          }
                                      }
        );
    }

    public void deleteIdList(final Integer ScanCount) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
                                          @Override
                                          public void execute(Realm realm) {
                                              StationModel item = realm.where(StationModel.class)
                                                      .equalTo("ScanCount", ScanCount)
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

    public List<StationModel> getParticularUser(int id, String RFid, String Name) {
        Realm realm = Realm.getDefaultInstance();
        List<StationModel> item = realm.where(StationModel.class)
                .equalTo("id", id)
                .equalTo("RFid", RFid)
                .equalTo("Name", Name)
                .findAll();
        return item;
    }
}
