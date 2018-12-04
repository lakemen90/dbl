package com.j.dbl.admin.sys.controller;


import com.j.dbl.admin.sys.service.SysConfigService;
import com.j.dbl.common.annotation.SysLog;
import com.j.dbl.common.domain.SysConfig;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.JqPageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenjp
 * @create 2018-09-17 1:22
 * @desc 系统配置信息
 *
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractSysController {
	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public JqPageUtil list(SysConfig sysConfig){
		return sysConfigService.queryPage(sysConfig);
	}



	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public AjaxResult info(@PathVariable("id") Long id){
		SysConfig config = sysConfigService.selectById(id);
        return new AjaxResult(config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public AjaxResult save(@RequestBody SysConfig config){
		sysConfigService.saveSysConfig(config);
		return new AjaxResult();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public AjaxResult update(@RequestBody SysConfig config){
		sysConfigService.updateSysConfig(config);
        return new AjaxResult();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public AjaxResult delete(@RequestBody Long[] ids){
		sysConfigService.deleteBatch(ids);
        return new AjaxResult();
	}

}
