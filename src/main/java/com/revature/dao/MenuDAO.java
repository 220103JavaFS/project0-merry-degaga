package com.revature.dao;

import com.revature.users.cart.food.Food;

public interface MenuDAO {
    void addMenuItem(Food food);
    void removeMenuItem(String name);
    void editMenuItem(String name);
}
