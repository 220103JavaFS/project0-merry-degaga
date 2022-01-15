package com.revature.service;
import com.revature.dao.MenuDAO;
import com.revature.dao.MenuDAOImp;
import com.revature.users.cart.food.Food;

public class MenuService {
    private MenuDAO dao =  new MenuDAOImp();
    public MenuService(){}

    public void getMenu() {}

    public Food addMenuItem(Food food) {
        //dao.addMenuItem(food);
        return null;
    }

    public void removeMenuItem(String name){
        //dao.removeMenuItem(name);
    }

    public void editMenuItem(String name) {
        //dao.editMenuItem(name);
    }

}
