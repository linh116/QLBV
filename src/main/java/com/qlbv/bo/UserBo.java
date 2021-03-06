package com.qlbv.bo;

import com.qlbv.dao.UserDao;
import com.qlbv.model.User;

public class UserBo{
	
	UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void save(User user){
		userDao.save(user);
	}
	
	public void update(User user){
		userDao.update(user);
	}
	
	public void delete(User user){
		userDao.delete(user);
	}
	
	public User findByUserName(String userName){
		return userDao.findByUserName(userName);
	}

	public User findByUserNameAndPassword(String userName, String password){
		return userDao.findByUserNameAndPassword(userName, password);
	}

    public User findUserById(Long userId) {
		return userDao.findUserById(userId);
    }
}
