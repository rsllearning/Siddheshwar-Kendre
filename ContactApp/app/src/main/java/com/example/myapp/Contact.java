package com.example.myapp;

public class Contact {
    String name,number,email;
    int id;

    public Contact(){

    }

    public Contact( int id,String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.id = id;
    }

    public Contact(String name, String number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
