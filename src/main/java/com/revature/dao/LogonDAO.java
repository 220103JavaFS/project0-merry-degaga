package com.revature.dao;

import com.revature.users.User;

public interface LogonDAO {
    //String logon(String username, String secret);
    User logon(String username);

}
