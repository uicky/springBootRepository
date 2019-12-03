package com.hqyj.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hqyj.modules.account.entity.Resource;
import com.hqyj.modules.account.entity.Role;
import com.hqyj.modules.account.entity.User;

@Repository
@Mapper
public interface UserDao {

	/** 通过用户id查询用户对象 */
	@Select("select * from m_user where user_id=#{userId}")
	User queryUserByUserId(int userId);

	/** 添加用户 */
	@Insert("insert into m_user values(null,#{userName},#{password},#{createDate})")
	@Options(keyColumn="user_id",keyProperty="userId",useGeneratedKeys=true)
	int  addUserByUser(User user);

	/**修改用户*/
	@Update("update m_user set user_name=#{userName},password=#{password} where user_id=#{userId}")
	void updateUserByUser(User user);

	
	/**删除用户*/
	@Delete("delete from m_user where user_id=#{userId}")
	void deleteUserByUserId(int userId);

	/**通过账号和密码查询用户*/
	@Select("select * from m_user where user_name=#{userName} and password=#{password}")
	User queryUserByUser(User user);

	/**通过账号查询是否存在该用户*/
	@Select("select * from m_user where user_name=#{userName}")
	User queryUserByUserName(String userName);

	/**查询所有用户*/
	@Select("select * from m_user")
	List<User> queryAllUser();

	/**查询所有的角色*/
	@Select("select * from m_role")
	List<Role> queryAllRole();
	
	/**查询中间表，user_role，通过用户id查询用户的角色分配情况*/
	@Select("SELECT\r\n" + 
			"	m_role.role_id,\r\n" + 
			"	m_role.role_name \r\n" + 
			"FROM\r\n" + 
			"	m_role\r\n" + 
			"	INNER JOIN m_user_role ON m_role.role_id = m_user_role.role_id\r\n" + 
			"	INNER JOIN m_user ON m_user_role.user_id = m_user.user_id \r\n" + 
			"WHERE\r\n" + 
			"	m_user.user_id = #{userId}")
	List<Role> getRolesByUserId(int userId);

	/**删除用户原先的角色分配情况*/
	@Delete("delete from m_user_role where user_id=#{userId}")
	int deleteRolesByUserId(int userId);
	
	/**将修改的角色情况重新插入中间表中*/
	@Insert("insert into m_user_role values(null,#{userId},#{roleId})")
	int updateUserRole(@Param(value="userId") int userId,@Param(value="roleId") int roleId);

	/**新增角色*/
	@Insert("insert into m_role values(null,#{roleName})")
	int addRoleByRole(Role role);

	@Update("update m_role set role_name=#{roleName} where role_id=#{roleId}")
	int updateRole(Role role);

	/**删除角色*/
	@Delete("delete from m_role where role_id=#{roleId}")
	void deleteRoleByRoleId(@Param(value="roleId") int roleId);

	/**查询所有资源*/
	@Select("select * from m_resource")
	List<Resource> queryAllResource();

	/**添加资源*/
	@Insert("insert into m_resource values(null,#{resourceUri},#{resourceName},#{permission})")
	int addResource(Resource resource);
	
	
	/**通过resourceID得到拥有该资源的角色*/
	@Select("SELECT\r\n" + 
			"	m_role.role_name,\r\n" + 
			"	m_role.role_id \r\n" + 
			"FROM\r\n" + 
			"	m_role\r\n" + 
			"	INNER JOIN m_role_resource ON m_role.role_id = m_role_resource.role_id\r\n" + 
			"	INNER JOIN m_resource ON m_role_resource.resource_id = m_resource.resource_id \r\n" + 
			"WHERE\r\n" + 
			"	m_resource.resource_id = #{resourceId}")
	List<Role> getRolesByResourceId(@Param(value="resourceId") int resourceId);

	/**修改资源信息*/
	@Update("update m_resource set resource_uri=#{resourceUri},resource_name=#{resourceName},permission=#{permission} where resource_id=#{resourceId}")
	int updateResource(Resource resource);

	/**通过资源id删除中间表中原先已经分配的角色*/
	@Delete("delete from m_role_resource where resource_id=#{resourceId}")
	void deleteRoleByResourceId(@Param(value="resourceId") int resourceId);

	/**将修改的资源对应的角色信息插入中间表*/
	@Insert("insert into m_role_resource values(null,#{roleId},#{resourceId})")
	int addRoleAndResource(@Param(value="roleId") int roleId,@Param(value="resourceId") int resourceId);

	/**删除资源*/
	@Delete("delete from m_resource where resource_id=#{resourceId}")
	void deleteResourceById(@Param(value="resourceId") int resourceId);


	

}
