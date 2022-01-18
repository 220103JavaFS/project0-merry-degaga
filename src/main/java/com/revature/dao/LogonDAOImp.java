package com.revature.dao;

import com.revature.users.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogonDAOImp implements LogonDAO {

    /**
     * used to grab the encrypted password from the database to service layer
     * @param username
     * @return
     */
    public User logon(String username){
    //public String logon(String username, String secret){ //make this return the whole row when userID matches
        try(Connection conn = ConnectionUtil.getConnection()){
            //String sql = "SELECT * FROM users WHERE user_id = ? AND secret = crypt(?, secret);";
            String sql = "SELECT * FROM users WHERE user_id = ?;";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,username);
            //statement.setString(2, secret);
            ResultSet result = statement.executeQuery();
            User user = new User();
            if(result.next()) {
                user.setFirstname(result.getString("firstname"));
                user.setLastname(result.getString("lastname"));
                user.setEmail(result.getString("email"));
                user.setPhoneNumber(result.getString("phone_number"));
                user.setDob(result.getString("dob"));
                user.setUserId(result.getString("user_id"));
                user.setSecret(result.getString("secret"));
                user.setRolez(result.getString("rolez"));
                //return result.getString("rolez");
            }
            return user;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return new User();
        //return null;
    }




}
