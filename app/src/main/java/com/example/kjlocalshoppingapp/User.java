package com.example.kjlocalshoppingapp;

import java.time.LocalDate;

public class User {
  private  int id;
    private String username;
    private String password;
    private String fullname;
    private  String email;
    private  LocalDate dateRegistered;
    private   LocalDate dateReviewed;
    private   String hobbies;
    private   String address;
    private String postcode;

    private String rank;

    public User(int id, String username, String password, String fullname, String email, LocalDate dateRegistered, LocalDate dateReviewed, String hobbies, String address, String postcode,String rank) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.dateRegistered = dateRegistered;
        this.dateReviewed = dateReviewed;
        this.hobbies = hobbies;
        this.address = address;
        this.postcode = postcode;
        this.rank = rank;
    }

    public User() {

    }
    public User(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public LocalDate getDateReviewed() {
        return dateReviewed;
    }

    public void setDateReviewed(LocalDate dateReviewed) {
        this.dateReviewed = dateReviewed;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}


