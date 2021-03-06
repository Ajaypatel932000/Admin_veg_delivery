package com.example.admin_veg_delivery.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin_veg_delivery.R;
import com.example.admin_veg_delivery.model.Product_details;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;


public class Vegetable_list_adp extends RecyclerView.Adapter<Vegetable_list_adp.Myholder>{
    Context context;
    List<Product_details> lits;

    public Vegetable_list_adp(Context context, List<Product_details> lits) {
        this.context = context;
        this.lits = lits;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.price_list_view, parent, false);

        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        Picasso.get().load(lits.get(position).getImageurl()).into(holder.iv);
        holder.name.setText(lits.get(position).getName());
        holder.weight.setText(lits.get(position).getWeight());
        holder.price.setText(lits.get(position).getPrice());
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YY");
        String dateString = sdf.format(date);
        holder.time.setText(dateString);



    }

    @Override
    public int getItemCount() {
        return lits.size();
    }

    class Myholder extends RecyclerView.ViewHolder{
            TextView name,weight,price,time;
            ImageView iv;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.veg_name);
            weight=itemView.findViewById(R.id.veg_unit);
            price=itemView.findViewById(R.id.veg_price);
            iv=itemView.findViewById(R.id.veg_iv);
            time=itemView.findViewById(R.id.time);
        }

    }


}
