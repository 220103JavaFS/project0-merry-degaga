package com.revature.service;

import com.revature.dao.OrderDAO;
import com.revature.dao.OrderDAOImp;

public class OrderService {
    private OrderDAO dao = new OrderDAOImp();
    public OrderService(){}

    public void getAllOrders(){
        //dao.getAllOrders();
    }

    public void completeOrder(){
        //dao.completeOrder();
    }
}
