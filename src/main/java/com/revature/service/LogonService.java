package com.revature.service;
import com.revature.dao.LogonDAO;
import com.revature.dao.LogonDAOImp;
import com.revature.exceptions.MyException;
import com.revature.users.User;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class LogonService {
    private static Cipher cipher;
    private static String algorithm = "AES";
    private LogonDAO dao =  new LogonDAOImp();
    public LogonService(){}

//    public String login(String username, String password){
//        if(username!=null && password != null) {
//            return dao.logon(username, password.hashCode()+"");
//        } else {
//            throw new MyException("Empty values entered in username or password");
//        }
//    }
    /**
    * User logs in method.
    * @param username is the user's inputted userID
    * @param password is the user's inputted password
    * @return user's role else null if user not in database or incorrect credentials provided
     * */
    public String login(String username, String password){
        if(username!=null && password != null) {
            User user = dao.logon(username);
            if(decrypt(user, password)) return user.getRolez();
        } else {
            throw new MyException("Empty values entered in username or password");
        }
         return null;
    }

    /**
     * Encrypts a user's password. This method is called when a user registers for the first time.
     * @param password is the User's inputted password when registering
     * @return is the encrypted password
     */
    public String encrypt(String password) {
        try {
            createCipher();
            byte[] ciphertext = cipher.doFinal(password.getBytes("UTF-8"));
            System.out.println(ciphertext);
            String s = Base64.getEncoder().encodeToString(ciphertext);
            return s;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * initializes the cipher object with a key and algorithm.
     */
    private void createCipher() {
        try {
            SecretKey secretKey = new SecretKeySpec(new byte[16], "AES");
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Decrypts user's password from database. Used when a user is logging in to verify identity.
     * @param user is the user retrieved from the database
     * @param password is the user's inputted password
     * @return true if the inputted password and the saved encrypted passwords match, else false
     */
    private boolean decrypt(User user, String password) {
        try {
            String secrete = user.getSecret();
            SecretKey secretKey = new SecretKeySpec(new byte[16], "AES");
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            String d = new String(cipher.doFinal(Base64.getDecoder().decode(secrete.getBytes("UTF-8"))));
            return password.equals(d);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }



}
