package com.hqyj.modules.account.service;

import java.util.List;

import com.hqyj.common.Result;
import com.hqyj.modules.account.entity.Resource;
import com.hqyj.modules.account.entity.Role;
import com.hqyj.modules.account.entity.User;

public interface UserService {

	Result queryUserByUser(User user);

	User queryUserByUserId(int userId);

	Result addUserByUser(User user);

	User updateUserByUser(User user);

	void deleteUserByUserId(int userId);

	Result checkUserName(User user);

	List<User> queryAllUser();

	List<Role> queryAllRole();

	List<Role> getRolesByUserId(int userId);

	Result updateUserRole(User user);

	void deleteUserRoleByUserId(int userId);

	Result addRoleByRole(Role role);

	Result updateRole(Role role);

	void deleteRole(int roleId);

	List<Resource> queryAllResource();

	Result addResource(Resource resource);

	List<Role> getRolesByResourceId(int resourceId);

	Result updateResource(Resource resource);

	void deleteResourceById(int resourceId);

	void deleteRoleByResourceId(int resourceId);

}
