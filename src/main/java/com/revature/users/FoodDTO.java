package com.revature.users;

public class FoodDTO {

    public String foodName;
    public int quantity;
    public double price = 0;

    public void setPrice(double price) {
        this.price = price;
    }
}
