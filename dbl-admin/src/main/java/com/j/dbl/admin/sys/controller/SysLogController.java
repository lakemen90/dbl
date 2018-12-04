package com.j.dbl.admin.sys.controller;

import com.j.dbl.admin.sys.service.SysLogService;
import com.j.dbl.common.domain.SysLog;
import com.j.dbl.pojo.JqPageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenjp
 * @create 2018-09-17 1:22
 * @desc 系统日志
 *
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public JqPageUtil list(SysLog sysLog){
		return sysLogService.queryPage(sysLog);
	}
	
}
