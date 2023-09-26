package com.example.kjlocalshoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminView extends AppCompatActivity {
    //decleration of the variable
    Button btnDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        btnDashboard = findViewById(R.id.btnDashboard);

//A method to navigate the admin user to the admin dashboard
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(AdminView.this, Dashboard.class);
                startActivity(intent);

            }
        });
    }
}