package com.j.dbl.common.dao;


import com.j.dbl.common.domain.SysConfig;
import com.j.dbl.common.supple.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 系统配置信息
 * 
 */
public interface SysConfigDao extends MyMapper<SysConfig> {

	/**
	 * 根据key，查询value
	 */
	SysConfig queryByKey(String paramKey);

	/**
	 * 根据key，更新value
	 */
	int updateValueByKey(@Param("paramKey") String paramKey, @Param("paramValue") String paramValue);

	/**
	 * 批量删除
	 * @param idList
	 */
	void batchDelete(@Param("idList") List<Long> idList);
}
