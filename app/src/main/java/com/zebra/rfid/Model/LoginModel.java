package com.zebra.rfid.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class LoginModel extends RealmObject {

    private String Password;
    private String AccessToken;
    private String Stations;
    @PrimaryKey
    private String userName;

    public RealmList<LoginModel> loginModelRealmList;

    public LoginModel() {
    }

    public LoginModel(Integer sNo, String users, String Password, String AccessToken, String Stations) {
        this.userName = users;
        this.Password = Password;
        this.AccessToken = AccessToken;
        this.Stations = Stations;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public String getStations() {
        return Stations;
    }

    public void setStations(String stations) {
        Stations = stations;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
