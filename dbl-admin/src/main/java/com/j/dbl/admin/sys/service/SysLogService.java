package com.j.dbl.admin.sys.service;


import com.j.dbl.common.domain.SysLog;
import com.j.dbl.pojo.JqPageUtil;


/**
 * 系统日志
 * 
 */
public interface SysLogService{

    JqPageUtil queryPage(SysLog sysLog);

    void insertSysLog(SysLog sysLog);
}
