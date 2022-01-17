package com.revature.users.cart.food;

import java.util.ArrayList;
import java.util.Objects;

public class Food {
    private String food_name;
    private String description;
    private double price;
    private ArrayList<Ingredient> ingredients;

    public Food(){}

    public Food(String food_name, String description, double price, ArrayList<Ingredient> ingredients) {
        this.food_name = food_name;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
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
        return Double.compare(food.price, price) == 0 && Objects.equals(food_name, food.food_name) && Objects.equals(description, food.description) && Objects.equals(ingredients, food.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food_name, description, price, ingredients);
    }

    @Override
    public String toString() {
        return "Food{" +
                "food_name='" + food_name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }
}
