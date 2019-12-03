package com.hqyj.modules.account.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.common.Result;
import com.hqyj.modules.account.dao.UserDao;
import com.hqyj.modules.account.entity.Resource;
import com.hqyj.modules.account.entity.Role;
import com.hqyj.modules.account.entity.User;
import com.hqyj.modules.account.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	/**
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
	public Result addUserByUser(User user) {
		user.setCreateDate(new Date());
		int flag=userDao.addUserByUser(user);
		if (flag<0) {
			return new Result(500, "注册失败");
		}
		return new Result(200, "注册成功");
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
	public void deleteUserByUserId(int userId) {
		 userDao.deleteUserByUserId(userId);
	}


	/**
	* <p>Title: queryUserByUser</p>
	* <p>Description: 使用账号和密码查询用户 </p>
	* @param user
	* @return
	* @see com.hqyj.modules.account.service.UserService#queryUserByUser(com.hqyj.modules.account.entity.User)
	 */
	@Override
	public Result queryUserByUser(User user) {

		User user_db=userDao.queryUserByUser(user);
		if (user_db==null) {
			return new Result(500, "用户名或密码错误");
		}
		return new Result(200, "登录成功");
	}

	/**
	* <p>Title: checkUserName</p>
	* <p>Description:检查用户注册的账号 </p>
	* @param user
	* @return
	* @see com.hqyj.modules.account.service.UserService#checkUserName(com.hqyj.modules.account.entity.User)
	 */
	@Override
	public Result checkUserName(User user) {
		User user_db=userDao.queryUserByUserName(user.getUserName());
		if (user_db!=null) {
			return new Result(500, "该用户名已存在");
		}
		return new Result(200, "用户名正确");
	}

	/**
	 * 
	 * <p>Title: queryAllUser</p>
	 * <p>Description:查询所有用户 </p>
	 * @return
	 * @see com.hqyj.modules.account.service.UserService#queryAllUser()
	 */
	@Override
	public List<User> queryAllUser() {
		return userDao.queryAllUser();
	}


	/**
	* <p>Title: queryAllRole</p>
	* <p>Description: 查询所有的角色 </p>
	* @return
	* @see com.hqyj.modules.account.service.UserService#queryAllRole()
	 */
	@Override
	public List<Role> queryAllRole() {
		return userDao.queryAllRole();
	}


	/**
	 * 
	* <p>Title: getRolesByUserId</p>
	* <p>Description:通过用户id查询用户的角色分配 </p>
	* @param user
	* @return
	* @see com.hqyj.modules.account.service.UserService#getRolesByUserId(com.hqyj.modules.account.entity.User)
	 */
	@Override
	public List<Role> getRolesByUserId(int userId) {
		return userDao.getRolesByUserId(userId);
	}


	/**
	* <p>Title: updateUserRole</p>
	* <p>Description: 修改用户角色 </p>
	* @param user
	* @return
	* @see com.hqyj.modules.account.service.UserService#updateUserRole(com.hqyj.modules.account.entity.User)
	 */
	@Override
	public Result updateUserRole(User user) {
		// 真正修改用户的角色情况之前，先删除原先的角色分配情况
		userDao.deleteRolesByUserId(user.getUserId());
		for (int i = 0; i < user.getRoles().size(); i++) {
			int roleId = user.getRoles().get(i).getRoleId();
			int flag = userDao.updateUserRole(user.getUserId(), roleId);
			if (flag == 0) {
				return new Result(500, "修改失败");
			}
		}
		return new Result(200, "修改成功");
	}

	/**
	 * 
	* <p>Title: deleteUserRoleByUserId</p>
	* <p>Description:删除用户角色分配 </p>
	* @param userId
	* @see com.hqyj.modules.account.service.UserService#deleteUserRoleByUserId(int)
	 */
	@Override
	public void deleteUserRoleByUserId(int userId) {
		userDao.deleteRolesByUserId(userId);
	}


	/**
	* <p>Title: addRoleByRole</p>
	* <p>Description: 添加角色 </p>
	* @param role
	* @return
	* @see com.hqyj.modules.account.service.UserService#addRoleByRole(com.hqyj.modules.account.entity.Role)
	 */
	@Override
	public Result addRoleByRole(Role role) {
		int flag=userDao.addRoleByRole(role);
		if (flag!=1) {
			return new Result(500,"添加失败");
		}
		return new Result(200,"添加成功");
	}


	/**
	* <p>Title: updateRole</p>
	* <p>Description: 修改角色</p>
	* @param role
	* @return
	* @see com.hqyj.modules.account.service.UserService#updateRole(com.hqyj.modules.account.entity.Role)
	 */
	@Override
	public Result updateRole(Role role) {
		int flag = userDao.updateRole(role);
		if (flag!=1) {
			return new Result(500,"修改失败");
		}
		return new Result(200,"修改成功");
	}

	/**
	* <p>Title: deleteRole</p>
	* <p>Description: 删除角色 </p>
	* @param roleId
	* @see com.hqyj.modules.account.service.UserService#deleteRole(int)
	 */
	@Override
	public void deleteRole(int roleId) {
		userDao.deleteRoleByRoleId(roleId);
		
	}


	/**
	 * 
	* <p>Title: queryAllResource</p>
	* <p>Description: 查询所有资源</p>
	* @return
	* @see com.hqyj.modules.account.service.UserService#queryAllResource()
	 */
	@Override
	public List<Resource> queryAllResource() {
		
		return userDao.queryAllResource();
	}


	@Override
	public Result addResource(Resource resource) {
		int flag=userDao.addResource(resource);
		if (flag!=1) {
			return new Result(500,"添加失败");
		}
		return new Result(200,"添加成功");
	}


	/**
	 * 
	* <p>Title: getRolesByResourceId</p>
	* <p>Description: 通过resourceID得到拥有该资源的角色</p>
	* @param resourceId
	* @return
	* @see com.hqyj.modules.account.service.UserService#getRolesByResourceId(int)
	 */
	@Override
	public List<Role> getRolesByResourceId(int resourceId) {
		return userDao.getRolesByResourceId(resourceId);
	}

	/**
	 * 
	* <p>Title: updateResource</p>
	* <p>Description: 提交修改用户数据 </p>
	* @param resource
	* @return
	* @see com.hqyj.modules.account.service.UserService#updateResource(com.hqyj.modules.account.entity.Resource)
	 */
	@Override
	public Result updateResource(Resource resource) {
		//修改资源表中的对应信息
		int flag=userDao.updateResource(resource);
		if (flag!=1) {
			return new Result(500,"资源表修改失败");
		}
		//在中间表中，删除该条资源原先分配的角色信息
		userDao.deleteRoleByResourceId(resource.getResourceId());
		//将新的角色信息插入表中
		for (int i = 0; i < resource.getRoles().size(); i++) {
			int temp=userDao.addRoleAndResource(resource.getRoles().get(i).getRoleId(), resource.getResourceId());
			if (temp!=1) {
				return new Result(500,"中间表插入修改失败");
			}
		}
		return  new Result(200,"修改成功");
	}


	/**
	 * 
	* <p>Title: deleteResourceById</p>
	* <p>Description:删除资源 </p>
	* @param resourceId
	* @see com.hqyj.modules.account.service.UserService#deleteResourceById(int)
	 */
	@Override
	public void deleteResourceById(int resourceId) {
		userDao.deleteResourceById(resourceId);
		
	}


	/**
	* <p>Title: deleteRoleByResourceId</p>
	* <p>Description:删除中间表资源对应的角色分配 </p>
	* @param resourceId
	* @see com.hqyj.modules.account.service.UserService#deleteRoleByResourceId(int)
	 */
	@Override
	public void deleteRoleByResourceId(int resourceId) {
		userDao.deleteRoleByResourceId(resourceId);
		
	}
}
