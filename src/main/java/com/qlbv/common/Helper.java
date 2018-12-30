package com.qlbv.common;

import com.qlbv.bo.UserBo;
import com.qlbv.model.User;
import com.sun.net.httpserver.HttpExchange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class Helper {
    private final static String salt="DGE$5SGr@3VsHYUMas2323E4d57vfBfFSTRU@!DSH(*%FDSdfg13sgfsg";
    static Logger _logger = new Logger();
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
        _logger.info("inserted User: " + user2);

    }

    public static void hasLogined(){

    }

    public static String getAction(HttpExchange httpExchange){
        String path = httpExchange.getRequestURI().getPath();

        if (StringUtils.isEmpty(path)){
            return "index";
        }
        String[] pathArr = path.split("/");
        if (pathArr.length > 2){
            return pathArr[2];
        }else{
            return "index";
        }
    }
    public static Long getLong(Map<String, Object> params, String key){
        try{
            return Long.valueOf((String)params.get(key));
        }catch (Exception e){
            return 0L;
        }
    }
    public static String getString(Map<String, Object> params, String key){
        try{
            if (params.containsKey(key)){
                return (String)params.get(key);
            }else{
                return "";
            }
        }catch (Exception e){
            return "";
        }
    }
    public static Integer getInt(Map<String, Object> params, String key){
        try{
            return Integer.valueOf((String)params.get(key));
        }catch (Exception e){
            return 0;
        }
    }
    public static Long getLong(Map<String, Object> params, String key, long defaultValue){
        try{
            return Long.valueOf((String)params.get(key));
        }catch (Exception e){
            return defaultValue;
        }
    }
    public static String getString(Map<String, Object> params, String key, String defaultValue){
        try{
            if (params.containsKey(key)){
                return (String)params.get(key);
            }else{
                return defaultValue;
            }
        }catch (Exception e){
            return defaultValue;
        }
    }
    public static Integer getInt(Map<String, Object> params, String key, int defaultValue){
        try{
            return Integer.valueOf((String)params.get(key));
        }catch (Exception e){
            return defaultValue;
        }
    }

    public static Boolean getBoolean(Map<String, Object> params, String key){
        try{
            String val = (String)params.get(key);
            if (StringUtils.isEmpty(val)){
                return false;
            }else {
                if (val.equalsIgnoreCase("1") || val.equalsIgnoreCase("true")) {
                    return true;
                }else if (val.equalsIgnoreCase("0") || val.equalsIgnoreCase("false")){
                    return false;
                }
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public static Boolean getBoolean(Map<String, Object> params, String key, boolean defaultValue){
        try{
            String val = (String)params.get(key);
            if (StringUtils.isEmpty(val)){
                return false;
            }else {
                if (val.equalsIgnoreCase("1") || val.equalsIgnoreCase("true")) {
                    return true;
                }else if (val.equalsIgnoreCase("0") || val.equalsIgnoreCase("false")){
                    return false;
                }
                return defaultValue;
            }
        }catch (Exception e){
            return defaultValue;
        }
    }

}
