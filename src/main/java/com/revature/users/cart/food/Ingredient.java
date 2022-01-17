package com.revature.users.cart.food;

import java.util.Objects;

public class Ingredient {
    private String ingredient_name;
    private int calls_for;

    public Ingredient() {
    }

    public Ingredient(String ingredient_name, int calls_for) {
        this.ingredient_name = ingredient_name;
        this.calls_for = calls_for;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getCalls_for() {
        return calls_for;
    }

    public void setCalls_for(int calls_for) {
        this.calls_for = calls_for;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return calls_for == that.calls_for && Objects.equals(ingredient_name, that.ingredient_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient_name, calls_for);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient_name='" + ingredient_name + '\'' +
                ", calls_for=" + calls_for +
                '}';
    }
}
