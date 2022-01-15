package com.revature.service;

import com.revature.dao.CartDAO;
import com.revature.dao.CartDAOImp;
import com.revature.users.cart.food.Food;

public class CartService {
    private CartDAO dao = new CartDAOImp();
    public CartService(){}

    public void addItemToCart(Food food) {

    }
    public void removeItemFromCart(String name){}

    public void getTotalOrder(){}
    public void editItemInCart(String name){}
}
