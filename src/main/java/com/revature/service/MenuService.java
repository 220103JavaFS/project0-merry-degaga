package com.revature.service;
import com.revature.dao.MenuDAO;
import com.revature.dao.MenuDAOImp;
import com.revature.exceptions.MyException;
import com.revature.users.Inventory;
import com.revature.users.cart.food.Food;

import java.util.ArrayList;

public class MenuService {
    private MenuDAO dao =  new MenuDAOImp();
    public MenuService(){}

    /**
     * Gets the menu for the customer, manager or employee
     * @return An arrayList of food items
     */
    public ArrayList<Food> getMenu() {
        return dao.getMenu();
    }

    /**
     * used when a manager adds a menu item
     * user input is validated using Validator util class
     * @param food is the food item to add to the menu
     * @return true if add was successful
     */
    public boolean addMenuItem(Food food) {
        try{
            Validator.isValidFoodName(food.getFoodName());
            Validator.isValidFoodDescription(food.getDescription());
            Validator.isValidPrice(food.getPrice());
            Validator.isValidIngredients(food.getIngredients());
            Validator.isValidAvailable(food.getAvailable());
            dao.addMenuItem(food);
        } catch(MyException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * used by manager to add an inventory item
     * user inputs are checked by Validator
     * @param item to add to inventory
     */
    public boolean addInventory(Inventory item) {
        try {
            Validator.isValidFoodName(item.ingredientName);
            Validator.isValidAvailable(item.quantity);
            dao.addInventory(item);
            return true;
        }catch(MyException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    //not implemented
    public void removeMenuItem(String name){
        //dao.removeMenuItem(name);
    }
    //not implemented
    public void editMenuItem(String name) {
        //dao.editMenuItem(name);
    }

}
