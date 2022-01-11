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
        pattern = "";



        return true;
    }

    static boolean isValidEmail(String email){
        if(isEmpty(email)) {
            //throw new Exception();
        }
        pattern = "";
        //email contains numbers + characters only
        //format is email@provider.xxx

        return false;
    }

    static boolean isValidPhoneNumber(int phoneNumber){
        //contains only numbers 1-9then0-9
        //follow format xxx.xxx.xxxx
        pattern = "";

        return false;
    }

    static boolean isValidDOB(int dob){
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
