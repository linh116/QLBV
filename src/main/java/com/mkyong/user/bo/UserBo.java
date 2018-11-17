package com.mkyong.user.bo;

import com.mkyong.user.model.User;

public interface UserBo {
	
	void save(User user);
	
	void update(User user);
	
	void delete(User user);

	User findByUserCode(String user);

}
