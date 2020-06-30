package com.example.admin_veg_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class Add_itemActivity extends AppCompatActivity {
    Button browse,submit_btn;
    ImageView pic_veg;
    TextInputEditText price,name;
    MaterialBetterSpinner weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        browse=findViewById(R.id.browse);
        submit_btn=findViewById(R.id.submit_btn);
        pic_veg=findViewById(R.id.pic_veg);
        price=findViewById(R.id.price);
        name=findViewById(R.id.name);
        weight=findViewById(R.id.weight);
        String str[] = getResources().getStringArray(R.array.weight);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, str);
        weight.setAdapter(adapter);

    }
}
