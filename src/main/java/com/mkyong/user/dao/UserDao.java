package com.mkyong.user.dao;


import com.mkyong.user.model.User;

public interface UserDao {
	
	void save(User user);
	
	void update(User user);
	
	void delete(User user);

	User findByUserCode(String userCode);

}
