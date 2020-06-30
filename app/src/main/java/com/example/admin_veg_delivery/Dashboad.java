package com.example.admin_veg_delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboad extends AppCompatActivity {
    CardView price_list,add_item,oders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);
        price_list=findViewById(R.id.price_list);
        add_item=findViewById(R.id.add_item);
        oders=findViewById(R.id.oder);
        price_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboad.this,Item_listActivity.class);
                startActivity(intent);
                finish();

            }
        });
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Dashboad.this,Add_itemActivity.class);
                startActivity(intent1);
                finish();


            }
        });
        oders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Dashboad.this,OderActivity.class);
                startActivity(intent2);
                finish();


            }
        });
    }
}
