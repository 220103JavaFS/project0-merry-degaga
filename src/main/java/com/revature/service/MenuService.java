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

    public ArrayList<Food> getMenu() {
        return dao.getMenu();
    }

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

    public void addInventory(Inventory item) {
        try {
            Validator.isValidFoodName(item.ingredientName);
            Validator.isValidAvailable(item.quantity);
            dao.addInventory(item);
        }catch(MyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeMenuItem(String name){
        //dao.removeMenuItem(name);
    }

    public void editMenuItem(String name) {
        //dao.editMenuItem(name);
    }

}
