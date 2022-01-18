package com.revature.users;

//again code can be refraactored to add Employee specific functions / fields
public class Employee extends User{

    //public Employee(){}

    public Employee(String firstname, String lastname, String email, String phoneNumber, String dob, String userId, String secret, String rolez) {
        super(firstname, lastname, email, phoneNumber, dob, userId, secret, rolez);
    }

    //created incase of future fields specific to employee
}
