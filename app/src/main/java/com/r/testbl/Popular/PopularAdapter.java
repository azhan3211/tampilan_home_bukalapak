package com.r.testbl.Popular;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.r.testbl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Unknown on 3/10/2018.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    List<PopularData> popularDatas;
    Context context;

    public PopularAdapter(Context context, List<PopularData> popularDatas) {
        this.context = context;
        this.popularDatas = popularDatas;
    }

    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.ViewHolder holder, int position) {
        PopularData popularData = popularDatas.get(position);

        holder.namaBarang.setText(popularData.getNamaBarang());
        holder.harga.setText("Rp."+popularData.getHargaBarang());
        holder.count.setText("("+popularData.getCountBarang()+")");
        holder.rating.setRating(Float.parseFloat(popularData.getRatingBarang()));
        Picasso.with(context).load(popularData.getImageBarang()).into(holder.gambarBarang);
    }

    @Override
    public int getItemCount() {
        return popularDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView namaBarang, harga, count;
        private RatingBar rating;
        private ImageView gambarBarang;

        public ViewHolder(View itemView) {
            super(itemView);
            namaBarang = (TextView) itemView.findViewById(R.id.nama_barang);
            harga = (TextView) itemView.findViewById(R.id.original_price);
            count = (TextView) itemView.findViewById(R.id.rating_count);
            rating = (RatingBar) itemView.findViewById(R.id.rating_popular);
            gambarBarang = (ImageView) itemView.findViewById(R.id.image_popular);
        }
    }
}
