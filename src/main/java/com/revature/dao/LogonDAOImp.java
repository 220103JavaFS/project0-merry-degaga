package com.revature.dao;

import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class LogonDAOImp implements LogonDAO {

    public String logon(String username){ //make this return the whole row when userID matches
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




}
