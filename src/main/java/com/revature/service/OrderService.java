package com.revature.service;

import com.revature.dao.OrderDAO;
import com.revature.dao.OrderDAOImp;
import com.revature.users.OrderDTO;

import java.util.ArrayList;

public class OrderService {
    private OrderDAO dao = new OrderDAOImp();
    public OrderService(){}

    public ArrayList<OrderDTO> getAllOrders(){
        return dao.getAllOrders();
    }

    public void completeOrder(int orderID){
        dao.completeOrder(orderID);
    }
}
