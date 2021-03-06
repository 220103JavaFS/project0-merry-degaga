package com.revature.service;


import com.revature.controllers.Role;
import com.revature.exceptions.MyException;
import com.revature.users.cart.food.Ingredient;

import java.util.ArrayList;

//utility class mainly used by the Service layer to validate the various user inputs
public final class Validator {

    private static String pattern;

    //make into a singleton
    public Validator(){}

    //default utility methods
    static boolean isValidName(String name) {
        if(isEmpty(name)) {
            throw new MyException("No name provided");
        }
        pattern = "[A-Za-z]{3,20} [A-Za-z]{3,20}";
        if(!name.matches(pattern)) {
            throw new MyException("Invalid Name entry.");
        }
        return true;
    }

    static boolean isValidEmail(String email) {
        if(isEmpty(email)) {
            throw new MyException("No email provided");
        }
        pattern = "[A-Za-z]{1,32}([A-Za-z]|[0-9]){0,32}@[A-Za-z]{1,121}([A-Za-z]|[0-9]){0,121}.[a-z]{3}";
        if(!email.matches(pattern)){
            throw new MyException("Invalid email entry.");
        }
        return true;
    }

    static boolean isValidPhoneNumber(String phoneNumber){
        pattern = "[1-9]{3}.\\d{3}.\\d{4}";
        if(!phoneNumber.matches(pattern)){
            throw new MyException("Invalid phone number entry.");
        }
        return true;
    }

    static boolean isValidDOB(String dob){
        pattern = "([1-9]|([1][0-2]))";

        int slash = dob.indexOf('/');
        String month = dob.substring(0,slash);

        if(!month.matches(pattern)) {
            throw new MyException("Invalid month entry");
        }
        //month is valid so check date
        switch (month) {
            case "2": break;
            case "7":
            case "6":
            case "9":
            case "11":pattern += "[/]((([1-9])|([1-2][0-9]))|30)";
                      break;
            default: pattern += "[/]((([1-9])|([1-2][0-9]))|30|31)";
                break;
        }
        if(!dob.matches(pattern)) {
            throw new MyException("Invalid date entry");
        }
        return true;
    }

    private static boolean isEmpty(String input){
        input = input.trim();
        if(input == null || input == "") {
            return true;
        }
        return false;
    }

    public static boolean isValidCredentials(String userId, String secret) {
        if(userId == null || secret == null || userId.trim() == "" || secret.trim() == "") {
            throw new MyException("No userId or password provided");
        }
        return true;
    }

    public static boolean isValidUserId(String userId) {
        if(userId == null || userId.trim() == "") {
            throw new MyException("No userId provided");
        }
        return true;
    }
    public static boolean isValidSecret(String secret) {
        if(secret == null || secret.trim() == "") {
            throw new MyException("No password provided");
        }
        return true;
    }

    public static boolean isValidRole(String rolez) {
        if(rolez == null){
            throw new MyException("No role provided");
        }
        Role[] roles = Role.values();
        boolean isInRoles = false;
        for (Role r: roles) {
            if(rolez.equalsIgnoreCase(r.toString())){
                isInRoles = true;
            }
        }
        if(isInRoles) return true;
        else throw new MyException("Invalid role provided");
    }

    public static boolean isValidFoodName(String foodName){
        if(foodName == null){
            throw new MyException("No food name provided");
        }
        pattern = "[a-zA-Z ]*";
        if(foodName.matches(pattern)) {
            return true;
        }
        throw new MyException("Invalid food name");
    }

    public static boolean isValidFoodDescription(String description){
        pattern = "[a-zA-Z0-9,\\-\\. ]*";
        if(description.matches(pattern)) {
            return true;
        }
        throw new MyException("Invalid description format");
    }

    public static boolean isValidPrice(double price){
        pattern = "([1-9].[0-9]{1,2})|([1-9][0-9].[0-9]{1,2})";
        if(Double.toString(price).matches(pattern)){
            return true;
        }
        throw new MyException("Invalid price provided");
    }

    public static boolean isValidIngredients(ArrayList<Ingredient> ingredient) {
        pattern = "[a-zA-Z0-9 ]*";
        String pattern2 = "[1-9]";
        for(Ingredient i: ingredient){
            if(!i.getIngredientName().matches(pattern) || !(Integer.toString(i.getCallsFor()).matches(pattern2))) {
                throw new MyException("Invalid ingredient name or calls for amount");
            }
        }
        return true;
    }

    public static boolean isValidAvailable(int available){
        if(available >= 0) {
            return true;
        }
        throw new MyException("Invalid available food items inputted");
    }

    public static boolean isValidQuantity(int quantity) {
        if(quantity > 0) {
            return true;
        }
        throw new MyException("Invalid quantity inputted");
    }

}
