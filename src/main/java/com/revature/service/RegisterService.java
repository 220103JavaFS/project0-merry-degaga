package com.revature.service;
import com.revature.dao.RegisterDAO;
import com.revature.dao.RegisterDAOImp;
import com.revature.exceptions.MyException;
import com.revature.users.User;

public class RegisterService {
    private RegisterDAO dao = new RegisterDAOImp();
    private LogonService service = new LogonService();
    public RegisterService(){}

    /**
     * This runs when a user registers for the first time. Validator utility class is used to ensure inputs are valid
     * @param user is the user's inputted information from the request
     * @return true if user created successfully, else false if Validator finds an input is not valid
     */
    public boolean register(User user){
        try{
            Validator.isValidName(user.getFirstname() + " " + user.getLastname());
            Validator.isValidEmail(user.getEmail());
            Validator.isValidPhoneNumber(user.getPhoneNumber());
            Validator.isValidDOB(user.getDob());
            Validator.isValidUserId(user.getUserId());
            Validator.isValidSecret(user.getSecret());
            Validator.isValidRole(user.getRolez());
            LogonService service = new LogonService();
            //user.setSecret(user.getSecret().hashCode()+"");
            user.setSecret(service.encrypt(user.getSecret()));
            dao.register(user);
            return true;
        }
        catch(MyException e){
            System.out.println(e.getMessage());
        }
        return false;
    }


}
