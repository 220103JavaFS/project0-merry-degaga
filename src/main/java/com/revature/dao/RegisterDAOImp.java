package com.revature.dao;

import com.revature.users.User;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDAOImp implements RegisterDAO {
    @Override
    public boolean register(User user) {
        try(Connection conn = ConnectionUtil.getConnection()){
            //String sql = "INSERT INTO users (firstname, lastname, email, phone_number, dob, user_id, secret, rolez)
            // " +
            //        "VALUES (?, ?, ?, ?, ?,?,crypt(?, gen_salt('bf')),?);";
            String sql = "INSERT INTO users (firstname, lastname, email, phone_number, dob, user_id, secret, rolez) " +
                    "Values (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            int count = 0;
            statement.setString(++count,user.getFirstname());
            statement.setString(++count,user.getLastname());
            statement.setString(++count,user.getEmail());
            statement.setString(++count,user.getPhoneNumber());
            statement.setString(++count,user.getDob());
            statement.setString(++count,user.getUserId());
            statement.setString(++count,user.getSecret());
            statement.setString(++count,user.getRolez());

            statement.execute();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
