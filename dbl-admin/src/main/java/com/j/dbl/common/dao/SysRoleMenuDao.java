package com.j.dbl.common.dao;

import com.j.dbl.common.domain.SysRoleMenu;
import com.j.dbl.common.supple.MyMapper;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 角色与菜单对应关系
 * 
 */
public interface SysRoleMenuDao extends MyMapper<SysRoleMenu> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);

	/**
	 * 批量新增
	 */
    void insertBatch(List<SysRoleMenu> list);
}
