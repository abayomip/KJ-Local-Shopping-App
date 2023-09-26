package com.example.kjlocalshoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class DisplayCatItems extends AppCompatActivity {
    RecyclerView recycler;
    List<ProductCtlr> productCtlrList;
    LinearLayoutManager layoutManager;
    databasecon db;
    ProductInfoAdapter adapter;
    int currentposition = 0;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_cat_items);
        recycler = findViewById(R.id.rcvDisplay);
        db = new databasecon(this);
        String category = getIntent().getStringExtra("category");
        productCtlrList = (List<ProductCtlr>) db.getDisplayCatItems(category);
        currentposition = 0;
        initRecyclerView();

    }

    private void initRecyclerView () {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new ProductInfoAdapter(getApplicationContext(), productCtlrList);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
}
}