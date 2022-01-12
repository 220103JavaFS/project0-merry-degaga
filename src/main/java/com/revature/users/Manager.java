package com.revature.users;

import java.util.Objects;

public class Manager extends Employee{


    public Manager(String name, String email, String phoneNumber, String dob){
        super(name, email, phoneNumber,dob);
    }

//    public void addEmployee(){}
//
//    public void removeEmployee(){}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(super.getUserID(), manager.getUserID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), super.getUserID());
    }

    @Override
    public String toString() {
        return super.toString() + "Manager{" +
                "userID='" + super.getUserID() + '\'' +
                '}';
    }

//    @Override
//    protected void addItem() {
//
//    }
//
//    @Override
//    protected void removeItem() {
//
//    }
}
