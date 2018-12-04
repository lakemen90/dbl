package com.j.dbl.admin.sys.service;

import com.j.dbl.common.domain.SysDict;
import com.j.dbl.pojo.JqPageUtil;

import java.util.List;

/**
 * 数据字典
 *
 */
public interface SysDictService{

    JqPageUtil queryPage(SysDict sysDict);

    SysDict selectById(Long id);

    void insertSysDict(SysDict dict);

    void updateById(SysDict dict);

    void batchDelete(List<Long> asList);
}

