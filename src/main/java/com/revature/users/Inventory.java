package com.revature.users;

//simple class that is used by a manager to add an item to an inventory
public class Inventory {
    public String ingredientName;
    public int quantity;

    public Inventory() {
    }

    public Inventory(String ingredientName, int quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }
}
