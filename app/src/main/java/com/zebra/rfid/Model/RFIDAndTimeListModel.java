package com.zebra.rfid.Model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RFIDAndTimeListModel extends RealmObject implements Serializable {

    @Expose
    @PrimaryKey
    private String Rfid;
    @Expose
    private Integer stationId;
    @Expose
    private String Time;

    private boolean isSelected;
    private boolean isSynced;

    public RFIDAndTimeListModel(){

    }

    public RFIDAndTimeListModel(String rfid, String time, boolean isSelected, boolean isSynced) {
        this.Rfid = rfid;
        this.Time = time;
        this.isSelected = isSelected;
        this.isSynced = isSynced;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getRfid() {
        return Rfid;
    }

    public void setRfid(String rfid) {
        Rfid = rfid;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }
    public boolean getSynced() {
        return isSynced;
    }
    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

}
