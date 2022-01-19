package com.revature.service;

import com.revature.dao.CartDAO;
import com.revature.dao.CartDAOImp;
import com.revature.exceptions.MyException;
import com.revature.users.Customer;
import com.revature.users.FoodDTO;




public class CartService {
    private CartDAO dao = new CartDAOImp();
    public CartService(){}

    /**
     * Customer can submit their order
     * @param customer contains a customer's cart and order_id
     */
    public void submitOrder(Customer customer) {
        dao.submitOrder(customer);
    }

    /**
     * adds an item to a customer's cart
     * input is validated
     * @param customer is the customer to add the food to
     * @param food is the food and quantity of food the customer is requesting
     * @return
     */
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

    /**
     * A customer can remove an item or some part of an item from their cart
     * input is validated
     * @param customer is the customer to remove cart item
     * @param food to remove and its quantity
     */
    public boolean removeItemFromCart(Customer customer, FoodDTO food){
        try {
            Validator.isValidFoodName(food.foodName);
            Validator.isValidAvailable(food.quantity);
            if(customer.getCart().removeFromCart(food)) {
                //update menu to increase available items when an item was removed from customer cart
                dao.updateAvailable(food);
                return true;
            }
        }catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    //not implemented
    public void editItemInCart(String name){
        //dao.editItemInCart(name);
    }

    /**
     * returns the current total cost of a customers cart
     * @param customer the customer to calculate the cart total
     * @return the current cart total
     */
    public double getTotalOrder(Customer customer) {
       return customer.getCart().getTotal();
    }
}
