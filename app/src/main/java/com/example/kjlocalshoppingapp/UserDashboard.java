package com.example.kjlocalshoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class UserDashboard extends AppCompatActivity {
    private static final String TAG = "MainActivity";

//declaring the button variables
    Button btnCategory, btnProduct, btnLogOut,btnBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        btnCategory = findViewById(R.id.btnCategory);
        btnProduct = findViewById(R.id.btnProduct);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnBasket = findViewById(R.id.btnBasket);


        //method to navigate to the CategoryList
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserDashboard.this, CategoryList.class);
                startActivity(intent);

            }
        });
        //method to navigate to the UserViewProductList

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserDashboard.this, UserViewProductList.class);
                startActivity(intent);

            }
        });
        btnBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserDashboard.this, ShoppingBasket.class);
                intent.getParcelableArrayListExtra("ShopinBasket");
                startActivity(intent);

            }
        });


        //method to log out
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserDashboard.this, MainActivity.class);
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