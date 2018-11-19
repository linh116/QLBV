package com.mkyong.dao;

import com.mkyong.common.Helper;
import com.mkyong.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserDao extends HibernateDaoSupport{
	
	public void save(User user){
		getHibernateTemplate().save(user);
	}
	
	public void update(User user){
		getHibernateTemplate().update(user);
	}
	
	public void delete(User user){
		getHibernateTemplate().delete(user);
	}
	
	public User findByUserName(String userName){
		List list = getHibernateTemplate().find("from User where username=?",userName);
		return (User)list.get(0);
	}

    public User findByUserNameAndPassword(String userName, String password){
        String passwordHashed = Helper.md5Hash(password);

        List list = getHibernateTemplate().find("from User where username=? and password=?"
                , new Object[]{userName, passwordHashed});
        if (CollectionUtils.isEmpty(list)){
        	return null;
		}
        return (User)list.get(0);
    }

}
