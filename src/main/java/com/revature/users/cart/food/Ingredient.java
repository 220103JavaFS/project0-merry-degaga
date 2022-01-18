package com.revature.users.cart.food;

import java.util.Objects;
//simple Ingredient class used by the manager to set up how much of one kind of ingredient a food item callsFor
public class Ingredient {
    private String ingredientName;
    private int callsFor;

    public Ingredient() {
    }

    public Ingredient(String ingredientName, int callsFor) {
        this.ingredientName = ingredientName;
        this.callsFor = callsFor;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public int getCallsFor() {
        return callsFor;
    }

    public void setCallsFor(int callsFor) {
        this.callsFor = callsFor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return callsFor == that.callsFor && Objects.equals(ingredientName, that.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientName, callsFor);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientName='" + ingredientName + '\'' +
                ", callsFor=" + callsFor +
                '}';
    }
}
