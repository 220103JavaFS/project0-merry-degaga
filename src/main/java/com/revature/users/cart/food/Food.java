package com.revature.users.cart.food;

import java.util.ArrayList;
import java.util.Objects;

public class Food {
    private String foodName;
    private String description;
    private double price;
    private ArrayList<Ingredient> ingredients;
    private int available;

    public Food(){}

    public Food(String foodName, String description, double price, ArrayList<Ingredient> ingredients, int available) {
        this.foodName = foodName;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 && available == food.available && Objects.equals(foodName, food.foodName) && Objects.equals(description, food.description) && Objects.equals(ingredients, food.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodName, description, price, ingredients, available);
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodName='" + foodName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                ", available=" + available +
                '}';
    }
}
