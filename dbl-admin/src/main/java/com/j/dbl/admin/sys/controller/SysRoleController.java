package com.j.dbl.admin.sys.controller;

import com.j.dbl.admin.sys.service.SysRoleMenuService;
import com.j.dbl.admin.sys.service.SysRoleService;
import com.j.dbl.common.annotation.SysLog;
import com.j.dbl.common.domain.SysRole;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.JqPageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-17
 * @desc 角色管理
 *
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractSysController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public JqPageUtil list(SysRole sysRole){
		return sysRoleService.queryPage(sysRole);
	}

	/**
	 * 角色列表
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public AjaxResult select(){
		List<SysRole> list = sysRoleService.selectAll();
		return new AjaxResult(list);
	}
	
	/**
	 * 角色信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public AjaxResult info(@PathVariable("roleId") Long roleId){
		SysRole role = sysRoleService.selectById(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);

		return new AjaxResult(role);
	}
	
	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public AjaxResult save(@RequestBody SysRole role){
		sysRoleService.saveSysRole(role);
		return new AjaxResult();
	}
	
	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public AjaxResult update(@RequestBody SysRole role){
		sysRoleService.updateSysRole(role);
		return new AjaxResult();
	}
	
	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public AjaxResult delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return new AjaxResult();
	}
}
