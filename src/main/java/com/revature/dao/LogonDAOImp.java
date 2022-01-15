package com.revature.dao;

public class LogonDAOImp implements LogonDAO {

    public boolean logon(String username, String password){
        return false;
    }

    @Override
    public String getUserRole(String username) {
        return "Manager";
    }


}
