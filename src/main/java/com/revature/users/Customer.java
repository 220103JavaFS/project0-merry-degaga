package com.revature.users;

import com.revature.users.cart.Cart;

import java.util.Objects;
import java.util.Random;
//mainly used when a customer is putting is adding to their cart/editing/submitting their cart
//orderID is generated and saved to the orders submitted table so that when a manager or employee goes to complete an
// order they can complete the orders listed under a customer's orderID
//rewards points can be implemented if code is refractored
public class Customer extends User{

    private Cart cart = new Cart();
    private int orderID = new Random().nextInt(10000);
    private int rewards;

    public Customer(){}

    public Customer(String firstname, String lastname, String email, String phoneNumber, String dob, String userId, String secret, String rolez) {
        super(firstname, lastname, email, phoneNumber, dob, userId, secret, rolez);
        this.cart = new Cart();
        this.rewards = 0;
    }

    public boolean addToCart(FoodDTO food){
        return cart.addToCart(food);
    }

    public int getOrderID() {
        return orderID;
    }



    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getRewards() {
        return rewards;
    }

    public void setRewards(int rewards) {
        this.rewards = rewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return orderID == customer.orderID && rewards == customer.rewards && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cart, orderID, rewards);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cart=" + cart +
                ", orderID=" + orderID +
                ", rewards=" + rewards +
                '}';
    }
}
