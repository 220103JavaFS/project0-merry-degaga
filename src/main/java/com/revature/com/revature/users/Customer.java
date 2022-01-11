package com.revature.com.revature.users;

public class Customer extends User{

    public Customer(String name, String email, int phoneNumber, int dob){
        super(name, email, phoneNumber,dob);
    }

    @Override
    protected void addItem() {

    }

    @Override
    protected void removeItem() {

    }
}
