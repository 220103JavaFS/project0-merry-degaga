package com.revature.dao;

import com.revature.users.cart.food.Food;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class CartDAOImp implements CartDAO{

    public CartDAOImp(){}

    @Override
    public void addItemToCart(Food food) {

        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeItemFromCart(String name) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTotalOrder() {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editItemInCart(String name) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
