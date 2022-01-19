package com.revature.users.cart;

import com.revature.users.FoodDTO;


import java.util.ArrayList;
import java.util.Objects;

//keeps track of food items in a customer's cart
//hides that functionality from the customer and the service layer can manipulate the Customer's cart
public class Cart {

    private double total;
    private ArrayList<FoodDTO> cart;

    public Cart(){
        cart = new ArrayList<>();
        total = 0;
    }

    public Cart(double total, ArrayList<FoodDTO> cart) {
        this.total = total;
        this.cart = cart;
    }

    public boolean addToCart(FoodDTO food) {
        for(FoodDTO item: cart) {
            if(item.foodName.equalsIgnoreCase(food.foodName)) {
                item.quantity += food.quantity;
                return true;
            }
        }
        return cart.add(food);
    }
    public boolean removeFromCart(FoodDTO food) {
        for(FoodDTO item: cart) {
            if(item.foodName.equalsIgnoreCase(food.foodName)) {
                if(item.quantity < food.quantity) {
                    return false; //a customer cannot remove 3 food items when they only have 2 of it in their cart
                }
                setTotal(total - (food.quantity * item.price));
                item.quantity = item.quantity - food.quantity;
                if(item.quantity == 0) {
                    cart.remove(food);
                }
                return true;
            }
        }
        return false;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ArrayList<FoodDTO> getCart() {
        return cart;
    }

    public void setCart(ArrayList<FoodDTO> cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart1 = (Cart) o;
        return total == cart1.total && Objects.equals(cart, cart1.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, cart);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "total=" + total +
                ", cart=" + cart.toString() +
                '}';
    }
}
