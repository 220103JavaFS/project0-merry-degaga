package com.revature.dao;

import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDAOImp implements OrderDAO{

    @Override
    public void getAllOrders() {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completeOrder() {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
