package com.example.kjlocalshoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserView extends AppCompatActivity {
    Button btnDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        btnDashboard = findViewById(R.id.btnDashboard);

//method to navigate to the users dashboard
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserView.this, UserDashboard.class);
                startActivity(intent);

            }
        });
    }
}