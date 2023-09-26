package com.example.kjlocalshoppingapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


//database connect
public class databasecon extends SQLiteOpenHelper {

    private ArrayList<ProductCtlr> productCtlrList;
    private ArrayList<User> listCtlrList;

    //declaring private instance variables
    private Cursor cursor;
    private Context context;

    private static String dbName = "KjProductManager";
    private static String ID = "id";
    //private static int ID = 0;
    private static String productName = "ProductName";
    private static String productDescription = "ProductDescription";
    private static String productPrice = "ProductPrice";
    private static String productListPrice = "ProductListPrice";
    private static String productRetailPrice = "ProductRetailPrice";

    private static String category = "Category";
    private static String dateCreated = "DateCreated";

    private static String dateUpdated = "DateUpdated";

    private static int dbVersion = 2;

    private static String username = "username";
    private static String password = "password";
    private static String fullname = "fullname";
    private static String email = "email";
    private static String dateRegistered = "dateRegistered";

    private static String hobbies = "hobbies";
    private static String address = "address";

    private static String postcode = "postcode";
    private static String rank = "rank";
    private static String dateReviewed = "dateReviewed";

    private static String dbTable = "tblProducts";
    private static  String registerTable = "table2";

//constructor for the databasecon
    public databasecon(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    //creating the products and the user table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + dbTable + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                productName + " TEXT, "
                + productDescription + " TEXT, "
                + productPrice + " TEXT, "
                + productListPrice + " TEXT, "
                + productRetailPrice + " TEXT, "
                + category + " TEXT, "
                + dateCreated + " TEXT, "
                + dateUpdated + " TEXT, "
                + dateReviewed + " TEXT) ;";
        sqLiteDatabase.execSQL(query);

        String registerTableQuery = "create table " + registerTable + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                username + " TEXT, "
                + password + " TEXT, "
                + fullname + " TEXT, "
                + email + " TEXT, "
                + dateRegistered + " TEXT, "
                + dateReviewed + " TEXT, "
                + hobbies + " TEXT, "
                + address + " TEXT, "
                + postcode + " TEXT, "
                + rank + " TEXT) ;";
        sqLiteDatabase.execSQL(registerTableQuery);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(dbTable);
        sqLiteDatabase.execSQL(registerTable);
        onCreate(sqLiteDatabase);

    }
    //The database add method to insert into the products table
    public void addProduct(ProductCtlr productCtlr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(productName, productCtlr.getProductName());
        values.put(productDescription, productCtlr.getProductDescription());
        values.put(productPrice, productCtlr.getProductPrice());
        values.put(productListPrice, productCtlr.getProductListPrice());
        values.put(productRetailPrice, productCtlr.getProductRetailPrice());
        values.put(category, productCtlr.getCategory());
        values.put(dateCreated, productCtlr.getDateCreated().toString());
        values.put(dateUpdated, productCtlr.getDateUpdated().toString());
        long result = db.insert(dbTable, null, values);
        if (result == -1) {
            Toast.makeText(context, "Unsuccesful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Succesfully Added", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

//The database update method for the products table
    public boolean updateProduct(ProductCtlr productCtlr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(productName, productCtlr.getProductName());
        values.put(productDescription, productCtlr.getProductDescription());
        values.put(productPrice, productCtlr.getProductPrice());
        values.put(productListPrice, productCtlr.getProductListPrice());
        values.put(productRetailPrice, productCtlr.getProductRetailPrice());
        values.put(category, productCtlr.getCategory());
        values.put(dateCreated, productCtlr.getDateCreated().toString());
        values.put(dateUpdated, productCtlr.getDateUpdated().toString());
        //calls the update method on the db object to update the selected row
        int finishResult = db.update(dbTable, values, ID + "=?",
                new String[]{String.valueOf(productCtlr.getId())});
        if (finishResult > 0) {
            return true;
        } else {
            return false;
        }

    }
//The database products table delete method
    public boolean deleteProduct(ProductCtlr productCtlr) {
        SQLiteDatabase db = this.getWritableDatabase();
        //calls the delete method on the db object to delete the selected row
        int finishResult = db.delete(dbTable, ID + "=?", new String[]{String.valueOf(productCtlr.getId())});
        if (finishResult > 0) {
            return true;
        } else {
            return false;
        }

    }
    //The database update method for the user register table
    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(username, user.getUsername());
        values.put(password, user.getPassword());
        values.put(fullname, user.getFullname());
        values.put(email, user.getEmail());
        values.put(dateRegistered, user.getDateRegistered().toString());
        values.put(dateReviewed, user.getDateReviewed().toString());
        values.put(hobbies, user.getHobbies());
        values.put(address, user.getAddress());
        values.put(postcode, user.getPostcode());
        values.put(rank, user.getRank());
        //calls the update method on the db object to update the selected row

        int finishResult = db.update(registerTable, values, ID + "=?",
                new String[]{String.valueOf(user.getId())});
        if (finishResult > 0) {
            return true;
        } else {
            return false;
        }

    }

    //The database products table delete method
    public boolean deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        //calls the delete method on the db object to delete the selected row
        int finishResult = db.delete(registerTable, ID + "=?", new String[]{String.valueOf(user.getId())});
        if (finishResult > 0) {
            return true;
        } else {
            return false;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
//DB method to load all product table fields
    List<ProductCtlr> getAllData() {
        List<ProductCtlr> productList = new ArrayList<>();
        String query = "SELECT * FROM " + dbTable;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            ProductCtlr product = new ProductCtlr();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setProductDescription(cursor.getString(2));
            product.setProductPrice(cursor.getString(3));
            product.setProductListPrice(cursor.getString(4));
            product.setProductRetailPrice(cursor.getString(5));
            product.setCategory(cursor.getString(6));
            product.setDateUpdated(LocalDate.parse(cursor.getString(7)));
            product.setDateCreated(java.time.LocalDate.parse(cursor.getString(8)));

            productList.add(product);
        }
        cursor.close();
        return productList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
//DB method to load all category field from the product table fields

    List<ProductCtlr> getAllCategories() {
        List<ProductCtlr> productList = new ArrayList<>();
        String query = "SELECT DISTINCT category FROM " + dbTable;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()){

            ProductCtlr product = new ProductCtlr();
            product.setCategory(cursor.getString(0));
            productList.add(product);
        }
        cursor.close();
        return productList;
    }

//DB method to load products in the category field from the product table fields
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<ProductCtlr> getDisplayCatItems(String category) {
        List<ProductCtlr> productList = new ArrayList<>();
        String query = "SELECT * FROM " + dbTable + " WHERE category = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{category});
        while (cursor.moveToNext()) {
            ProductCtlr product = new ProductCtlr();
            product.setId(Integer.parseInt(cursor.getString(0)));
            product.setProductName(cursor.getString(1));
            product.setProductDescription(cursor.getString(2));
            product.setProductPrice(cursor.getString(3));
            product.setProductListPrice(cursor.getString(4));
            product.setProductRetailPrice(cursor.getString(5));
            product.setCategory(cursor.getString(6));
            product.setDateUpdated(LocalDate.parse(cursor.getString(7)));
            product.setDateCreated(java.time.LocalDate.parse(cursor.getString(8)));
            productList.add(product);
        }
        cursor.close();
        return productList;
    }

//The database add method to insert into the register user table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(username, user.getUsername());
        values.put(password, user.getPassword());
        values.put(fullname, user.getFullname());
        values.put(email, user.getEmail());
        values.put(dateRegistered, user.getDateRegistered().toString());
        values.put(dateReviewed, user.getDateReviewed().toString());
        values.put(hobbies, user.getHobbies());
        values.put(address, user.getAddress());
        values.put(postcode, user.getPostcode());
        values.put(rank, user.getRank());

        long result = db.insert(registerTable, null, values);
        if (result == -1) {
            Toast.makeText(context, "Registration Unsuccesful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Succesfully Added", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
//DB method to load all Register users table fields
    List<User> getAllRegUser() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + registerTable;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            User user = new User();
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setFullname(cursor.getString(3));
            user.setEmail(cursor.getString(4));
            user.setDateRegistered(LocalDate.parse(cursor.getString(5)));
            user.setDateReviewed(java.time.LocalDate.parse(cursor.getString(6)));
            user.setHobbies(cursor.getString(7));
            user.setAddress(cursor.getString(8));
            user.setPostcode(cursor.getString(9));
            user.setRank(cursor.getString(10));

            userList.add(user);
        }
        cursor.close();
        return userList;
    }

    //method to query the product table selecting the username and password to login
    public boolean checkForUsernameAndPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from  table2 where username=? and password=?",
                new String[]{username,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }


    //this method is called when user login it direct the user to the assigned dashboard by checking the value in the rank column
    @SuppressLint("Range")
    public String getTheRank(String username) {
        String rankResult = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + rank + " FROM " + registerTable + " WHERE username=?", new String[]{username});;
        if (cursor.moveToFirst()){
            rankResult = cursor.getString(cursor.getColumnIndex(rank));

        }
        cursor.close();
        return rankResult;

    }
    //test method to check the product details are inserted in to the dashboard successfully
    @RequiresApi(api = Build.VERSION_CODES.O)
        public ProductCtlr getProdById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.query("tblProducts", new String[]{ "id", "productName",
                "productDescription",
                "productPrice",
                "ProductListPrice",
                "ProductRetailPrice",
                "Category",
                "DateCreated",
                "DateUpdated"
        }, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateUpdated = LocalDate.parse(cursor.getString(7), formatter);
            LocalDate dateCreated = LocalDate.parse(cursor.getString(8), formatter);

            ProductCtlr productTest = new ProductCtlr(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), dateCreated, dateUpdated);
            cursor.close();
            return productTest;
        }
        return null;

        //test method to check the user details are inserted in to the dashboard successfully
    }  @RequiresApi(api = Build.VERSION_CODES.O)
    public User getUserById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("table2", new String[]{ "id", "password",
                "password",
                "fullname",
                "email",
                "dateRegistered",
                "dateReviewed",
                "hobbies",
                "address",
                "postcode",
                "rank"
        }, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateRegistered = LocalDate.parse(cursor.getString(5), formatter);
            LocalDate dateReviewed = LocalDate.parse(cursor.getString(6), formatter);

            User Test = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), dateRegistered, dateReviewed, cursor.getString(7), cursor.getString(8), cursor.getString(9),cursor.getString(10));
            cursor.close();
            return Test;
        }
        return null;
    }


}
