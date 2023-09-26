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
public class ProductInfoAdapter extends RecyclerView.Adapter<ProductInfoAdapter.ViewHolder> {
    private Context context;
    private List<ProductCtlr>productCtlrList;
    public ProductInfoAdapter (Context context, List<ProductCtlr>productCtlrList){
        this.context = context;
        this.productCtlrList=productCtlrList;
    }



    //method to update the data in the adapter
    //public void setProductCtlrList(List<ProductCtlr> productCtlrList) {
      //  this.productCtlrList = productCtlrList;
    //}

    @NonNull
    @Override
    public ProductInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_productinfo,parent, false);
        return new ViewHolder(view);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ProductInfoAdapter.ViewHolder holder, int position) {
        int txtid = productCtlrList.get(position).getId();
        String txtproductName = productCtlrList.get(position).getProductName();
        String txtproductDescription = productCtlrList.get(position).getProductDescription();

        String txtproductPrice = productCtlrList.get(position).getProductPrice();

        String txtproductListPrice = productCtlrList.get(position).getProductListPrice();

        String txtproductRetailPrice = productCtlrList.get(position).getProductRetailPrice();

        String txtcategory = productCtlrList.get(position).getCategory();

        java.time.LocalDate txtdateCreated = productCtlrList.get(position).getDateCreated();

        java.time.LocalDate txtdateUpdated = productCtlrList.get(position).getDateUpdated();

        holder.setData (txtid, txtproductName, txtproductDescription, txtproductPrice, txtproductListPrice, txtproductRetailPrice, txtcategory, txtdateCreated, txtdateUpdated);


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
        private TextView id;
        private EditText productName, productDescription, productPrice, productListPrice, productRetailPrice, category, dateCreated, dateUpdated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtProductId);
            productName = itemView.findViewById(R.id.txtProductName);
            productDescription = itemView.findViewById(R.id.txtProductDis);
            productPrice = itemView.findViewById(R.id.txtProductPrice);
            productListPrice = itemView.findViewById(R.id.txtProductListPrice);
            productRetailPrice = itemView.findViewById(R.id.txtProductRetailPrice);
            category = itemView.findViewById(R.id.txtCategory);
            dateCreated = itemView.findViewById(R.id.txtDateCreated);
            dateUpdated =  itemView.findViewById(R.id.txtdateUpdated);

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void setData(int txtid, String txtproductName, String txtproductDescription, String txtproductPrice, String txtproductListPrice, String txtproductRetailPrice, String txtcategory, java.time.LocalDate txtdateCreated, java.time.LocalDate txtdateUpdated) {
            id.setText(Integer.toString(txtid));
            productName.setText(String.valueOf(txtproductName));
            productDescription.setText(String.valueOf(txtproductDescription));
            productPrice.setText(String.valueOf(txtproductPrice));
            productListPrice.setText(String.valueOf(txtproductListPrice));
            productRetailPrice.setText(String.valueOf(txtproductRetailPrice));
            category.setText(String.valueOf(txtcategory));
            java.time.format.DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dateCreated.setText(formatter.format(txtdateCreated));
            dateUpdated.setText(formatter.format(txtdateUpdated));

        }
    }
}