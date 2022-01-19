package com.revature.users;

import java.util.Objects;

//simple data transfer object used when a customer needs to add, edit, submit their cart
public class FoodDTO {

    public String foodName;
    public int quantity;
    public double price = 0;
    public FoodDTO(){}
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodDTO foodDTO = (FoodDTO) o;
        return Objects.equals(foodName, foodDTO.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, quantity, price);
    }
}
