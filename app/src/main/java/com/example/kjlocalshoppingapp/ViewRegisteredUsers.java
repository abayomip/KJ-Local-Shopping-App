package com.example.kjlocalshoppingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
public class ViewRegisteredUsers extends AppCompatActivity {
    //private static final String TAG = "ViewProductList";
    private static final String TAG = "ViewRegisteredUser";

    RecyclerView recyclerViewUsers;
    List<User> userList;
    LinearLayoutManager layoutManager;
    databasecon db;
    UsersAdapter adapter;
    Date date = new Date();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Button btnAdd, btnPrevious, btnNext,btnUpdate,btnDelete;

    int currentposition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_users);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        recyclerViewUsers = findViewById(R.id.rcvList);

        //btnViewAll = findViewById(R.id.btnViewAll);
        db = new databasecon(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            userList = (List<User>) db.getAllRegUser();
        }
        currentposition = 0;
        initRecyclerView();
        loadData(currentposition);
//Method to update the registered user
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the position of the product to update position
                int position = currentposition;
                //get the product obj from the the list by its position
                User u1 = userList.get(position);
                View view = findViewById(android.R.id.content);
                EditText edtUsername = view.findViewById(R.id.edtUsername);
                EditText edtPassword = view.findViewById(R.id.edtPassword);
                EditText edtFullname = view.findViewById(R.id.edtFullname);
                EditText edtEmail = view.findViewById(R.id.edtEmail);
                EditText edtDateRegistered = view.findViewById(R.id.edtDateRegistered);
                EditText edtDateReviewed = view.findViewById(R.id.edtDateReviewed);
                EditText edtHobbies = view.findViewById(R.id.edtHobbies);
                EditText edtAddress = view.findViewById(R.id.edtAddress);
                EditText edtPostcode = view.findViewById(R.id.edtPostcode);
                EditText edtRank = view.findViewById(R.id.edtRank);


                //get the text from the editview variables
                String Username = edtUsername.getText().toString();
                String Password = edtPassword.getText().toString();
                String Fullname = edtFullname.getText().toString();
                String Email = edtEmail.getText().toString();
                String DateRegistered = edtDateRegistered.getText().toString();
                String DateReviewed = edtDateReviewed.getText().toString();
                String Hobbies = edtHobbies.getText().toString();
                String Address = edtAddress.getText().toString();
                String Postcode = edtPostcode.getText().toString();
                String Rank = edtRank.getText().toString();

//checking if any field is empty
                if (!Username.isEmpty()){
                    u1.setUsername(Username);
                }
                if (!Password.isEmpty()){
                    u1.setPassword(Password);
                }
                if (!Fullname.isEmpty()){
                    u1.setFullname(Fullname);
                }
                if (!Email.isEmpty()){
                    u1.setEmail(Email);
                }
                if (!DateRegistered.isEmpty()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(DateRegistered, formatter);
                    u1.setDateRegistered(date);
                }
                if (!DateReviewed.isEmpty()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(DateReviewed, formatter);

                    u1.setDateReviewed(date);
                }
                if (!Hobbies.isEmpty()){
                    u1.setHobbies(Hobbies);
                }
                if (!Address.isEmpty()){
                    u1.setAddress(Address);
                }
                if (!Postcode.isEmpty()){
                    u1.setPostcode(Postcode);
                }

                if (!Rank.isEmpty()){
                    u1.setRank(Rank);
                }


                // update the product in the database
                db.updateUser(u1);
                //notify the adapter that the data has changedd
                adapter.notifyDataSetChanged();
                Toast.makeText(ViewRegisteredUsers.this, "Succesfull", Toast.LENGTH_SHORT).show();


            }
        });

// delete method to delete selected row
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtId = findViewById(R.id.txtId);
                String ID = txtId.getText().toString();
                if (ID.isEmpty()) {
                    Toast.makeText(ViewRegisteredUsers.this, "Please enter a valid ID", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean result = db.deleteUser(new User(Integer.parseInt(ID)));
                if (result) {
                    // show a success message
                    Toast.makeText(ViewRegisteredUsers.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // show an error message
                    Toast.makeText(ViewRegisteredUsers.this, "User not found or deletion failed", Toast.LENGTH_SHORT).show();
                }

            }
        });


//a method to move next to the next row
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentposition >= 0 && currentposition < userList.size())
                    currentposition++;
                loadData(currentposition);

            }
        });

//a method to move back to the previous row
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentposition >= 0 && currentposition < userList.size())
                    currentposition--;
                loadData(currentposition);
            }
        });
    }


    private void initRecyclerView () {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewUsers.setLayoutManager(layoutManager);
        adapter = new UsersAdapter(getApplicationContext(), userList);
        recyclerViewUsers.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    //Displaying the productlist via the adapter
    private void loadData(int currentposition) {
        List<User> userList = db.getAllRegUser();
        if (userList.isEmpty()) {
            Toast.makeText(this, "No Product Available", Toast.LENGTH_SHORT).show();
        } else{
            User users = userList.get(currentposition);
            List<User> List = new ArrayList<>();
            List.add(users);
            // Passing the ProductList to the adapter
            UsersAdapter adapter = new UsersAdapter(this, List);
            recyclerViewUsers.setAdapter(adapter);
            // Use the properties of the user object here
            int id = users.getId();
            String username = users.getUsername();
            String password = users.getPassword();
            String fullname = users.getFullname();
            String email = users.getEmail();
            LocalDate dateRegistered = users.getDateRegistered();
            LocalDate dateReviewed = users.getDateReviewed();
            String hobbies = users.getHobbies();
            String address = users.getAddress();
            String postcode = users.getPostcode();
            String rank = users.getRank();


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