package com.j.dbl.common.dao;

import com.j.dbl.common.domain.SysRole;
import com.j.dbl.common.supple.MyMapper;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 角色管理
 * 
 */
public interface SysRoleDao  extends MyMapper<SysRole> {


    /**
     * 批量删除
     */
    void deleteBatch(Long[] roleIds);
}
