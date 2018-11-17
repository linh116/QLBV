package com.mkyong.user.dao.impl;

import com.mkyong.user.dao.UserDao;
import com.mkyong.user.model.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao{
	
	public void save(User user){
		getHibernateTemplate().save(user);
	}
	
	public void update(User user){
		getHibernateTemplate().update(user);
	}
	
	public void delete(User user){
		getHibernateTemplate().delete(user);
	}
	
	public User findByUserCode(String userCode){
		List list = getHibernateTemplate().find("from User where userCode=?",userCode);
		return (User)list.get(0);
	}

}
