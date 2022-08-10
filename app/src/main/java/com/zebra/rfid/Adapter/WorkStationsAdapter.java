package com.zebra.rfid.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.zebra.rfid.Model.StationModel;
import com.zebra.rfid.demo.sdksample.R;

import java.util.List;

import io.realm.Realm;

public class WorkStationsAdapter extends RecyclerView.Adapter<WorkStationsAdapter.MyViewHolder> {

    Context mContext;
    List<StationModel> stations;
    StationModel mData;
    String listCount;
    Realm realm;

    public WorkStationsAdapter(Context context, List<StationModel> stationApiResponse,
                               StationModel mData, String listCount) {
        this.mContext = context;
        this.stations = stationApiResponse;
        this.mData = mData;
        this.listCount = listCount;
    }

    @NonNull
    @Override
    public WorkStationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View previousList = layoutInflater.inflate(R.layout.adapter_stations, parent,
                false);
        return new MyViewHolder(previousList);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WorkStationsAdapter.MyViewHolder myViewHolder, int position) {

        realm = Realm.getDefaultInstance();
        myViewHolder.tvStations.setText(stations.get(position).getName());

        if (stations != null) {
            try {
                if (stations.get(position).getRFid().equals(mData.getRFid())) {
                    myViewHolder.llStationBG.setBackground(mContext.getResources()
                            .getDrawable(R.drawable.bg_station2));
                    if (listCount != null) {
                        myViewHolder.tvStationsNos.setText(listCount);
                    } else {
                        myViewHolder.tvStationsNos.setText("0");
                    }
                } else if (stations.get(position).getScanCount() > 0) {
                    myViewHolder.tvStationsNos.setText(String.valueOf(stations.get(position).getScanCount()));
                    myViewHolder.llStationBG.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_station1));
                } else {
                    myViewHolder.tvStationsNos.setText("0");
                    myViewHolder.llStationBG.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_stations));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvStationsNos, tvStations;
        LinearLayout llStationBG;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStations = itemView.findViewById(R.id.tvStations);
            tvStationsNos = itemView.findViewById(R.id.tvStationsNos);
            llStationBG = itemView.findViewById(R.id.llStationBG);
        }
    }
}
