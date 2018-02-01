package com.example.root.severconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ListView listView;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.mylist);
        fetchingData();


        
    }

    void fetchingData(){
        String myURL ="http://192.168.0.112/api/studentInfo.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                final String[] Name = new String[response.length()];
                final String[]phoneNumber = new String[response.length()];
                final String[] Email = new String[response.length()];

                for (int i = 0; i < response.length();i++){
                    try {

                        JSONObject jsonObject = (JSONObject) response.get(i);
                        Name[i] = jsonObject.getString("Name");
                        phoneNumber[i] = jsonObject.getString("PhoneNumber");
                        Email[i] = jsonObject.getString("Email");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                listView.setAdapter(new ArrayAdapter(getApplicationContext(),
                        R.layout.mylistview,R.id.textviewforlist,Name));



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                        intent.putExtra("Name",Name[position]);
                        intent.putExtra("phoneNumber",phoneNumber[position]);
                        intent.putExtra("Email",Email[position]);
                        startActivity(intent);

                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log : "+error.getMessage());
            }
        });

        com.example.root.severconnect.AppController.
              getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(MainActivity.this,"Data Loaded Successfully",
                Toast.LENGTH_LONG).show();

    }
}
