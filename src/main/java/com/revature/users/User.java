package com.revature.users;

import java.util.Objects;

//parent class that contains user field information
public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String dob;
    private String userId;
    private String secret;
    private String rolez;

   public User(){}

    public User(String firstname, String lastname, String email, String phoneNumber, String dob, String userId, String secret, String rolez) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.userId = userId;
        this.secret = secret;
        this.rolez = rolez;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
           this.secret = secret;
    }

    public String getRolez() {
        return rolez;
    }

    public void setRolez(String rolez) {
        this.rolez = rolez;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(dob, user.dob) && Objects.equals(userId, user.userId) && Objects.equals(secret, user.secret) && Objects.equals(rolez, user.rolez);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, email, phoneNumber, dob, userId, secret, rolez);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob='" + dob + '\'' +
                ", userId ='" + userId + '\'' +
                ", secret='" + secret + '\'' +
                ", rolez='" + rolez + '\'' +
                '}';
    }
}
