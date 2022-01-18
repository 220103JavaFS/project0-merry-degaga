package com.revature.exceptions;

public final class MyException extends RuntimeException{
    //mainly used by the Validator to notify if a user inputted input incorrectly
    public MyException(String message){
        super(message);
    }
}
