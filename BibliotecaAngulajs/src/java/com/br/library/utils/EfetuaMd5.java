package com.br.library.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EfetuaMd5 {   
   
    public String  hashMD5(String password){
        
        MessageDigest m;
        try 
        {
        password = password.trim();
        m = MessageDigest.getInstance("MD5"); 
        m.update(password.getBytes(),0,password.length()); 
        BigInteger i = new BigInteger(1, m.digest()); 
        //Formatando o resultado em uma cadeia de 32 caracteres, completando com 0 caso falte 
        password = String.format("%1$032x", i); 
          
        } 
        catch (NoSuchAlgorithmException e) 
        { 
        e.printStackTrace(); 
        } 
        return password;
        
    }
    
    
    
    
}
