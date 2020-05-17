package com.nurma.absensi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nurma.absensi.R;
import com.nurma.absensi.model.Guru;
import com.nurma.absensi.model.Siswa;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> {
    ArrayList<Siswa> items;

    public SiswaAdapter(ArrayList<Siswa> items)
    {
        this.items = items;
    }

    @NonNull
    @Override
    public SiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_siswa, parent, false);
        return new SiswaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaAdapter.ViewHolder holder, int position) {
        Siswa item = items.get(position);
        holder.nimSiswa.setText(Integer.toString(item.getNim()));
        holder.namaSiswa.setText(item.getNama_siswa());
        holder.alamatSiswa.setText(item.getAlamat_siswa());
        holder.jkSiswa.setText(item.getJenis_kelamin());
        holder.ttlSiswa.setText(item.getTanggal_lahir());
        holder.kelasSiswa.setText(item.getKelas());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nimSiswa,namaSiswa,jkSiswa,alamatSiswa,ttlSiswa,kelasSiswa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nimSiswa= itemView.findViewById(R.id.nimSiswa);
            namaSiswa = itemView.findViewById(R.id.namaSiswa);
            jkSiswa= itemView.findViewById(R.id.jkSiswa);
            alamatSiswa= itemView.findViewById(R.id.alamatSiswa);
            ttlSiswa = itemView.findViewById(R.id.ttlSiswa);
            kelasSiswa=itemView.findViewById(R.id.kelasSiswa);
        }
    }
}
