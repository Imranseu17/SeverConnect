package com.example.root.severconnect;


import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddingItem extends AppCompatActivity {

    EditText Name, PhoneNumber, Email;
    Button AddData;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_item);

        Name = findViewById(R.id.name);
        PhoneNumber = findViewById(R.id.phoneNumber);
        Email = findViewById(R.id.email);

        AddData = findViewById(R.id.addserver);

        AddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.0.112/api/postData.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("Volley Log : " + error.getMessage());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("Name",Name.getText().toString());
                        params.put("PhoneNumber",PhoneNumber.getText().toString());
                        params.put("Email",Email.getText().toString());
                        return params;
                    }
                };


                AppController.getInstance().addToRequestQueue(stringRequest);
                Toast.makeText(getApplicationContext(), "Add Successfully", Toast.LENGTH_LONG
                ).show();
            }
        });


    }


}