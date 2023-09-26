package com.example.kjlocalshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.time.format.DateTimeFormatter;
import java.util.Date;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.Button;
@RequiresApi(api = Build.VERSION_CODES.O)

public class CategoryList extends AppCompatActivity {
    private static final String TAG = "ViewProductList";
//decleration of the variables
    RecyclerView recycler;
    List<ProductCtlr> productCtlrList;
    LinearLayoutManager layoutManager;
    databasecon db;
    CategoryAdapter adapter;
    int currentposition = 0;
    //Arraylist of object
    private List<ProductCtlr> ProductCtlrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        recycler = findViewById(R.id.rcvDisplay);
        //creates the database class
        db = new databasecon(this);
//calls for the getAllCategories methid in the db object to retrieve a list of all the categories
        productCtlrList = (List<ProductCtlr>) db.getAllCategories();
        //setting current position to 0
        currentposition = 0;
        initRecyclerView();
        loadData();

    }

    private void initRecyclerView () {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(layoutManager);
       // setting the categoryAdapter class using data from thr list
        adapter = new CategoryAdapter(getApplicationContext(), productCtlrList);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void loadData() {
        List<ProductCtlr> productList = db.getAllCategories();
        if (productList.isEmpty()) {
            Toast.makeText(this, "NO category available", Toast.LENGTH_SHORT).show();
        } else{
            // Passing the List to the adapter
            CategoryAdapter adapter = new CategoryAdapter(this, productList);
            recycler.setAdapter(adapter);

        }
    }
}