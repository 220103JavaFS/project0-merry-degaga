package com.revature.users;


import com.revature.exceptions.MyException;

import java.util.Objects;

public abstract class User {
    private String name;
    private String email;
    private String phoneNumber;
    private String dob;

    public User(){}

    public User(String name, String email, String phoneNumber, String dob){
        super();
        //validate input with utility class
        try {
            if (Validator.isValidName(name)) {
                this.name = name;
            }
            if (Validator.isValidEmail(email)) {
                this.email = email;
            }
            if (Validator.isValidPhoneNumber(phoneNumber)) {
                this.phoneNumber = phoneNumber;
            }
            if (Validator.isValidDOB(dob)) {
                this.dob = dob;
            }
        } catch(MyException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //concrete methods
    //protected void getMenu(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(dob, user.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, dob);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }

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
    //protected abstract void addItem();
    //protected abstract void removeItem();

}
