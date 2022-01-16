package com.revature.users;

public class Employee extends User{

    //public Employee(){}

    public Employee(String firstname, String lastname, String email, String phoneNumber, String dob, String userId, String secret, String rolez) {
        super(firstname, lastname, email, phoneNumber, dob, userId, secret, rolez);
    }

    //created incase of future fields specific to employee
}
