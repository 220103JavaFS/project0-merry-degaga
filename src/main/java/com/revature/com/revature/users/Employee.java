package com.revature.com.revature.users;

public class Employee extends User{

    private String userID;
    private boolean isLoggedIn;

    public Employee(String name, String email, int phoneNumber, int dob){
        super(name, email, phoneNumber, dob);
        this.isLoggedIn = false;
        //userID?
    }

    //concrete methods
    public void completeOrder(){}
    public void takeOrder(){}
    public void login(){}
    public void logout(){}
    

    //abstract methods
    @Override
    protected void addItem() {
    }

    @Override
    protected void removeItem() {
    }
}
