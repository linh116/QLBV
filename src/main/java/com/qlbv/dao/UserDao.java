package com.qlbv.dao;

import com.qlbv.common.Helper;
import com.qlbv.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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

	public static void main(String[] args){
		System.out.println(Helper.md5Hash("123456"));
	}

    public User findByUserNameAndPassword(String userName, String password){
        String passwordHashed = Helper.md5Hash(password);

        try{
			List list = getHibernateTemplate().find("from User where username=? and password=?"
					, new Object[]{userName, passwordHashed});
			if (CollectionUtils.isEmpty(list)){
				return null;
			}
			return (User)list.get(0);
		}catch (Exception e){
        	e.printStackTrace();
		}
		return null;

    }

    public User findUserById(Long userId) {
		try{
			List list = getHibernateTemplate().find("from User where userId = ?"
					, new Object[]{userId});
			if (CollectionUtils.isEmpty(list)){
				return null;
			}
			return (User)list.get(0);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
    }
}
