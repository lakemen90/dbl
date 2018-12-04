package com.j.dbl.admin.sys.service;

import com.j.dbl.common.domain.SysUser;
import com.j.dbl.pojo.JqPageUtil;

import java.util.List;


/**
 * 系统用户
 * 
 */
public interface SysUserService{

	JqPageUtil queryPage();
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 保存用户
	 */
	void saveSysUser(SysUser user);
	
	/**
	 * 修改用户
	 */
	void updateSysUser(SysUser user);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	boolean updatePassword(Long userId, String password, String newPassword);

    SysUser selectById(Long userId);

	void batchDelete(List<Long> idList);
}
