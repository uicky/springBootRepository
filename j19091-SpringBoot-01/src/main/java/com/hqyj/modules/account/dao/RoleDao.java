package com.hqyj.modules.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hqyj.modules.account.entity.Role;

@Repository
@Mapper
public interface RoleDao {

	@Select("select * from m_role where role_id=#{roleId}")
	Role queryRoleByRoleId(int roleId);

	@Insert("insert into m_role value(null,#{roleName})")
	@Options(keyColumn="role_id",keyProperty="roleId",useGeneratedKeys=true)
	void addRoleByRole(Role role);

	@Update("update m_role set role_name=#{roleName} where role_id=#{roleId}")
	void updateRoleByRoleId(Role role);
	
	

}
