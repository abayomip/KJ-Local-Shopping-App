package com.example.kjlocalshoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class UserViewProductList extends AppCompatActivity {
    private static final String TAG = "ViewProductList";
private UserViewProductList activity;
    RecyclerView rcvList;
    List<ProductCtlr> productCtlrList;
    LinearLayoutManager layoutManager;
    databasecon db;
    ProductInfoAdapter adapter;
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Button btnPrevious, btnNext,btnADDToBasket,btnBasket;
EditText editUserText;
    int currentposition = 0;
    List<ProductCtlr> ShopinBasket = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_product_list);
        activity = this;
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        btnADDToBasket = findViewById(R.id.btnADDToBasket);
        btnBasket = findViewById(R.id.btnBasket);

        rcvList = findViewById(R.id.rcvList);
        ShopinBasket = new ArrayList<>();
        db = new databasecon(this);
        productCtlrList = (List<ProductCtlr>) db.getAllData();
        currentposition = 0;
        initRecyclerView();
        loadData(currentposition);

        //A method to navigate to the next product when viewing the products
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentposition >= 0 && currentposition < productCtlrList.size())
                    currentposition++;
                loadData(currentposition);

            }
        });

        //A method to navigate to the previous product when viewing the products
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentposition >= 0 && currentposition < productCtlrList.size())
                    currentposition--;
                loadData(currentposition);
            }
        });

// to add basket in a shopping basket
        btnADDToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductCtlr productSelected = productCtlrList.get(currentposition);
                ShopinBasket.add(productSelected);
                Toast.makeText(UserViewProductList.this, "Product Succesfully Added To Basket", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, ShoppingBasket.class);
                intent.putParcelableArrayListExtra("ShopinBasket", (ArrayList<? extends Parcelable>) ShopinBasket);
                startActivity(intent);
            }
        });

//to view the products added to the basket
    btnBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserViewProductList.this, ShoppingBasket.class);
                intent.putParcelableArrayListExtra("ShopinBasket", (ArrayList<? extends Parcelable>) ShopinBasket);
                startActivity(intent);

            }
        });


    }
    private void initRecyclerView () {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvList.setLayoutManager(layoutManager);
        adapter = new ProductInfoAdapter(getApplicationContext(), productCtlrList);
        rcvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    //rewrite this one
    private void loadData(int currentposition) {
        List<ProductCtlr> productList = db.getAllData();
        if (productList.isEmpty()) {
            Toast.makeText(this, "No Product Available", Toast.LENGTH_SHORT).show();
        } else{
            ProductCtlr product = productList.get(currentposition);
            List<ProductCtlr> viewProductList = new ArrayList<>();
            viewProductList.add(product);
            // Pass the viewProductList to the adapter
            ProductInfoAdapter adapter = new ProductInfoAdapter(this, viewProductList);
            rcvList.setAdapter(adapter);
            // Using the properties of the products object here
            int id = product.getId();
            String productName = product.getProductName();
            String productDescription = product.getProductDescription();
            String productPrice = product.getProductPrice();
            String productListPrice = product.getProductListPrice();
            String productRetailPrice = product.getProductRetailPrice();
            String category = product.getCategory();
            LocalDate dateUpdated = product.getDateUpdated();
            LocalDate dateCreated = product.getDateCreated();

        }
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