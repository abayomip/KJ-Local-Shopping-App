package com.example.kjlocalshoppingapp;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;


public class ProductCtlr implements Parcelable {

    private int id;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productListPrice;
    private String productRetailPrice;

    private String category;
    private java.time.LocalDate dateCreated;

    private java.time.LocalDate dateUpdated;

    public ProductCtlr(int id, String productName, String productDescription, String productPrice, String productListPrice, String productRetailPrice, String category, LocalDate dateCreated, LocalDate dateUpdated) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productListPrice = productListPrice;
        this.productRetailPrice = productRetailPrice;
        this.category = category;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public ProductCtlr(String productName, String productDescription, String productPrice, String productListPrice, String productRetailPrice, String category, LocalDate dateCreated, LocalDate dateUpdated) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productListPrice = productListPrice;
        this.productRetailPrice = productRetailPrice;
        this.category = category;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ProductCtlr() {
// initialize the product attributes with default values here
        this.id = 0;
        this.productName = "";
        this.productPrice = "";
        this.productListPrice = "";
        this.productRetailPrice = "";
        this.category = "";
        this.dateCreated = java.time.LocalDate.now();
        this.dateUpdated = java.time.LocalDate.now();

    }

    public ProductCtlr(int parseInt, String productDescription, String productPrice, String productListPrice, String productRetailPrice, String category, LocalDate dateUpdatedLocalDate, LocalDate dateCreatedLocalDate) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productListPrice = productListPrice;
        this.productRetailPrice = productRetailPrice;
        this.category = category;
        this.dateCreated = dateCreatedLocalDate;
        this.dateUpdated = dateUpdatedLocalDate;
    }

    public ProductCtlr(int id) {
        this.id = id;

    }


    protected ProductCtlr(Parcel in) {
        id = in.readInt();
        productName = in.readString();
        productDescription = in.readString();
        productPrice = in.readString();
        productListPrice = in.readString();
        productRetailPrice = in.readString();
        category = in.readString();
    }

    public static final Creator<ProductCtlr> CREATOR = new Creator<ProductCtlr>() {
        @Override
        public ProductCtlr createFromParcel(Parcel in) {
            return new ProductCtlr(in);
        }

        @Override
        public ProductCtlr[] newArray(int size) {
            return new ProductCtlr[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductListPrice() {
        return productListPrice;
    }

    public void setProductListPrice(String productListPrice) {
        this.productListPrice = productListPrice;
    }

    public String getProductRetailPrice() {
        return productRetailPrice;
    }

    public void setProductRetailPrice(String productRetailPrice) {
        this.productRetailPrice = productRetailPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public java.time.LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(java.time.LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public java.time.LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(java.time.LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

// returns a string that contains the product information
    public String toString() {
        return "Product Name: " + productName + "\n" +
                "Product Description: " + productDescription + "\n" +
                "Product Price: £" + productPrice + "\n" +
                "Product ListPrice: £" + productListPrice + "\n" +
                "Product RetailPrice : £" + productRetailPrice + "\n" +
                "Category: " + category + "\n" +
                "Date Created: " + dateCreated + "\n" +
                "Date Updated: " + dateUpdated;
}


//a parcelable interface used to pass data between the addToBasket method in the userViewProductList activity and the shoppingbasket activities
    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(productName);
        dest.writeString(productDescription);
        dest.writeString(productPrice);
        dest.writeString(productListPrice);
        dest.writeString(productRetailPrice);
        dest.writeString(category);
        dest.writeSerializable(dateCreated);
        dest.writeSerializable(dateUpdated);
    }
}