package com.zebra.rfid.RealmDB;

public class CommonCallback<L> {
    public interface Listener {
        public void onSuccess();
        public void onFailure();
    }

    private Listener listener;
}
