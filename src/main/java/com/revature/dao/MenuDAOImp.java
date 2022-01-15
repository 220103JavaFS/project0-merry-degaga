package com.revature.dao;

import com.revature.users.cart.food.Food;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class MenuDAOImp implements MenuDAO{
    @Override
    public void addMenuItem(Food food) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMenuItem(String name) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editMenuItem(String name) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
