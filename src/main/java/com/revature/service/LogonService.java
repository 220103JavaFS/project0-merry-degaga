package com.revature.service;
import com.revature.dao.LogonDAOImp;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;


public class LogonService {
    private Cipher cipher;
    private String algorithm = "RSA";
    private LogonDAOImp service =  new LogonDAOImp();
    public LogonService(){}

    public boolean login(String username, String password, boolean isLoggedIn){
        try {
            if (!isLoggedIn) {
                createCipher();
                byte[] ciphertext = cipher.doFinal(password.getBytes(StandardCharsets.US_ASCII));
                //new String(ciphertext)
                return true;
            }
        } catch (Exception e) {
            System.out.println("top");
            System.out.println(e.getMessage());
        }
        return false; //user is already logged in
    }

    public boolean logout(boolean isLoggedIn){
        if(isLoggedIn) {
            return(service.logout());
        }
        return false;
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

}
