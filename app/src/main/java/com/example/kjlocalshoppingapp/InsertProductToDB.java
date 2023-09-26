package com.example.kjlocalshoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InsertProductToDB extends AppCompatActivity {
    Button btnAddToDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product_to_db);
        btnAddToDB = findViewById(R.id.btnAddToDB);


        btnAddToDB.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(InsertProductToDB.this, AddProduct.class);
                startActivity(intent);

            }
        });
    }
}