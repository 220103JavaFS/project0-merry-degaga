package com.revature.service;

import com.revature.dao.CartDAO;
import com.revature.dao.CartDAOImp;
import com.revature.exceptions.MyException;
import com.revature.users.Customer;
import com.revature.users.FoodDTO;




public class CartService {
    private CartDAO dao = new CartDAOImp();
    public CartService(){}

    public void submitOrder(Customer customer) {
        dao.submitOrder(customer);
    }

    public boolean addItemToCart(Customer customer, FoodDTO food) {
        try {
            Validator.isValidFoodName(food.foodName);
            Validator.isValidAvailable(food.quantity);
            Double price = dao.checkMenuItem(food);
            if (price != null) {
                food.setPrice(price);
                customer.getCart().setTotal(customer.getCart().getTotal() + (food.quantity*price));
                customer.addToCart(food);
                return true;
            }
        } catch (MyException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void removeItemFromCart(Customer customer, FoodDTO food){
        try {
            Validator.isValidFoodName(food.foodName);
            Validator.isValidAvailable(food.quantity);
            customer.getCart().removeFromCart(food);
            //update menu to increase available items when an item was removed from customer cart
            dao.updateAvailable(food);
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editItemInCart(String name){
        //dao.editItemInCart(name);
    }
    public double getTotalOrder(Customer customer) {
       return customer.getCart().getTotal();
    }
}
