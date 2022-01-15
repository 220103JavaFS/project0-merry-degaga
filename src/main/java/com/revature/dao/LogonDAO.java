package com.revature.dao;

public interface LogonDAO {
    boolean logon(String username, String ciphertext);
    String getUserRole(String username);
}
