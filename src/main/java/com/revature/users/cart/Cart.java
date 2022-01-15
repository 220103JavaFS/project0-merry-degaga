package com.revature.users.cart;

import com.revature.users.cart.food.Food;

import java.util.ArrayList;
import java.util.Objects;

public class Cart {

    private int total;
    private ArrayList<Food> cart;

    public Cart(){
        cart = new ArrayList<>();
        total = 0;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Food> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Food> cart) {
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
