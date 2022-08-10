package com.zebra.rfid.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncWorkOrderApiRequest {


    public SyncWorkOrderApiRequest() {

    }


    @SerializedName("rfid")
    @Expose
    private String rfid;
    @SerializedName("stationId")
    @Expose
    private Integer stationId;
    @SerializedName("scanTime")
    @Expose
    private String scanTime;

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getScanTime() {
        return scanTime;
    }

    public void setScanTime(String scanTime) {
        this.scanTime = scanTime;
    }


}
