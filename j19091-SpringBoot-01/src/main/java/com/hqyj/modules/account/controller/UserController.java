package com.hqyj.modules.account.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.modules.account.entity.User;
import com.hqyj.modules.account.service.UserService;

@Controller
@RequestMapping(value = "/userController")
public class UserController {
	/**
	 * 注入业务接口
	 */
	@Autowired
	private UserService userService;

	/**
	 * 
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
	* <p>Title:addUserByUser </p>  
	* <p>Description: 添加用户，json提交数据 </p>  
	* @param user
	* @return
	 */
	@PostMapping(value="addUserByUser.do",consumes="application/json")
	@ResponseBody
	public User addUserByUser(@RequestBody User user) {
		return userService.addUserByUser(user);
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
	@RequestMapping(value="/deleteUser.do")
	@ResponseBody
	public int deleteUserByUserId(@RequestParam int userId) {
		return userService.deleteUserByUserId(userId);
	}
	

}
