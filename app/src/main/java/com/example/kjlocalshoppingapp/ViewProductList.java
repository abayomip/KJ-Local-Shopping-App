package com.example.kjlocalshoppingapp;

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


public class ViewProductList extends AppCompatActivity {
    private static final String TAG = "ViewProductList";

    RecyclerView rcvList;
    List<ProductCtlr> productCtlrList;
    LinearLayoutManager layoutManager;
    databasecon db;
    ProductInfoAdapter adapter;
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Button btnAdd, btnPrevious, btnNext,btnUpdate,btnDelete;

    int currentposition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_list);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        rcvList = findViewById(R.id.rcvList);

        db = new databasecon(this);
        productCtlrList = (List<ProductCtlr>) db.getAllData();
        currentposition = 0;
        initRecyclerView();
        loadData(currentposition);
//The method for updating the product details
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the position of the product to update position
                int position = currentposition;
                //get the product obj from the the list by its position
                ProductCtlr p1 = productCtlrList.get(position);
                View view = findViewById(android.R.id.content);
                EditText edtproductName = view.findViewById(R.id.txtProductName);
                EditText edtproductDescription = view.findViewById(R.id.txtProductDis);
                EditText edtproductPrice = view.findViewById(R.id.txtProductPrice);
                EditText edtproductListPrice = view.findViewById(R.id.txtProductListPrice);
                EditText edtproductRetailPrice = view.findViewById(R.id.txtProductRetailPrice);
                EditText edtcategory = view.findViewById(R.id.txtCategory);
                EditText edtdateUpdated = view.findViewById(R.id.txtdateUpdated);
                EditText edtdateCreated = view.findViewById(R.id.txtDateCreated);
                //get the text from the editview variables
                String ProductName = edtproductName.getText().toString();
                String ProductDescription = edtproductDescription.getText().toString();
                String ProductPrice = edtproductPrice.getText().toString();
                String ProductListPrice = edtproductListPrice.getText().toString();
                String ProductRetailPrice = edtproductRetailPrice.getText().toString();
                String Category = edtcategory.getText().toString();
                String DateUpdated = edtdateUpdated.getText().toString();
                String DateCreated = edtdateCreated.getText().toString();
//checking if any field is empty
                if (!ProductName.isEmpty()){
                    p1.setProductName(ProductName);
                }
                if (!ProductDescription.isEmpty()){
                    p1.setProductDescription(ProductDescription);
                }
                if (!ProductPrice.isEmpty()){
                    p1.setProductPrice(ProductPrice);
                }
                if (!ProductListPrice.isEmpty()){
                    p1.setProductListPrice(ProductListPrice);
                }
                if (!ProductRetailPrice.isEmpty()){
                    p1.setProductRetailPrice(ProductRetailPrice);
                }
                if (!Category.isEmpty()){
                    p1.setCategory(Category);
                }
                if (!DateUpdated.isEmpty()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(DateUpdated, formatter);
                    p1.setDateUpdated(date);
                }
                if (!DateCreated.isEmpty()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(DateUpdated, formatter);

                    p1.setDateCreated(date);
                }
               // update the data in the database
                db.updateProduct(p1);
                //notifing the adapter that the data has changed
                adapter.notifyDataSetChanged();
                Toast.makeText(ViewProductList.this, "Succesfull", Toast.LENGTH_SHORT).show();


            }
        });

// delete method to delete selected row
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtId = findViewById(R.id.txtProductId);
                String ID = txtId.getText().toString();
                if (ID.isEmpty()) {
                    Toast.makeText(ViewProductList.this, "Please enter a valid ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean result = db.deleteProduct(new ProductCtlr(Integer.parseInt(ID)));
                if (result) {

                    Toast.makeText(ViewProductList.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewProductList.this, "Product not found or deletion failed", Toast.LENGTH_SHORT).show();
                }

            }
        });


//a method to move next to the next row
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentposition >= 0 && currentposition < productCtlrList.size())
                    currentposition++;
                loadData(currentposition);

            }
        });

//a method to move back to the previous row
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentposition >= 0 && currentposition < productCtlrList.size())
                    currentposition--;
                loadData(currentposition);
            }
        });
    }

//displaying the view through a recycler view
    private void initRecyclerView () {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rcvList.setLayoutManager(layoutManager);
        adapter = new ProductInfoAdapter(getApplicationContext(), productCtlrList);
        rcvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    //a method to load data to the screen
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