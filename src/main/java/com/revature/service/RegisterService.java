package com.revature.service;
import com.revature.dao.RegisterDAO;
import com.revature.dao.RegisterDAOImp;
import com.revature.exceptions.MyException;
import com.revature.users.User;

public class RegisterService {
    private RegisterDAO dao = new RegisterDAOImp();
    public RegisterService(){}

    public boolean register(User user){
        //if role is cusotmer make customer object...
        try{
            Validator.isValidName(user.getFirstname() + " " + user.getLastname());
            Validator.isValidEmail(user.getEmail());
            Validator.isValidPhoneNumber(user.getPhoneNumber());
            Validator.isValidDOB(user.getDob());
            Validator.isValidUserId(user.getUserId());
            Validator.isValidSecret(user.getSecret());
            Validator.isValidRole(user.getRolez());
            LogonService service = new LogonService();
            user.setSecret(service.encryptPassword(user.getSecret()));
            dao.register(user);
            return true;
        }
        catch(MyException e){
            System.out.println(e.getMessage());
        }
        return false;
    }


}
