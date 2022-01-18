package com.revature.dao;

import com.revature.users.FoodDTO;
import com.revature.users.OrderDTO;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImp implements OrderDAO{

    @Override
    public ArrayList<OrderDTO> getAllOrders() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM orders_submitted";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            ArrayList<OrderDTO> orders = new ArrayList<>();
            while(result.next()) {
                OrderDTO order = new OrderDTO();
                order.foodName = result.getString("food_name");
                order.quantity = Integer.parseInt(result.getString("quantity"));
                order.price = Double.parseDouble(result.getString("charge"));
                order.orderID = Integer.parseInt(result.getString("order_id"));
                orders.add(order);
            }
            return orders;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<OrderDTO>();
    }

    @Override
    public void completeOrder(int orderID) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "DELETE FROM orders_submitted WHERE order_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, Integer.toString(orderID));
            statement.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
