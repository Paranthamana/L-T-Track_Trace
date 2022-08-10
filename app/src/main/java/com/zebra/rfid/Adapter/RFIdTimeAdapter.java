package com.zebra.rfid.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zebra.rfid.Activity.MainActivity;
import com.zebra.rfid.Event.CheckBoxDeleteCountRFID;
import com.zebra.rfid.Model.StationModel;
import com.zebra.rfid.demo.sdksample.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;

public class RFIdTimeAdapter extends RecyclerView.Adapter<RFIdTimeAdapter.holder> {

    @SuppressLint("StaticFieldLeak")
    private static MainActivity mContext;
    private static ArrayList<String> mrFidTagValueList;
    Realm realm;
    public static ArrayList<String> idAndTimeModels;
    private StationModel item;
    CheckBoxDeleteCountRFID deleteCountRFID;
    public RFIdTimeAdapter(MainActivity context, ArrayList<String> simpleViewModelList1,
                           ArrayList<String> rFidTagValueList, Realm realm, StationModel item) {
        this.mContext = context;
        this.mrFidTagValueList = rFidTagValueList;
        this.realm = realm;
        this.idAndTimeModels = simpleViewModelList1;
        this.item = item;
        this.deleteCountRFID = deleteCountRFID;
    }

    @NonNull
    @Override
    public RFIdTimeAdapter.holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.adapter_rfidtime, viewGroup, false);

        return new holder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RFIdTimeAdapter.holder holder,
                                 @SuppressLint("RecyclerView") int position) {
        // checkBoxDelete.onItemClick(position);
        String sb = "null";

        Date d = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String currentDateTimeString = sdf.format(d);
        final int pos = position;

        if (sb == "null") {
            mrFidTagValueList.remove(sb);
            holder.llRFIDTagText.setVisibility(View.GONE);
        }else {
            holder.llRFIDTagText.setVisibility(View.VISIBLE);
            mrFidTagValueList.add(sb);
        }

        holder.llRFIDTagText.setVisibility(View.VISIBLE);
        holder.tvRFIDNumber.setText(mrFidTagValueList.get(position));
        holder.tvTimeValue.setText(currentDateTimeString);
        holder.cbRFIdTag.setTag(mrFidTagValueList.get(position));

        try {
            if (item.getRFid() != null) {
                if (mrFidTagValueList.get(position).equals(item.getRFid())) {
                    mrFidTagValueList.remove(sb);
                    holder.llRFIDTagText.setVisibility(View.GONE);
                }
            }
        }catch (Exception e){
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        holder.cbRFIdTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.check(v, pos);
                mContext.startSelection(pos);
               // holder.cbRFIdTag.getTag(Integer.parseInt(mrFidTagValueList.get(position)));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mrFidTagValueList.size();
    }

    public class holder extends RecyclerView.ViewHolder {

        TextView tvRFIDNumber;
        TextView tvTimeValue;
        CheckBox cbRFIdTag;
        LinearLayout llRFIDTagText;
        View view;

        public holder(@NonNull View itemView, MainActivity activity) {
            super(itemView);
            tvRFIDNumber = itemView.findViewById(R.id.tvRFIDNumber);
            tvTimeValue = itemView.findViewById(R.id.tvTimeValue);
            cbRFIdTag = itemView.findViewById(R.id.cbRFIdTag);
            llRFIDTagText = itemView.findViewById(R.id.llRFIDTagText);
            view = itemView;
            view.setOnClickListener(activity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
