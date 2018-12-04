package com.j.dbl.common.dao;

import com.j.dbl.common.domain.SysMenu;
import com.j.dbl.common.supple.MyMapper;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 菜单管理
 * 
 */
public interface SysMenuDao  extends MyMapper<SysMenu> {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();

}
