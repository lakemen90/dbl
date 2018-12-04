package com.j.dbl.admin.sys.service;


import com.j.dbl.common.domain.SysMenu;

import java.util.List;


/**
 * 菜单管理
 * 
 */
public interface SysMenuService{

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenu> getUserMenuList(Long userId);

	/**
	 * 删除
	 */
	void delete(Long menuId);

	List<SysMenu> selectAll();

	SysMenu selectById(Long parentId);

	void insertSysMenu(SysMenu menu);

	void updateById(SysMenu menu);
}
