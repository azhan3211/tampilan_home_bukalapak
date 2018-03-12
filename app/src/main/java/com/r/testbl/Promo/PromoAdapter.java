package com.r.testbl.Promo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.r.testbl.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Unknown on 3/9/2018.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder> {

    List<PromoData> promos;
    Context context;
    PromoData promo;

    public PromoAdapter(Context context, List<PromoData> promos) {
        this.promos = promos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promo_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        promo = promos.get(position);
        Picasso.with(context).load(promo.getImage()).into(holder.imagePromo);
        holder.descriptionPromo.setText(promo.getDescription());
    }

    @Override
    public int getItemCount() {
        return promos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView descriptionPromo;
        ImageView imagePromo;
        public ViewHolder(View itemView) {
            super(itemView);
            descriptionPromo = (TextView) itemView.findViewById(R.id.description_promo);
            imagePromo = (ImageView) itemView.findViewById(R.id.image_promo);
        }
    }
}
