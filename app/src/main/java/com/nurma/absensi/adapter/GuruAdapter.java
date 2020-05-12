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
import com.nurma.absensi.model.Login;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GuruAdapter extends RecyclerView.Adapter<GuruAdapter.ViewHolder> {
    ArrayList<Guru> items;

    public GuruAdapter(ArrayList<Guru> items)
    {
        this.items = items;
    }

    @NonNull
    @Override
    public GuruAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guru, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuruAdapter.ViewHolder holder, int position) {
        Guru item = items.get(position);
        holder.idText.setText(Integer.toString(item.getId()));
        holder.namaText.setText(item.getNama());
        holder.alamatText.setText(item.getAlamat());
        holder.jkText.setText(item.getJenis_kelamin());
        holder.telpText.setText(item.getNo_telp());
        holder.username.setText(item.getUsername());
        holder.password.setText(item.getUsername());
        String url = "http://retrofitnurma.000webhostapp.com/images/"+item.getFoto();
        Picasso.get().load(url).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idText, namaText, alamatText, jkText, telpText,username,password;
        ImageView foto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.idTag);
            namaText = itemView.findViewById(R.id.namaGuruTag);
            alamatText = itemView.findViewById(R.id.alamatTag);
            jkText = itemView.findViewById(R.id.jkTag);
            telpText = itemView.findViewById(R.id.telpTag);
            foto = itemView.findViewById(R.id.imageView2);
            username=itemView.findViewById(R.id.usernameTag);
            password = itemView.findViewById(R.id.passwordTag);

        }
    }
}
