package com.revature.dao;

import com.revature.users.Customer;
import com.revature.users.FoodDTO;
import com.revature.users.cart.food.Food;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAOImp implements CartDAO {

    public CartDAOImp() {
    }


    @Override
    public void editItemInCart(String name) {
        try (Connection conn = ConnectionUtil.getConnection()) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void submitOrder(Customer customer) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO orders_submitted (food_name, quantity, charge, customer_id) VALUES (?,CAST(? " +
                    "AS INTEGER),CAST (? AS NUMERIC),CAST(? AS INTEGER));";
            PreparedStatement statement = conn.prepareStatement(sql);
            int count = 0;
            for (FoodDTO item : customer.getCart().getCart()) { //mistake...
                statement.setString(++count, item.foodName);
                statement.setString(++count, Integer.toString(item.quantity));
                statement.setString(++count, Double.toString(item.price));
                statement.setString(++count, Integer.toString(customer.getOrderID()));
                statement.execute();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAvailable(FoodDTO food) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT available FROM menu WHERE food_name=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, food.foodName);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                sql = "UPDATE menu SET available=CAST(? AS INTEGER) WHERE food_name=?;";
                statement = conn.prepareStatement(sql);
                statement.setString(1, Integer.toString(food.quantity+result.getInt("available")));
                statement.setString(2, food.foodName);
                statement.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Double checkMenuItem(FoodDTO food) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM menu WHERE food_name=? AND available >= CAST (? AS INTEGER);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, food.foodName);
            statement.setString(2, Integer.toString(food.quantity));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                //update menu price
                sql = "UPDATE menu SET available=CAST(? AS INTEGER) WHERE food_name=?;";
                double price = result.getDouble("price");
                statement = conn.prepareStatement(sql);
                statement.setString(1, Integer.toString(result.getInt("available") - food.quantity));
                statement.setString(2, food.foodName);
                statement.execute();
                return price;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
