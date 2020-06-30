package com.example.admin_veg_delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.admin_veg_delivery.adapter.Vegetable_list_adp;
import com.example.admin_veg_delivery.model.Product_details;

import java.util.List;

public class Item_listActivity extends AppCompatActivity {
    RecyclerView item_rec;
    List<Product_details> product_detailsList;
    Vegetable_list_adp  adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        item_rec= findViewById(R.id.item_rec);

    }
}
