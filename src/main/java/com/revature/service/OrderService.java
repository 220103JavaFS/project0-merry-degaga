package com.revature.service;

import com.revature.dao.OrderDAO;
import com.revature.dao.OrderDAOImp;
import com.revature.users.OrderDTO;

import java.util.ArrayList;

public class OrderService {
    private OrderDAO dao = new OrderDAOImp();
    public OrderService(){}

    /**
     * Gets all the submitted_orders for a manager or employee
     * @return an Arraylist of order idems
     */
    public ArrayList<OrderDTO> getAllOrders(){
        return dao.getAllOrders();
    }

    /**
     * Manager or Employee can automatically complete all the orders submitted by a customer
     * @param orderID is the customer's id to complete the order
     */
    public void completeOrder(int orderID){
        dao.completeOrder(orderID);
    }
}
