package com.hqyj.modules.account.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.modules.account.entity.Role;
import com.hqyj.modules.account.service.RoleService;

@Controller
@RequestMapping(value = "/roleController")
public class RoleController {
	@Autowired
	private RoleService roleService;

	/**
	* <p>Title: queryRoleByRoleId </p>  
	* <p>Description: </p>  
	* @param roleId
	* @return
	 */
	@RequestMapping(value = "/queryRoleById.do")
	@ResponseBody
	public Role queryRoleByRoleId(@RequestParam int roleId) {
		return roleService.queryRoleByRoleId(roleId);
	}
	
	/**
	* <p>Title: addRoleByRole </p>  
	* <p>Description: </p>  
	* @param role
	* @return
	 */
	@PostMapping(value="/addRole.do",consumes="application/json")
	@ResponseBody
	public Role addRoleByRole(@RequestBody Role role) {
		return roleService.addRoleByRole(role);
	}
	
	/**
	* <p>Title:updateRoleByRoleId </p>  
	* <p>Description: </p>  
	* @param role
	* @return
	 */
	@PutMapping(value="/updateRole.do",consumes="application/json")
	@ResponseBody
	public Role updateRoleByRoleId(@RequestBody Role role) {
		return roleService.updateRoleByRoleId(role);
	}

}
