package com.j.dbl.admin.sys.controller;

import com.j.dbl.admin.sys.service.SysMenuService;
import com.j.dbl.common.annotation.SysLog;
import com.j.dbl.common.domain.SysMenu;
import com.j.dbl.common.enums.CommonEnum;
import com.j.dbl.exception.AppErrorException;
import com.j.dbl.pojo.AjaxResult;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-17 1:22
 * @desc 系统菜单
 *
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractSysController {
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 导航菜单
	 */
	@RequestMapping("/nav")
	public AjaxResult nav(){
		List<SysMenu> menuList = sysMenuService.getUserMenuList(getUserId());
		return new AjaxResult(menuList);
	}
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public List<SysMenu> list(){
		List<SysMenu> menuList = sysMenuService.selectAll();
		for(SysMenu sysMenuEntity : menuList){
			SysMenu parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}

		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public AjaxResult select(){
		//查询列表数据
		List<SysMenu> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenu root = new SysMenu();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		return new AjaxResult(menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public AjaxResult info(@PathVariable("menuId") Long menuId){
		SysMenu menu = sysMenuService.selectById(menuId);
		return new AjaxResult(menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public AjaxResult save(@RequestBody SysMenu menu){
		//数据校验
		verifyForm(menu);

		sysMenuService.insertSysMenu(menu);
		
		return new AjaxResult();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public AjaxResult update(@RequestBody SysMenu menu){
		//数据校验
		verifyForm(menu);
				
		sysMenuService.updateById(menu);

		return new AjaxResult();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public AjaxResult delete(long menuId){
		if(menuId <= 31){
			return new AjaxResult("系统菜单，不能删除");
		}

		//判断是否有子菜单或按钮
		List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return new AjaxResult("请先删除子菜单或按钮");
		}

		sysMenuService.delete(menuId);

		return new AjaxResult();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenu menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new AppErrorException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new AppErrorException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == CommonEnum.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new AppErrorException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = CommonEnum.MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenu parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == CommonEnum.MenuType.CATALOG.getValue() ||
				menu.getType() == CommonEnum.MenuType.MENU.getValue()){
			if(parentType != CommonEnum.MenuType.CATALOG.getValue()){
				throw new AppErrorException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == CommonEnum.MenuType.BUTTON.getValue()){
			if(parentType != CommonEnum.MenuType.MENU.getValue()){
				throw new AppErrorException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
