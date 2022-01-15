package com.revature.dao;

import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class LogonDAOImp implements LogonDAO {

    public boolean logon(String username, String password){
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getUserRole(String username) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "Manager";
    }


}
