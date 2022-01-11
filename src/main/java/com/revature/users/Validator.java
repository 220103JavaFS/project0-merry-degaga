package com.revature.users;

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
        pattern = "[A-Za-z]{1,32}([A-Za-z]|[0-9]){0,32}@[A-Za-z]{1,121}([A-Za-z]|[0-9]){0,121}.[a-z]{3,3}";
        if(!email.matches(pattern)){
            //throw new exception
        }
        return true;
    }

    static boolean isValidPhoneNumber(String phoneNumber){
        pattern = "[1-9]{3,3}.\\d{3,3}.\\d{4,4}";
        if(!phoneNumber.matches(pattern)){
            //throw exception
        }
        return true;
    }

    static boolean isValidDOB(String dob){
        //format month/date
        //month abbr month/1-...
        pattern = "";
        return false;
    }

    private static boolean isEmpty(String input){
        input = input.trim();
        if(input == null || input == "") {
            return true;
        }
        return false;
    }



}
