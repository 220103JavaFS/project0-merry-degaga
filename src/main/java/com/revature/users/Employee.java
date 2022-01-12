package com.revature.users;

import java.util.Objects;

public class Employee extends User{

    private String userID;
    private boolean isLoggedIn;

    public Employee(String name, String email, String phoneNumber, String dob){
        super(name, email, phoneNumber, dob);
        this.isLoggedIn = false;
        //userID?
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return isLoggedIn == employee.isLoggedIn && Objects.equals(userID, employee.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userID, isLoggedIn);
    }

    @Override
    public String toString() {
        return super.toString() + "Employee{" +
                "userID='" + userID + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }

    //concrete methods
//    public void completeOrder(){}
//    public void takeOrder(){}
//    public void login(){}
//    public void logout(){}


    //abstract methods
//    @Override
//    protected void addItem() {
//    }
//
//    @Override
//    protected void removeItem() {
//    }
}
