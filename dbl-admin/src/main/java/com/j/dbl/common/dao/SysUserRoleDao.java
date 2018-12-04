package com.j.dbl.common.dao;

import com.j.dbl.common.domain.SysUserRole;
import com.j.dbl.common.supple.MyMapper;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 用户与角色对应关系
 * 
 */
public interface SysUserRoleDao extends MyMapper<SysUserRole> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);

	/**
	 * 批量新增
	 * @param list
	 */
    void insertBatch(List<SysUserRole> list);
}
