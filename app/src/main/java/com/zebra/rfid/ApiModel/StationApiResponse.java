package com.zebra.rfid.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StationApiResponse {
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("stations")
    @Expose
    private List<Station> stations = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public class Station {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("isActive")
        @Expose
        private Boolean isActive;
        @SerializedName("rfid")
        @Expose
        private String rfid;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("createdUserId")
        @Expose
        private Integer createdUserId;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("modifiedUserId")
        @Expose
        private Object modifiedUserId;
        @SerializedName("modifiedDate")
        @Expose
        private Object modifiedDate;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public String getRfid() {
            return rfid;
        }

        public void setRfid(String rfid) {
            this.rfid = rfid;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCreatedUserId() {
            return createdUserId;
        }

        public void setCreatedUserId(Integer createdUserId) {
            this.createdUserId = createdUserId;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public Object getModifiedUserId() {
            return modifiedUserId;
        }

        public void setModifiedUserId(Object modifiedUserId) {
            this.modifiedUserId = modifiedUserId;
        }

        public Object getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Object modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

    }


}
