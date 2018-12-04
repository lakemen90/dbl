package com.j.dbl.admin.sys.controller;


import com.j.dbl.admin.sys.service.SysUserRoleService;
import com.j.dbl.admin.sys.service.SysUserService;
import com.j.dbl.common.annotation.SysLog;
import com.j.dbl.common.domain.SysUser;
import com.j.dbl.common.shiro.ShiroUtils;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.JqPageUtil;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-17
 * @desc 系统用户
 *
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractSysController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public JqPageUtil list(){
		return sysUserService.queryPage();
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public AjaxResult info(){
	    return new AjaxResult(getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/password")
	public AjaxResult password(String password, String newPassword){

		//原密码
		password = ShiroUtils.sha256(password, getUser().getSalt());
		//新密码
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return new AjaxResult("原密码不正确");
		}
		
		return new AjaxResult();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public AjaxResult info(@PathVariable("userId") Long userId){
		SysUser user = sysUserService.selectById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		return new AjaxResult(user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public AjaxResult save(@RequestBody SysUser user){
		sysUserService.saveSysUser(user);
		return new AjaxResult();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public AjaxResult update(@RequestBody SysUser user){

		sysUserService.updateSysUser(user);
		
		return new AjaxResult();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public AjaxResult delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return new AjaxResult("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return new AjaxResult("当前用户不能删除");
		}

		sysUserService.batchDelete(Arrays.asList(userIds));
		
		return new AjaxResult();
	}
}
