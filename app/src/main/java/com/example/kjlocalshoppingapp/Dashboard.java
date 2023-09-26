package com.example.kjlocalshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
@RequiresApi(api = Build.VERSION_CODES.O)
public class Dashboard extends AppCompatActivity {
    private static final String TAG = "MainActivity";

//decleration of the variables
    Button btnCategory, btnAddProductDB, btnProduct, btnLogOut,btnViewUsersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnCategory = findViewById(R.id.btnCategory);
        btnProduct = findViewById(R.id.btnProduct);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnAddProductDB = findViewById(R.id.btnAddProductDB);
        btnViewUsersList = findViewById(R.id.btnUserList);

        //A method to navigate to the categoryList class
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Dashboard.this, CategoryList.class);
                startActivity(intent);

            }
        });


        //A method to navigate to the ViewProductList class
        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Dashboard.this, ViewProductList.class);
                startActivity(intent);

            }
        });

        //A method to navigate to the AddProduct class
        btnAddProductDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Dashboard.this, AddProduct.class);
                startActivity(intent);

            }
        });

        btnViewUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Dashboard.this, ViewRegisteredUsers.class);
                startActivity(intent);

            }
        });

        //A method to navigate to the MainActivity class
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);

            }
        });



    }


    //Method to keep logs of events
    protected void onStart() {
        Log.d(TAG, "activity at onStart() Stage");
        super.onStart();
    }

    protected void onResume() {
        Log.d(TAG, "activity at onResume() Stage");
        super.onResume();
    }

    protected void onPause() {
        Log.d(TAG, "activity at onPause() Stage");
        super.onPause();
    }

    protected void onRestart() {
        Log.d(TAG, "activity at onRestart() Stage");
        super.onRestart();
    }

    protected void onDestroy() {
        Log.d(TAG, "activity at onDestroy() Stage");
        super.onDestroy();
    }

    protected void onStop() {
        Log.d(TAG, "activity at onStop() Stage");
        super.onStop();
    }


}