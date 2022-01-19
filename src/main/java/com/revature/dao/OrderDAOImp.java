package com.revature.dao;

import com.revature.users.FoodDTO;
import com.revature.users.OrderDTO;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImp implements OrderDAO{

    /**
     *
     * @return All the submitted orders that a manager or employee needs to completes
     */
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
                order.orderID = Integer.parseInt(result.getString("customer_id"));
                orders.add(order);
            }
            return orders;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<OrderDTO>();
    }

    /**
     *
     * @param orderID is the order(s) to complete, will remove this from the orders_submitted table. can be
     *                refractored to have a trigger so that manager or employee can keep track of what orders were
     *                completed
     */
    @Override
    public void completeOrder(int orderID) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "DELETE FROM orders_submitted WHERE customer_id = CAST(? AS INTEGER)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, Integer.toString(orderID));
            statement.execute();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
