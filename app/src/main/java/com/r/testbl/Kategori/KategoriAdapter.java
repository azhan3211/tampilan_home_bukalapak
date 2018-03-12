package com.r.testbl.Kategori;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.r.testbl.R;

import java.util.List;

/**
 * Created by Unknown on 3/9/2018.
 */

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {
    List<String> data;
    Context context;
    public KategoriAdapter(Context context, List<String> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kategori_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.namaKategori.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView namaKategori;
        ImageView gambarKategori;
        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout_kategori);
            namaKategori = (TextView) itemView.findViewById(R.id.nama_kategori);
            gambarKategori = (ImageView) itemView.findViewById(R.id.kategori_image);
        }
    }
}
