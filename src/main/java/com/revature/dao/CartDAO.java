package com.revature.dao;

import com.revature.users.Customer;
import com.revature.users.FoodDTO;
import com.revature.users.cart.food.Food;

import java.util.ArrayList;

public interface CartDAO {


    void editItemInCart(String name);
    void submitOrder(Customer customer);

    Double checkMenuItem(FoodDTO food);
    void updateAvailable(FoodDTO food);
}
