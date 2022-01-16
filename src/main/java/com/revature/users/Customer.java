package com.revature.users;

import com.revature.users.cart.Cart;

import java.util.Objects;

public class Customer extends User{

    private Cart cart;
    private int rewards;

    //public Customer(){}

    public Customer(String firstname, String lastname, String email, String phoneNumber, String dob, String userId, String secret, String rolez) {
        super(firstname, lastname, email, phoneNumber, dob, userId, secret, rolez);
        this.cart = new Cart();
        this.rewards = 0;
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
        return rewards == customer.rewards && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cart, rewards);
    }

    @Override
    public String toString() {
        return "Customer{" + super.toString() +
                "cart=" + cart +
                ", rewards=" + rewards +
                '}';
    }
}
