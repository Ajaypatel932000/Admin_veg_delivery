package com.example.admin_veg_delivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin_veg_delivery.adapter.Oder_list_adp;
import com.example.admin_veg_delivery.adapter.Vegetable_list_adp;
import com.example.admin_veg_delivery.model.Product_details;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item_listActivity extends AppCompatActivity {
     RecyclerView recyclerView;
    public String name,price,URL="https://test-ajay.000webhostapp.com/show_item.php";
    List<Product_details> list;
    Vegetable_list_adp adp;
     RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);


        requestQueue= Volley.newRequestQueue(getApplicationContext());
        recyclerView= findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));// Recycle view in the form of liner_layout




    }
    private ArrayList<Product_details> getMyList()
    {
        final    ArrayList<Product_details> models= new ArrayList<>();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                 if(response!=null) {
                     try {

                         JSONObject jsonObject = new JSONObject(response);
                         JSONArray jsonArray = jsonObject.getJSONArray("root");
                         for (int i = 0; i < jsonArray.length(); i++) {
                               JSONObject object = jsonArray.getJSONObject(i);

                             name = object.getString("name");
                             price = object.getString("price");

                             // here create model object and setter the data into setter method
                             Product_details product_details = new Product_details(null,name,price,null);
                             list.add(product_details);




                         }
                         adp= new Vegetable_list_adp(Item_listActivity.this,list);
                         recyclerView.setAdapter(adp);


                     } catch (JSONException e) {
                         e.printStackTrace();
                         Toast.makeText(Item_listActivity.this, "exception"+e.getMessage(), Toast.LENGTH_LONG).show();

                     }
                 }else
                 {
                     Toast.makeText(Item_listActivity.this, "null", Toast.LENGTH_LONG).show();

                 }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Toast.makeText( Item_listActivity.this,"Volley Response error"+error.getMessage(),Toast.LENGTH_LONG).show();
            }

        }){
   //         @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("enroll_key", login.ENROLLMENT_NO);
//                return params;
//          }


        };
        requestQueue.add(stringRequest);


        return models;


    }
}
