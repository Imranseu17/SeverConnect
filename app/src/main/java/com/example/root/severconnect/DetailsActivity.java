package com.example.root.severconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView name, phoneNumber,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.email);
        String Name = getIntent().getStringExtra("Name");
        String PhoneNumber = getIntent().getStringExtra("phoneNumber");
        String Email = getIntent().getStringExtra("Email");

        name.setText("Name: \n"+ Name);
        phoneNumber.setText("PhoneNumber: \n"+PhoneNumber);
        email.setText("Email: \n"+Email);
    }
}
