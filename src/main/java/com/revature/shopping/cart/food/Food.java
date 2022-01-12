package com.revature.shopping.cart.food;

import java.util.Objects;

public class Food {
    private String name;
    private String cost;
    private int quantity;

    public Food(String name, String cost, int quantity) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return quantity == food.quantity && Objects.equals(name, food.name) && Objects.equals(cost, food.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, quantity);
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
