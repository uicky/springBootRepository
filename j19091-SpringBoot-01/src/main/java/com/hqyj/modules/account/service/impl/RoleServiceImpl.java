package com.hqyj.modules.account.service.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.modules.account.dao.RoleDao;
import com.hqyj.modules.account.entity.Role;
import com.hqyj.modules.account.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	/**
	* <p>Title: queryRoleByRoleId</p>
	* <p>Description: </p>
	* @param roleId
	* @return
	* @see com.hqyj.modules.account.service.RoleService#queryRoleByRoleId(int)
	 */
	@Override
	public Role queryRoleByRoleId(int roleId) {
		return roleDao.queryRoleByRoleId( roleId);
	}

	/**
	* <p>Title: addRoleByRole</p>
	* <p>Description: </p>
	* @param role
	* @return
	* @see com.hqyj.modules.account.service.RoleService#addRoleByRole(com.hqyj.modules.account.entity.Role)
	 */
	@Override
	public Role addRoleByRole(Role role) {
		 roleDao.addRoleByRole(role);
		 return role;
	}

	@Override
	public Role updateRoleByRoleId(Role role) {
		roleDao.updateRoleByRoleId(role);
		return role;
	}


}
