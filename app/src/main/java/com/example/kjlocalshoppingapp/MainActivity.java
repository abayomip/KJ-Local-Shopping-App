
package com.example.kjlocalshoppingapp;


import static android.view.View.VISIBLE;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //Arraylist of objects
    List<User> userList;


//definition of UI element
    EditText edtUsername, edtPassword;
    TextView txtDisplay;
    //creating a new instances
    databasecon db;

    ProductInfoAdapter adapter;
    Button btnRegister, btnLogin;
    //An arraylist containing the objects of the class User.
    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initializing the UI elements by by find the in the layout
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        db = new databasecon(this);
        userList = (List<User>) db.getAllRegUser();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.linearlayout);
        txtDisplay = findViewById(R.id.txtDisplay);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, Register.class);
                startActivity(intent);

            }
        });

//login method that uses the rank value to navigate the user to either the users dashboard or the admin user dashboard
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            //getting the users username and password
            public void onClick(View v) {
                String getUsername = edtUsername.getText().toString();
                String getPassword = edtPassword.getText().toString();

                if (getUsername.equals("") || getPassword.equals("")) {
                    Toast.makeText(MainActivity.this, "Please complete each field", Toast.LENGTH_SHORT).show();
                } else {
                    //check if the entered details are valid
                    Boolean checkUser = db.checkForUsernameAndPassword(getUsername,getPassword);
                    //if valid it uses the rank value to navigate the user to the respective dashboard
                    if(checkUser==true) {
                        String rank = db.getTheRank(getUsername);
                        if(rank.equals("user")){
                            Toast.makeText(MainActivity.this, "Sign in Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, UserView.class);
                            startActivity(intent);
                        } else if (rank.equals("admin")) {
                            Toast.makeText(MainActivity.this, "Sign in Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, AdminView.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                    }
                }
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



