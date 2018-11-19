package com.mkyong.common;

import com.mkyong.bo.UserBo;
import com.mkyong.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {
    private final static String salt="DGE$5SGr@3VsHYUMas2323E4d57vfBfFSTRU@!DSH(*%FDSdfg13sgfsg";

    //Takes a string, and converts it to md5 hashed string.
    public static String md5Hash(String message) {

        String md5 = "";
        if(null == message)
            return null;

        message = message+salt;//adding a salt to the string before it gets hashed.
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");//Create MessageDigest object for MD5
            digest.update(message.getBytes(), 0, message.length());//Update input string in message digest
            md5 = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static void main(String[] args){
        registUser("Linh","linhnd3", "admin");

        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

        UserBo userBo = (UserBo)appContext.getBean("userBo");
        User user2 = userBo.findByUserName("linhnd3");
        System.out.println(user2);

    }

    public static void registUser(String displayName, String userName, String password){
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

        UserBo userBo = (UserBo)appContext.getBean("userBo");

        //    	* insert *
        User user = new User();
        user.setDisplayName(displayName);
        user.setUsername(userName);
        user.setPassword(md5Hash(password));
        userBo.save(user);


        //    	* select *
        User user2 = userBo.findByUserName("linhnd3");
        System.out.println("inserted User: " + user2);

    }
}
