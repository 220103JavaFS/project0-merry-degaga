package com.revature.service;
import com.revature.dao.RegisterDAO;
import com.revature.dao.RegisterDAOImp;
import com.revature.exceptions.MyException;
import com.revature.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterService {
    private static Logger log = LoggerFactory.getLogger(RegisterService.class);
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
            log.info("Validating user input...");
            Validator.isValidName(user.getFirstname() + " " + user.getLastname());
            Validator.isValidEmail(user.getEmail());
            Validator.isValidPhoneNumber(user.getPhoneNumber());
            Validator.isValidDOB(user.getDob());
            Validator.isValidUserId(user.getUserId());
            Validator.isValidSecret(user.getSecret());
            Validator.isValidRole(user.getRolez());
            LogonService service = new LogonService();
            user.setSecret(service.encrypt(user.getSecret()));
            dao.register(user);
            return true;
        }
        catch(MyException e){
            log.info("Validation failed....");
            log.info(e.getMessage());
            //System.out.println(e.getMessage());
        }
        return false;
    }


}
