package com.mkyong.user.bo.impl;

import com.mkyong.user.dao.UserDao;
import com.mkyong.user.model.User;

public class UserBoImpl implements com.mkyong.user.bo.UserBo {
	
	UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void save(com.mkyong.user.model.User user){
		userDao.save(user);
	}
	
	public void update(com.mkyong.user.model.User user){
		userDao.update(user);
	}
	
	public void delete(com.mkyong.user.model.User user){
		userDao.delete(user);
	}
	
	public User findByUserCode(String userId){
		return userDao.findByUserCode(userId);
	}
}
