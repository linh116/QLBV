package com.qlbv.framework;

import com.qlbv.model.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;


/**
 *
 * @author avm
 */
public class Session {

    private final User user;

    private final String id;

    private final Map<String, Object> attributesMap;

    public Session(User user) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.attributesMap = new ConcurrentHashMap<>();
    }

    public void setAttribute(String name, Object attribute) {
        synchronized(attributesMap) {
            attributesMap.put(name, attribute);
        }
    }

    public Object getAttribute(String name) {
        return attributesMap.get(name);
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    @SuppressWarnings("unchecked")
    public <T> T getOrCreateAttribute(String attributeName, Supplier<? extends T> supplier) {
        T result;
        synchronized (attributesMap) {
            result = (T) attributesMap.get(attributeName);
            if (null == result) {
                result = supplier.get();
                attributesMap.put(attributeName, result);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", user.getUserId(), id);
    }

}