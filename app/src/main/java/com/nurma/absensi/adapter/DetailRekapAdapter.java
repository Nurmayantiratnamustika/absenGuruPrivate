package com.nurma.absensi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nurma.absensi.ActivityGuruLogin;
import com.nurma.absensi.DetailRekapActivity;
import com.nurma.absensi.MainActivity;
import com.nurma.absensi.MapsActivity;
import com.nurma.absensi.R;
import com.nurma.absensi.model.Login;
import com.nurma.absensi.model.LoginResponse;
import com.nurma.absensi.model.RekapDetail;

import java.util.List;

public class DetailRekapAdapter  extends RecyclerView.Adapter<DetailRekapAdapter.ViewHolder> {
    List<RekapDetail> detailList;
    Context mContext;

    public DetailRekapAdapter(List<RekapDetail> detailList, Context mContext) {
        this.detailList = detailList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DetailRekapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detailrekap, parent, false);
        return new DetailRekapAdapter.ViewHolder(itemView);
    }

    public void setDetailList(List<RekapDetail> detailList){
        this.detailList=detailList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull DetailRekapAdapter.ViewHolder holder, int position) {
        final RekapDetail itemLogin = detailList.get(position);
        holder.tvJamlogin.setText(itemLogin.getJam_login());
        holder.tvTanggal.setText(itemLogin.getTanggal());
        holder.tvJamlogout.setText(itemLogin.getJam_logout());
        holder.latitude.setText(Double.toString(itemLogin.getLatitude()));
        holder.longitude.setText(Double.toString(itemLogin.getLongitude()));
        holder.tvnamasiswa.setText(itemLogin.getNama_siswa());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), MapsActivity.class);
                mIntent.putExtra("latitude", detailList.get(position).getLatitude());
                mIntent.putExtra("longitude", detailList.get(position).getLongitude());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJamlogin;
        TextView tvTanggal;
        TextView tvJamlogout,latitude,longitude,tvnamasiswa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJamlogin=itemView.findViewById(R.id.detjamlogin);
            tvTanggal=itemView.findViewById(R.id.dettanggal);
            tvJamlogout=itemView.findViewById(R.id.detjamlogout);
            latitude=itemView.findViewById(R.id.detlatitude);
            longitude = itemView.findViewById(R.id.detlongitude);
            tvnamasiswa = itemView.findViewById(R.id.detNama);
        }
    }

}
