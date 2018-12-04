package com.j.dbl.common.dao;

import com.j.dbl.common.domain.SysDict;
import com.j.dbl.common.supple.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 数据字典
 *
 */
public interface SysDictDao  extends MyMapper<SysDict> {

    void batchDelete(@Param("idList") List<Long> idList);
}
