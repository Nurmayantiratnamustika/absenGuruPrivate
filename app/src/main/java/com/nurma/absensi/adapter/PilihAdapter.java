package com.nurma.absensi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nurma.absensi.R;
import com.nurma.absensi.model.Siswa;

import java.util.ArrayList;
import java.util.List;

public class PilihAdapter extends RecyclerView.Adapter<PilihAdapter.ViewHolder> {
    ArrayList<Siswa> siswaList= new ArrayList<>();
    private ListItemListener listItemListener;
    int id;

    public PilihAdapter(ArrayList<Siswa> siswaList,ListItemListener listItemListener) {
        this.siswaList = siswaList;
        this.listItemListener=listItemListener;

    }

    public  interface  ListItemListener{
        void onListClik(int position);
    }


    @NonNull
    @Override
    public PilihAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_siswapilih, parent, false);
        return new PilihAdapter.ViewHolder(view,listItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Siswa item = siswaList.get(position);
        holder.nim.setText(Integer.toString(item.getNim()));
        holder.nama.setText(item.getNama_siswa());



    }


    @Override
    public int getItemCount() {
        return siswaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView nama,nim;
        ListItemListener listItemListener;
        public ViewHolder(@NonNull View itemView,ListItemListener listItemListener) {
            super(itemView);
            nim = itemView.findViewById(R.id.pilihNim);
            nama= itemView.findViewById(R.id.pilihNama);
            this.listItemListener = listItemListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            listItemListener.onListClik(getAdapterPosition());

        }
    }
}