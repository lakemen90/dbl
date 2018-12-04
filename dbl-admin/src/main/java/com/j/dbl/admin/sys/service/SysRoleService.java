package com.j.dbl.admin.sys.service;


import com.j.dbl.common.domain.SysRole;
import com.j.dbl.pojo.JqPageUtil;

import java.util.List;


/**
 * 角色
 */
public interface SysRoleService {

    JqPageUtil queryPage(SysRole sysRole);

    void saveSysRole(SysRole role);

    void updateSysRole(SysRole role);

    void deleteBatch(Long[] roleIds);

    List<SysRole> selectAll();

    SysRole selectById(Long roleId);
}
