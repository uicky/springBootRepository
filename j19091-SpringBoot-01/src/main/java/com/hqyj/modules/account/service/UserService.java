package com.hqyj.modules.account.service;

import com.hqyj.modules.account.entity.User;

public interface UserService {

	User queryUserByUserId(int userId);

	User addUserByUser(User user);

	User updateUserByUser(User user);

	int deleteUserByUserId(int userId);

}
