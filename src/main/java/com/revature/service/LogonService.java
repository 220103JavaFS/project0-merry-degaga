package com.revature.service;
import com.revature.dao.LogonDAO;
import com.revature.dao.LogonDAOImp;
import com.revature.exceptions.MyException;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;


public class LogonService {
    private Cipher cipher;
    private String algorithm = "RSA";
    private LogonDAO dao =  new LogonDAOImp();
    public LogonService(){}

    public boolean login(String username, String password){
        if(username!=null && password != null) {
            try {
                createCipher();
                byte[] ciphertext = cipher.doFinal(password.getBytes(StandardCharsets.US_ASCII));
                return checkDB(username, new String(ciphertext));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new MyException("Empty values entered in username or password");
        }
        return true;
    }


    private Cipher createCipher() {
        try {
            KeyPairGenerator key = KeyPairGenerator.getInstance(algorithm);
            key.initialize(571);
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE,key.generateKeyPair().getPrivate());
            return cipher;
         } catch(Exception e) {
             System.out.println(e.getMessage());
         }
        return null;
    }

    private boolean checkDB(String username, String ciphertext){
        dao.logon(username, ciphertext);
        return true;
    }

    public String getUserRole(String username){
        return dao.getUserRole(username);
    }

}
