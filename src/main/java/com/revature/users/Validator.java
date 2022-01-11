package com.revature.users;

import java.util.Locale;

public final class Validator {

    private static String pattern;

    public Validator(){}

    //default utility methods
    static boolean isValidName(String name){
        //name is only contains characters A-Za-z
        //follow format first lastname
        if(isEmpty(name)) {
            //throw new Exception();
        }
        pattern = "[A-Za-z]{3,20} [A-Za-z]{3,20}";
        if(!name.matches(pattern)) {
            //throw error that does not follow pattern
        }
        return true;
    }

    static boolean isValidEmail(String email){
        if(isEmpty(email)) {
            //throw new Exception();
        }
        pattern = "[A-Za-z]{1,32}([A-Za-z]|[0-9]){0,32}@[A-Za-z]{1,121}([A-Za-z]|[0-9]){0,121}.[a-z]{3}";
        if(!email.matches(pattern)){
            //throw new exception
        }
        return true;
    }

    static boolean isValidPhoneNumber(String phoneNumber){
        pattern = "[1-9]{3}.\\d{3}.\\d{4}";
        if(!phoneNumber.matches(pattern)){
            //throw exception
        }
        return true;
    }

    static boolean isValidDOB(String dob){
        pattern = "([1-9]|([1][0-2]))";

        int slash = dob.indexOf('/');
        String month = dob.substring(0,slash);

        if(!month.matches(pattern)) {
            //throw error invalid month
        }

        //month is valid so check date

        switch (month) {
            case "2": break;
            case "7":
            case "6":
            case "9":
            case "11":pattern = "[/]((([1-9])|([1-2][0-9]))|30)";
                      break;
            default: pattern = "[/]((([1-9])|([1-2][0-9]))|30|31)";
                break;
        }

        if(!dob.matches(pattern)) {
            //throw error bad date
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



}
