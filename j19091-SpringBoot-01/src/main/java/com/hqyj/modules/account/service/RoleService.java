package com.hqyj.modules.account.service;

import com.hqyj.modules.account.entity.Role;

public interface RoleService {

	Role queryRoleByRoleId(int roleId);

	Role addRoleByRole(Role role);

	Role updateRoleByRoleId(Role role);

}
