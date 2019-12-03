package com.hqyj.modules.account.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.common.Result;
import com.hqyj.modules.account.entity.Resource;
import com.hqyj.modules.account.entity.Role;
import com.hqyj.modules.account.entity.User;
import com.hqyj.modules.account.service.UserService;

import ch.qos.logback.core.net.LoginAuthenticator;

@Controller
@RequestMapping(value = "/account")
public class UserController {
	/**
	 * 注入业务接口
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:跳转登录页面 </p>  
	* @return
	 */
	@RequestMapping(value = "/login")
	public String Login(ModelMap modelMap) {
		modelMap.addAttribute("template", "account/login");
		return "indexSimple";
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description: 跳转到主页面</p>  
	* @param modelMap
	* @return
	 */
	@RequestMapping(value = "/dashboard")
	public String toDashboard(ModelMap modelMap) {
		modelMap.addAttribute("template", "account/dashboard");
		return "index";
	}
	/**
	* <p>Title: </p>  
	* <p>Description:跳转到注册界面 </p>  
	* @param modelMap
	* @return
	 */
	@RequestMapping(value = "/register")
	public String toRegister(ModelMap modelMap) {
		modelMap.addAttribute("template", "account/register");
		return "indexSimple";
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description: 实现退出功能，跳转到登录页面</p>  
	* @param modelMap
	* @return
	 */
	@RequestMapping(value="/logout")
	public String loginOut(ModelMap modelMap) {
		modelMap.addAttribute("template", "account/login");
		return "indexSimple";
	}
	
	
	/**
	* <p>Title: </p>  
	* <p>Description:实现登录验证 </p>  
	* @param user
	* @return
	 */
	@PostMapping(value="doLogin",consumes="application/json")
	@ResponseBody
	public Result queryUserByUser(@RequestBody User user) {
		 return userService.queryUserByUser(user); 
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description: 检查用户登录账号</p>  
	* @param user
	* @return
	 */
	@PostMapping(value="/checkUserName",consumes="application/json")
	@ResponseBody
	public Result Result(@RequestBody User user) {
		return userService.checkUserName(user); 
	}
	
	/**
	* <p>Title:queryUserByUserId </p>  
	* <p>Description:通过用户Id查询用户 </p>  
	* @param userId
	* @return
	 */
	@RequestMapping(value="/queryUserByUserId.do")
	@ResponseBody
	public User queryUserByUserId(@RequestParam int userId) {
		return userService.queryUserByUserId(userId);
	}
	
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:查询所有用户 </p>  
	* @return
	 */
	@RequestMapping(value="/users")
	public String queryAllUser(ModelMap modelMap) {
		modelMap.put("users",userService.queryAllUser() );
		modelMap.put("roles", userService.queryAllRole());
		modelMap.put("template","account/users" );
		return "index";
	}
	
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:通过用户id得到用户角色 </p>  
	* @param user
	* @return
	 */
	@RequestMapping(value="/getRolesByUserId")
	@ResponseBody
	public List<Role> getRoleByUserId(@RequestParam int userId) {
		return userService.getRolesByUserId(userId);
		
	}
	
	/**
	 * 
	* <p>Title:addUserByUser </p>  
	* <p>Description: 添加用户，json提交数据 </p>  
	* @param user
	* @return
	 */
	@PostMapping(value="/register",consumes="application/json")
	@ResponseBody
	public Result addUserByUser(@RequestBody User user) {
		return userService.addUserByUser(user);
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description: 实现修改用户角色</p>  
	* @param user
	* @return
	 */
	@PostMapping(value="/updateUserRole",consumes="application/json")
	@ResponseBody
	public Result updateUserRole(@RequestBody User user) {
		return userService.updateUserRole(user);
		
	}

	
	/**
	* <p>Title:updateUserByUser </p>  
	* <p>Description: 修改用户信息，通过json提交数据</p>  
	* @param user
	* @return
	 */
	@PutMapping(value="/updateUser.do",consumes="application/json")
	@ResponseBody
	public User updateUserByUser(@RequestBody User user) {
		return userService.updateUserByUser(user);
	}
	
	/**
	 * 
	* <p>Title:deleteUserByUserId </p>  
	* <p>Description:通过用户id删除用户信息，通过get提交请求 </p>  
	* @param userId
	* @return
	 */
	@RequestMapping(value="/deleteUser/{userId}")
	public String deleteUserByUserId(@PathVariable int userId) {
		//删除用户信息
		 userService.deleteUserByUserId(userId);
		 //删除用户的角色分配情况
		 userService.deleteUserRoleByUserId(userId);
		 return "redirect:/account/users";
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description:跳转到角色界面并查询出所有的角色 </p>  
	* @param modelMap
	* @return
	 */
	@RequestMapping(value = "/roles")
	public String toRoles(ModelMap modelMap) {
		//查询出所有的角色并返回到页面上
		List<Role> roles = userService.queryAllRole();
		modelMap.put("roles", roles);
		modelMap.addAttribute("template", "/account/roles");
		return "index";
	}
	/**
	* <p>Title: </p>  
	* <p>Description: 增加角色</p>  
	* @param role
	* @return
	 */
	@PostMapping(value="/addRole",consumes="application/json")
	@ResponseBody
	public Result addRole(@RequestBody Role role ) {
		return userService.addRoleByRole(role);
	}
	
	
	/**
	* <p>Title: </p>  
	* <p>Description: 修改角色</p>  
	* @param role
	* @return
	 */
	@PostMapping(value="/updateRole",consumes="application/json")
	@ResponseBody
	public Result updateRole(@RequestBody Role role) {
		return userService.updateRole(role);
	}
	
	
	/**
	* <p>Title: </p>  
	* <p>Description:删除角色</p>  
	* @param roleId
	* @return
	 */
	@RequestMapping(value="/deleteRole/{roleId}")
	public String deleteRole(@PathVariable int roleId) {
		//删除角色信息
		  userService.deleteRole(roleId);
		 return "redirect:/account/roles";
	}
	/**
	* <p>Title: </p>  
	* <p>Description: 跳转到资源页面，并加载所有的资源</p>  
	* @param modelMap
	* @return
	 */
	@RequestMapping(value = "/resources")
	public String toResources(ModelMap modelMap) {
		// 查询出所有的角色并返回到页面上
		List<Resource> resources = userService.queryAllResource();
		List<Role> roles = userService.queryAllRole();
		modelMap.put("roles", roles);
		modelMap.put("resources", resources);
		modelMap.addAttribute("template", "/account/resources");
		return "index";
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description:添加资源 </p>  
	* @param resource
	* @return
	 */
	@PostMapping(value="/addResource",consumes="application/json")
	@ResponseBody
	public Result addResource(@RequestBody Resource resource ) {
		return userService.addResource(resource);
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description:通过资源id得到拥有该资源的角色 </p>  
	* @return
	 */
	@RequestMapping(value = "/getRolesByResourceId")
	@ResponseBody
	public List<Role> getRolesByResourceId(@RequestParam int resourceId) {
		return userService.getRolesByResourceId(resourceId);
	}
	
	/**
	* <p>Title: </p>  
	* <p>Description:修改资源信息 </p>  
	* @param resource
	* @return
	 */
	@PostMapping(value="/updateResource",consumes="application/json")
	@ResponseBody
	public Result updateResource(@RequestBody Resource resource ) {
		System.err.println(resource);
		return userService.updateResource(resource);
		
	}
	/**
	 * 
	* <p>Title: </p>  
	* <p>Description:删除用户 </p>  
	* @param resourceId
	* @return
	 */
	@RequestMapping(value = "/deleteResource/{resourceId}")
	public String deleteResource(@PathVariable int resourceId) {
		//删除指定资源
		userService.deleteResourceById(resourceId);
		//删除中间表对应的资源
		userService.deleteRoleByResourceId(resourceId);
		return "redirect:/account/resources";
		
	}
}
