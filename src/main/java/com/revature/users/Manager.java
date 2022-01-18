package com.revature.users;

import java.util.Objects;

//no specific methods or fields but application can be refractored to have these special methods/ fields
public class Manager extends Employee{

    //public Manager() {}

    public Manager(String firstname, String lastname, String email, String phoneNumber, String dob, String userId, String secret, String rolez) {
        super(firstname, lastname, email, phoneNumber, dob, userId, secret, rolez);
    }

    //created incase of future fields specific to manager
}
