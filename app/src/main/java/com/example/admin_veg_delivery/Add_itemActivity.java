package com.example.admin_veg_delivery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.service.autofill.FieldClassification;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add_itemActivity extends AppCompatActivity implements View.OnClickListener {
    Button browse,submit_btn;
    ImageView pic_veg;
  public   TextInputEditText price,name;
  public   MaterialBetterSpinner weight;
    private Bitmap bitmap;
    private  final int IMG_REQUEST=1;
    public int check_img=0;

    String URL="https://test-ajay.000webhostapp.com/add_vegetable.php";
    RequestQueue requestQueue;

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
         requestQueue= Volley.newRequestQueue(getApplicationContext());

         submit_btn.setOnClickListener(this);
         browse.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.browse:
                check_img=1;
                selectImage();
                 break;
            case R.id.submit_btn:
                  SubmitData();
                break;

        }
    }
    private void selectImage()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);

    }
    private String ImageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgeByte=byteArrayOutputStream.toByteArray();

        Toast.makeText(Add_itemActivity.this," String ="+imgeByte,Toast.LENGTH_LONG).show();

        return Base64.encodeToString(imgeByte,Base64.DEFAULT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null)
        {
            try {

                Uri path = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                pic_veg.setImageBitmap(bitmap);
                Toast.makeText(Add_itemActivity.this,"Path ="+path+" Bitmap  ="+bitmap,Toast.LENGTH_LONG).show();

            }catch(IOException e)
            {
                Toast.makeText(Add_itemActivity.this,"Error "+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }
    public void SubmitData()
    {
        final ProgressDialog progressDialog=new ProgressDialog(Add_itemActivity.this);
        progressDialog.setMessage("Loading... ");
        progressDialog.setCancelable(false);
        progressDialog.show();
        if(CheckEmpty()) {
            final String img = ImageToString(bitmap);
          //  Toast.makeText(Add_itemActivity.this, "Entered value =" + name.getText().toString() + "pirce = " + price.getText().toString() + "weight =" + weight.getText().toString() + " " + img, Toast.LENGTH_LONG).show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getBoolean("key")) {
                            name.getText().clear();
                            price.getText().clear();
                            weight.getText().clear();
                            // here write image clear code

                            Toast.makeText(Add_itemActivity.this, "Succees", Toast.LENGTH_LONG).show();
                             progressDialog.dismiss();
                        } else {
                            Toast.makeText(Add_itemActivity.this, "false", Toast.LENGTH_LONG).show();
                             progressDialog.dismiss();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(Add_itemActivity.this, "Exception =" + e.getMessage(), Toast.LENGTH_LONG).show();
                          progressDialog.dismiss();

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_itemActivity.this, "Error =" + error.getMessage(), Toast.LENGTH_LONG).show();
                     progressDialog.dismiss();
                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("name", name.getText().toString().trim());
                    params.put("price", price.getText().toString().trim());
                    params.put("weight", weight.getText().toString());
                    params.put("img", img);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }

    }

    public boolean CheckEmpty()
    {  // check the You enter number or anything else
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(name.getText().toString());
        boolean bs = ms.matches();

        if(bs==false)
        {
            name.setError("Invalid Input");
            return false;

        }else if(price.getText().toString().isEmpty())
        {price.setError("Invalid Input");
            return  false;
        }else if(weight.getText().toString().isEmpty())
        {
            weight.setError("Invalid Input");
            return false;
        }else if(check_img==0)
        {
            browse.setError("Please select Image");
            return false;
        }else
        {
            return true;
        }
    }
}
