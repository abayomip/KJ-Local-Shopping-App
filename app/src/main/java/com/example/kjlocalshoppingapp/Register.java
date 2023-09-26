package com.example.kjlocalshoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RequiresApi(api = Build.VERSION_CODES.O)

//Arraylist of objects
public class Register extends AppCompatActivity {
    List<User> userList;
    LinearLayoutManager layoutManager;

    databasecon db;
    UsersAdapter adapter;
    Date date = new Date();

    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    EditText id, username,  password,  fullname,  email,  dateRegistered,  dateReviewed,  hobbies,  address,  postcode, rank;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userList = new ArrayList<>();
        adapter = new UsersAdapter(this, userList);
        id = findViewById(R.id.txtId);
        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        fullname = findViewById(R.id.edtFullname);
        email = findViewById(R.id.edtEmail);
        dateRegistered = findViewById(R.id.edtDateRegistered);
        dateReviewed = findViewById(R.id.edtDateReviewed);
        hobbies =  findViewById(R.id.edtHobbies);
        address = findViewById(R.id.edtAddress);
        postcode = findViewById(R.id.edtPostcode);
        rank = findViewById(R.id.edtRank);
        btnAdd = findViewById(R.id.btnAdd);

//A method to insert user details into the database
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasecon db = new databasecon(Register.this);
                // check if any field is empty if
                if (id.getText().toString().isEmpty()|| username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || fullname.getText().toString().isEmpty()||
                        email.getText().toString().isEmpty() || dateRegistered.getText().toString().isEmpty() || dateReviewed.getText().toString().isEmpty() || hobbies.getText().toString().isEmpty()||
                        address.getText().toString().isEmpty()|| postcode.getText().toString().isEmpty() || rank.getText().toString().isEmpty()) {
                    Toast.makeText(Register.this, "Complete each field", Toast.LENGTH_SHORT).show();
                    return;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate DateRegisteredLocalDate = LocalDate.parse(dateRegistered.getText().toString(), formatter);
                LocalDate dateReviewedLocalDate = LocalDate.parse(dateReviewed.getText().toString(), formatter);
                User u1 = new User(Integer.parseInt(id.getText().toString()), username.getText().toString(), password.getText().toString(), fullname.getText().toString(), email.getText().toString(),
                        DateRegisteredLocalDate, dateReviewedLocalDate,  hobbies.getText().toString(), address.getText().toString(), postcode.getText().toString(), rank.getText().toString());
                db.addUser(u1);
                userList.add(u1);
                adapter.notifyDataSetChanged();

                Toast.makeText(Register.this, "User Succesfully Added", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(Register.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    }
