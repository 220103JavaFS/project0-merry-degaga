package com.revature.dao;

import com.revature.users.cart.food.Food;

import java.util.ArrayList;

public interface MenuDAO {
    ArrayList<Food> getMenu();
    boolean addMenuItem(Food food);
    void removeMenuItem(String name);
    void editMenuItem(String name);
}
