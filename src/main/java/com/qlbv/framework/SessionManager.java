package com.qlbv.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    static Map<String, Long> mapSessionUser = new HashMap<>();
    static Map<String, Object> sessionAttrManager =  new ConcurrentHashMap<>();
    public static void putSession(String sessionUUID, Long userId){
        mapSessionUser.put(sessionUUID, userId);
    }

    public static Long getSession(String sessionUUID){
        return mapSessionUser.get(sessionUUID);
    }

    public static void removeSession(String sessionUUID){
        mapSessionUser.remove(sessionUUID);
    }

    public static void setAttribute(String attr, Object object){
        sessionAttrManager.put(attr, object);
    }

    public static Object getAttribute(String attr){
        return sessionAttrManager.get(attr);
    }

    public static void removeAttribute(String attr){
        sessionAttrManager.remove(attr);
    }
}
