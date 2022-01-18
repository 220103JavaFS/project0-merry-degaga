package com.revature.users;

//simple data transfer object used when a customer needs to add, edit, submit their cart
public class FoodDTO {

    public String foodName;
    public int quantity;
    public double price = 0;
    public FoodDTO(){}
    public void setPrice(double price) {
        this.price = price;
    }
}
