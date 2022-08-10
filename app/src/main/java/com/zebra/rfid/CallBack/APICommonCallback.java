package com.zebra.rfid.CallBack;

public class APICommonCallback {
    public interface Listener {
        public void onSuccess(Object body);
        public void onFailure(String reason);
    }

    private Listener listener;
}
