package com.j.dbl.admin.sys.service;


import com.j.dbl.common.domain.SysConfig;
import com.j.dbl.pojo.JqPageUtil;

/**
 * 系统配置信息
 * 
 */
public interface SysConfigService  {

	JqPageUtil queryPage(SysConfig sysConfig);
	
	/**
	 * 保存配置信息
	 */
	public void saveSysConfig(SysConfig config);
	
	/**
	 * 更新配置信息
	 */
	public void updateSysConfig(SysConfig config);
	
	/**
	 * 根据key，更新value
	 */
	public void updateValueByKey(String key, String value);
	
	/**
	 * 删除配置信息
	 */
	public void deleteBatch(Long[] ids);
	
	/**
	 * 根据key，获取配置的value值
	 * 
	 * @param key           key
	 */
	public String getValue(String key);
	
	/**
	 * 根据key，获取value的Object对象
	 * @param key    key
	 * @param clazz  Object对象
	 */
	public <T> T getConfigObject(String key, Class<T> clazz);

	SysConfig selectById(Long id);
}
