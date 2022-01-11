package com.revature.com.revature.users;

public abstract class User {
    private String name;
    private String email;
    private int phoneNumber;
    private int dob;

    protected User(String name, String email, int phoneNumber, int dob){
        super();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    //concrete methods
    protected void getMenu(){}



    //getters and setters
    protected String getName(){
        return this.name;
    }
    protected String getEmail(){
        return this.email;
    }
    protected int getPhoneNumber(){
        return this.phoneNumber;
    }
    protected int getDob(){
        return  this.dob;
    }


    //abstract methods
    protected abstract void addItem();
    protected abstract void removeItem();

}
