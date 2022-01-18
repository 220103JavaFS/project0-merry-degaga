package com.revature.dao;

import com.revature.users.OrderDTO;
import java.util.ArrayList;

public interface OrderDAO {
    ArrayList<OrderDTO> getAllOrders();
    void completeOrder(int orderID);
}
