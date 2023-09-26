package com.example.kjlocalshoppingapp;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private Context context;
    private List<User>userList;
    public UsersAdapter (Context context, List<User>userList){
        this.context = context;
        this.userList=userList;
    }



    //method to update the data in the adapter
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_users,parent, false);
        return new ViewHolder(view);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        int txtId = userList.get(position).getId();
        String edtUsername = userList.get(position).getUsername();
        String edtPassword = userList.get(position).getPassword();

        String edtFullname = userList.get(position).getFullname();

        String edtEmail = userList.get(position).getEmail();

        LocalDate edtDateRegistered = userList.get(position).getDateRegistered();

        java.time.LocalDate edtDateReviewed = userList.get(position).getDateReviewed();

        String edtHobbies = userList.get(position).getHobbies();

        String edtAddress = userList.get(position).getAddress();

        String edtPostcode = userList.get(position).getPostcode();

        String edtRank = userList.get(position).getRank();

        holder.setData (txtId, edtUsername,edtPassword, edtFullname,edtEmail, edtDateRegistered, edtDateReviewed, edtHobbies, edtAddress, edtPostcode, edtRank);

    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id;
        private EditText username,password,fullname, email, dateRegistered, dateReviewed, hobbies, address, postcode, rank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtId);
            username = itemView.findViewById(R.id.edtUsername);
            password = itemView.findViewById(R.id.edtPassword);
            fullname = itemView.findViewById(R.id.edtFullname);
            email = itemView.findViewById(R.id.edtEmail);
            dateRegistered = itemView.findViewById(R.id.edtDateRegistered);
            dateReviewed = itemView.findViewById(R.id.edtDateReviewed);
            hobbies = itemView.findViewById(R.id.edtHobbies);
            address =  itemView.findViewById(R.id.edtAddress);
            postcode = itemView.findViewById(R.id.edtPostcode);
            rank = itemView.findViewById(R.id.edtRank);

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void setData(int txtid, String edtUsername, String edtPassword, String edtFullname, String edtEmail,java.time.LocalDate edtDateRegistered, java.time.LocalDate edtDateReviewed, String edtHobbies,  String edtAddress, String edtPostcode,String edtRank ) {
            id.setText(Integer.toString(txtid));
            username.setText(String.valueOf(edtUsername));
            password.setText(String.valueOf(edtPassword));
            fullname.setText(String.valueOf(edtFullname));
            email.setText(String.valueOf(edtEmail));
            java.time.format.DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dateRegistered.setText(formatter.format(edtDateRegistered));
            dateReviewed.setText(formatter.format(edtDateReviewed));
            hobbies.setText(String.valueOf(edtHobbies));
            address.setText(String.valueOf(edtAddress));
            postcode.setText(String.valueOf(edtPostcode));
            rank.setText(String.valueOf(edtRank));

        }
    }
}
