package com.example.kjlocalshoppingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private List<ProductCtlr> productCtlrList;

    public CategoryAdapter(Context context, List<ProductCtlr> productCtlrList) {
        this.context = context;
        this.productCtlrList = productCtlrList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category, parent, false);
        return new ViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        String txtCategory = productCtlrList.get(position).getCategory();
   {
            holder.setData(txtCategory);
        }

    }

    @Override
    public int getItemCount() {

        if (productCtlrList != null) {
            return productCtlrList.size();
        } else {
            return 0;
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText  category;
        Button btnVCP;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.txtCategory);
            btnVCP = itemView.findViewById(R.id.btnVCP);
            btnVCP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    ProductCtlr product = productCtlrList.get(position);
                    String category = product.getCategory();
                    Intent intent = new Intent(context, DisplayCatItems.class);
                    intent.putExtra("category", category);
                    context.startActivity(intent);
                }
            }
            );
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void setData(String txtCategory) {
            category.setText(String.valueOf(txtCategory));
        }

    }
}