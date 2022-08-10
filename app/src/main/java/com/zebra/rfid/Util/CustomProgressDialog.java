package com.zebra.rfid.Util;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.LinearLayout;
import com.zebra.rfid.demo.sdksample.R;


public class CustomProgressDialog {
    private static CustomProgressDialog ourInstance = new CustomProgressDialog();
    Dialog dialog;

    public static CustomProgressDialog getInstance() {
        return ourInstance;
    }

    public CustomProgressDialog() {

    }

    public void show(Context context) {

        try {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.pdialog);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            LinearLayout mkLoader = dialog.findViewById(R.id.llView);
            mkLoader.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public boolean isShowing() {
        return dialog != null && dialog.isShowing();
    }
}
