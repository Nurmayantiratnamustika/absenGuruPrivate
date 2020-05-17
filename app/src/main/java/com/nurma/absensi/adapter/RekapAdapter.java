package com.nurma.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nurma.absensi.DetailRekapActivity;
import com.nurma.absensi.R;
import com.nurma.absensi.RekapAbsenActivity;
import com.nurma.absensi.model.Login;
import com.nurma.absensi.model.Rekap;
import com.nurma.absensi.model.Siswa;

import java.util.ArrayList;
import java.util.List;

public class RekapAdapter extends RecyclerView.Adapter<RekapAdapter.ViewHolder> {
    List<Rekap> rekapList;
    Context mContext;

    public RekapAdapter(List<Rekap> rekapList, Context mContext) {
        this.rekapList = rekapList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RekapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rekap, parent, false);
        return new RekapAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RekapAdapter.ViewHolder holder, int position) {
        final Rekap item = rekapList.get(position);
        holder.username.setText(item.getUsername());
        holder.rekap.setText(item.getRekap());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailRekapActivity.class);
                mIntent.putExtra("username", rekapList.get(position).getUsername());
                mIntent.putExtra("password", rekapList.get(position).getPassword());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    public void setRekapList(List<Rekap> rekapList){
        this.rekapList = rekapList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return rekapList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,rekap;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username= itemView.findViewById(R.id.rekapUsername);
            rekap = itemView.findViewById(R.id.jumRekap);
        }
    }
}
