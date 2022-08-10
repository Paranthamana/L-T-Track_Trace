package io.realm;


public interface LoginModelRealmProxyInterface {
    public String realmGet$Password();
    public void realmSet$Password(String value);
    public String realmGet$AccessToken();
    public void realmSet$AccessToken(String value);
    public String realmGet$Stations();
    public void realmSet$Stations(String value);
    public String realmGet$userName();
    public void realmSet$userName(String value);
    public RealmList<com.zebra.rfid.Model.LoginModel> realmGet$loginModelRealmList();
    public void realmSet$loginModelRealmList(RealmList<com.zebra.rfid.Model.LoginModel> value);
}
