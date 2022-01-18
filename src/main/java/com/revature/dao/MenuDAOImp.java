package com.revature.dao;

import com.revature.users.cart.food.Food;
import com.revature.users.cart.food.Ingredient;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class MenuDAOImp implements MenuDAO{
    @Override
    public void getMenu() {

    }

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
                        sql = "INSERT INTO menu_inv (food_name, ingredient_name) VALUES (?,?);";
                        statement = conn.prepareStatement(sql);
                        for (Ingredient i : ingredients) {
                            count = 0;
                            statement.setString(++count, food.getFoodName());
                            statement.setString(++count, i.getIngredientName());
                            statement.execute();
                            sql = "INSERT INTO menu_inv (food_name, ingredient_name) VALUES (?,?);";
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

    @Override
    public void removeMenuItem(String name) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editMenuItem(String name) {
        try(Connection conn = ConnectionUtil.getConnection()){

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
