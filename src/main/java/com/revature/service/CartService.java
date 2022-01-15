package com.revature.service;

import com.revature.dao.CartDAO;
import com.revature.dao.CartDAOImp;
import com.revature.users.cart.food.Food;

public class CartService {
    private CartDAO dao = new CartDAOImp();
    public CartService(){}

    public void addItemToCart(Food food) {
        //dao.addItemToCart(food);
    }
    public void removeItemFromCart(String name){
        //dao.removeItemFromCart(name);
    }

    public void getTotalOrder(){
        //dao.getTotalOrder();
    }
    public void editItemInCart(String name){
        //dao.editItemInCart(name);
    }
}
