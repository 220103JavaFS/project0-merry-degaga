package com.revature.users;

public abstract class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String dob;

    protected User(String name, String email, String phoneNumber, String dob){
        super();
        //validate input with utility class
        if(Validator.isValidName(name)) {
            this.name = name;
        }
        if(Validator.isValidEmail(email)) {
            this.email = email;
        }
        if(Validator.isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
        if(Validator.isValidDOB(dob)) {
            this.dob = dob;
        }
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
    protected String getPhoneNumber(){
        return this.phoneNumber;
    }
    protected String getDob(){
        return  this.dob;
    }


    //abstract methods
    protected abstract void addItem();
    protected abstract void removeItem();

}
