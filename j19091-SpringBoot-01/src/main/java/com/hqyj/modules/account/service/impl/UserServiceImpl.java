package com.hqyj.modules.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.modules.account.dao.UserDao;
import com.hqyj.modules.account.entity.User;
import com.hqyj.modules.account.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	/**
	 * 
	* <p>Title: queryUserByUserId</p>
	* <p>Description: 通过用户id查询用户对象</p>
	* @param userId
	* @return
	* @see com.hqyj.modules.account.service.UserService#queryUserByUserId(int)
	 */
	@Override
	public User queryUserByUserId(int userId) {
		
		return userDao.queryUserByUserId(userId);
	}

	
	/**
	* <p>Title: addUserByUser</p>
	* <p>Description: </p>
	* @param user
	* @return
	* @see com.hqyj.modules.account.service.UserService#addUserByUser(com.hqyj.modules.account.entity.User)
	 */
	@Override
	public User addUserByUser(User user) {
		userDao.addUserByUser(user);		
		return user;
	}


	/**
	* <p>Title: updateUserByUser</p>
	* <p>Description: </p>
	* @param user
	* @return
	* @see com.hqyj.modules.account.service.UserService#updateUserByUser(com.hqyj.modules.account.entity.User)
	 */
	@Override
	public User updateUserByUser(User user) {
		userDao.updateUserByUser(user);
		return user;
	}


	/**
	 * 
	* <p>Title: deleteUserByUserId</p>
	* <p>Description: </p>
	* @param userId
	* @return
	* @see com.hqyj.modules.account.service.UserService#deleteUserByUserId(int)
	 */
	@Override
	public int deleteUserByUserId(int userId) {
		return userDao.deleteUserByUserId(userId);
	}

}
