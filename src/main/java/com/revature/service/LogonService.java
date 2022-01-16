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
            createCipher();
            return checkDB(username, encryptPassword(password));
        } else {
            throw new MyException("Empty values entered in username or password");
        }
    }

    String encryptPassword(String password) {
        try {
            createCipher();
            byte[] ciphertext = cipher.doFinal(password.getBytes(StandardCharsets.US_ASCII));
            return new String(ciphertext);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void createCipher() {
        try {
            KeyPairGenerator key = KeyPairGenerator.getInstance(algorithm);
            key.initialize(571);
            cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE,key.generateKeyPair().getPrivate());
         } catch(Exception e) {
             System.out.println(e.getMessage());
         }
    }

    private boolean checkDB(String username, String ciphertext){
       String ciphertext_db = dao.logon(username);
       //do pw description and check here...
        return true;
    }



}
