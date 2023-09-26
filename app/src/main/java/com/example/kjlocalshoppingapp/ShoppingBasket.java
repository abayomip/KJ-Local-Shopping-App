package com.example.kjlocalshoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket extends AppCompatActivity {

    ListView showCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_basket);
        showCart = findViewById(R.id.showCart);
        Intent intent = getIntent();
        List<ProductCtlr> ShopinBasket = intent.getParcelableArrayListExtra("ShopinBasket");

        ArrayAdapter<ProductCtlr> cart = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ShopinBasket);
        showCart.setAdapter(cart);



    }
}