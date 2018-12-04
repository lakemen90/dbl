package com.j.dbl.common.dao;

import com.j.dbl.common.domain.SysUser;
import com.j.dbl.common.supple.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 系统用户
 * 
 */
public interface SysUserDao extends MyMapper<SysUser> {

	List<Map<String,Object>> selectByParams();
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	void batchDelete(@Param("idList") List<Long> idList);
}
