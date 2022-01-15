package com.revature.dao;

import com.revature.users.cart.food.Food;

public interface CartDAO {

    void addItemToCart(Food food);
    void removeItemFromCart(String name);
    void getTotalOrder();
    void editItemInCart(String name);
}
