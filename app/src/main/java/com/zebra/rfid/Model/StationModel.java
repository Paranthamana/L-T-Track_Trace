package com.zebra.rfid.Model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class StationModel extends RealmObject implements Serializable {

    @PrimaryKey
    private Integer id; //---station id
    private String RFid;  //-- Rfid
    private String Name;
    private Integer ScanCount;

    public StationModel(){

    }

    public StationModel(Integer id, String rfid, String name,Integer ScanCount) {
        this.id = id;
        this.Name = name;
        this.RFid = rfid;
        this.ScanCount = ScanCount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getRFid() {
        return RFid;
    }

    public void setRFid(String RFid) {
        this.RFid = RFid;
    }

    public int getScanCount() {
        return ScanCount;
    }

    public void setScanCount(int scanCount) {
        ScanCount = scanCount;
    }
}
