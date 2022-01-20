package com.revature.dao;

import com.revature.users.Inventory;
import com.revature.users.cart.food.Food;
import com.revature.users.cart.food.Ingredient;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class MenuDAOImp implements MenuDAO{
    @Override
    public ArrayList<Food> getMenu() {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM menu";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<Food> menu = new ArrayList<>();

            while(result.next()) {
                menu.add(new Food(result.getString("food_name"), result.getString("description"),
                        Double.parseDouble(result.getString("price")), new ArrayList<Ingredient>() ,
                        Integer.parseInt(result.getString("available"))));
            }
            return menu;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * a manager can add a menu item.
     * it checks the inventory first, if nothing in inventory, this returns a false
     * if there is something in the inventory, make sure enough ingredients in inventory to make the food
     * if not, return false
     * else update inventory, to remove that number of ingredients a food calls for
     * then in the menu check to see if it exists in the menu, if it does, just update the available field else it
     * entry
     * @param food
     * @return true if add to menu is a success
     */
    @Override
    public boolean addMenuItem(Food food) {
        try(Connection conn = ConnectionUtil.getConnection()){
            //check inventory first
            String sql = "SELECT * FROM inventory";
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery(sql);

            if(result.next()) {
                ArrayList<String> remaining_inv = new ArrayList<>();
                ArrayList<Ingredient> ingredients = food.getIngredients();
                sql = "CALL remaining_inv (CAST(? AS INTEGER), CAST(? AS INTEGER),?);";
                PreparedStatement statement = conn.prepareStatement(sql);
                int count;
                for(Ingredient i: ingredients){
                    count = 0;
                    statement.setString(++count,Integer.toString(food.getAvailable()));
                    statement.setString(++count,Integer.toString(i.getCallsFor()));
                    statement.setString(++count,i.getIngredientName());
                    result = statement.executeQuery();
                    result.next();
                    remaining_inv.add(result.getString("var1"));
                    sql = "CALL remaining_inv (CAST(? AS INTEGER), CAST(? AS INTEGER),?);";
                }

                if(remaining_inv.contains(null)) {
                    return false; //an unable to meet the available amount due to lack of an inventory item
                }
                else {
                    sql = "CALL update_inv (?,CAST(? AS INTEGER) );";
                    statement = conn.prepareStatement(sql);
                    //update the inventory
                    for(int index = 0; index < ingredients.size(); index++) {
                        count = 0;
                        statement.setString(++count, ingredients.get(index).getIngredientName());
                        statement.setString(++count, remaining_inv.get(index));
                        statement.execute();
                        sql = "CALL update_inv (?,CAST(? AS INTEGER) );";
                    }
                    //then update the menu
                    sql = "SELECT * from menu WHERE food_name=?;";
                    statement = conn.prepareStatement(sql);
                    statement.setString(1, food.getFoodName());
                    result = statement.executeQuery();
                    if(result.next()) {
                        //item already in menu, so just update its available field in menu
                        sql = "CALL update_available(?,CAST(? AS INTEGER));";
                        statement = conn.prepareStatement(sql);
                        statement.setString(1, food.getFoodName());
                        statement.setString(2, Integer.toString(food.getAvailable()));
                        statement.execute();
                    }
                    else {

                        sql = "INSERT INTO menu (food_name, description, price, available ) VALUES (?,?,CAST(? AS " +
                                "NUMERIC),CAST (? AS INTEGER));";
                        statement = conn.prepareStatement(sql);
                        count = 0;
                        statement.setString(++count, food.getFoodName());
                        statement.setString(++count, food.getDescription());
                        statement.setString(++count, Double.toString(food.getPrice()));
                        statement.setString(++count, Integer.toString(food.getAvailable()));
                        statement.execute();

                        //then update the join table
                       // sql = "INSERT INTO menu_inv (food_name, ingredient_name) VALUES (?,?);";
                        sql = "INSERT INTO menu_inv (food_name, ingredient_name, calls_for) VALUES (?,?,CAST (? AS " +
                                "INTEGER));";

                        statement = conn.prepareStatement(sql);
                        for (Ingredient i : ingredients) {
                            count = 0;
                            statement.setString(++count, food.getFoodName());
                            statement.setString(++count, i.getIngredientName());
                            statement.setString(++count, Integer.toString(i.getCallsFor()));
                            statement.execute();
                            sql = "INSERT INTO menu_inv (food_name, ingredient_name, calls_for) VALUES (?,?,CAST (? AS " +
                                    "INTEGER));";
                        }
                    }


                    return true;
                }

            }
            return false;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * a manager can remove a menu item
     * @param name
     */
    @Override
    public void removeMenuItem(String name) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "DELETE FROM menu WHERE food_name = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //not implemented
    @Override
    public void editMenuItem(String name) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * a manager can add an item to an inventory
     * @param item
     */
    @Override
    public void addInventory(Inventory item) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM inventory WHERE ingredient_name=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, item.ingredientName);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                //then just update the quantity
                int total = item.quantity + Integer.parseInt(result.getString("quantity"));
                sql = "UPDATE inventory SET quantity=CAST(? AS INTEGER) WHERE ingredient_name=?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, Integer.toString(total));
                statement.setString(2, item.ingredientName);
                statement.execute();
            }
            else {
                //add the inventory item ingredient name and quantity
                sql = "INSERT INTO inventory(ingredient_name, quantity) VALUES (?,CAST(? AS INTEGER));";
                statement = conn.prepareStatement(sql);
                statement.setString(1, item.ingredientName);
                statement.setString(2, Integer.toString(item.quantity));
                statement.execute();
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Inventory> getInventory() {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM inventory";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ArrayList<Inventory> inventory = new ArrayList<>();

            while(result.next()) {
                inventory.add(new Inventory(result.getString("ingredient_name"),
                        Integer.parseInt(result.getString("quantity"))));
            }
            return inventory;

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
