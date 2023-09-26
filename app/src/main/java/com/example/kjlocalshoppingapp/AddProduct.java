package com.example.kjlocalshoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AddProduct extends AppCompatActivity {
    //Arraylist of objects
    List<ProductCtlr> productCtlrList;
    LinearLayoutManager layoutManager;
    databasecon db;
    ProductInfoAdapter adapter;
//declaration of the variables
    EditText id,productName, productDescription, productPrice, productListPrice, productRetailPrice, category, dateCreated, dateUpdated;
    Button btnAddProduct;
    //new date object
    Date date = new Date();
//converting the date object to a localdate instance
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        id = findViewById(R.id.txtProductId);
        productName = findViewById(R.id.txtProductName);
        productDescription = findViewById(R.id.txtProductDis);
        productPrice = findViewById(R.id.txtProductPrice);
        productListPrice = findViewById(R.id.txtProductListPrice);
        productRetailPrice = findViewById(R.id.txtProductRetailPrice);
        category = findViewById(R.id.txtCategory);
        dateUpdated =  findViewById(R.id.txtdateUpdated);
        dateCreated = findViewById(R.id.txtDateCreated);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        productCtlrList = new ArrayList<>();
        adapter = new ProductInfoAdapter(this, productCtlrList);

//A method to insert product into the database
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasecon db = new databasecon(AddProduct.this);
                // check if the field is empty
                if (id.getText().toString().isEmpty()|| productName.getText().toString().isEmpty() || productDescription.getText().toString().isEmpty() || productPrice.getText().toString().isEmpty()||
                        productListPrice.getText().toString().isEmpty() || productRetailPrice.getText().toString().isEmpty() || category.getText().toString().isEmpty() || dateUpdated.getText().toString().isEmpty()||
                        dateCreated.getText().toString().isEmpty()) {
                    Toast.makeText(AddProduct.this, "Complete each field", Toast.LENGTH_SHORT).show();
                    return;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate DateUpdatedLocalDate = LocalDate.parse(dateUpdated.getText().toString(), formatter);
                LocalDate DateCreatedLocalDate = LocalDate.parse(dateCreated.getText().toString(), formatter);
                //insert the desired value in the database
                ProductCtlr p1 = new ProductCtlr(Integer.parseInt(id.getText().toString()), productName.getText().toString(), productDescription.getText().toString(), productPrice.getText().toString(), productListPrice.getText().toString(),
                        productRetailPrice.getText().toString(), category.getText().toString(), DateUpdatedLocalDate, DateCreatedLocalDate);
                db.addProduct(p1);
                productCtlrList.add(p1);
                adapter.notifyDataSetChanged();
                Toast.makeText(AddProduct.this, "Succesfully Added", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(AddProduct.this,Dashboard.class);
                startActivity(intent);
            }
        });

    }
    }
