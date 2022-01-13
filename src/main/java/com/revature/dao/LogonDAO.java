package com.revature.dao;

interface LogonDAO {
    boolean logon(String username, String password);
    boolean logout();

}
