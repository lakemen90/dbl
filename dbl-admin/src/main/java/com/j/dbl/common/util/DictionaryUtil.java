package com.j.dbl.common.util;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j.dbl.common.Constant;
import com.j.dbl.common.redis.RedisDao;



/**
 * 数据字典工具类
 */
@Component
public class DictionaryUtil {
	
	@Autowired
	private RedisDao redisDao;
	
	private static RedisDao staticRedisDao;
	
	@PostConstruct
	public void init() {
		this.staticRedisDao = redisDao;
	}
	
    public static Map getDataMap() {
        return (Map) staticRedisDao.getObject("_dataMap");
    }

    public static List getDataGroupItemList(String groupName) {
        return DataDictUtil.getDataGroupItemList(DataDictUtil.getDataGroupMap(getDataMap(), groupName));
    }

    public static Map getDataGroupMap(String groupName) {
        return DataDictUtil.getDataGroupMap(getDataMap(), groupName);
    }

    
    public static Map getDataGroupMap(String groupName, String key, String value) {
        return DataDictUtil.getDataGroupMap(DataDictUtil.getDataGroupMap(getDataMap(), groupName), key, value);
    }

    public static String getDataLabelByValue(String groupName, String optionValue) {
        String validConfig = DataDictUtil.getDataItemValueByName(DataDictUtil.getDataGroupMap(getDataMap(), Constant.DATA_VALID_GROUP), Constant.FORCE_ATTR, Constant.CONFIRM_STR);
        return DataDictUtil.getDataItemLabelByValue(DataDictUtil.getDataGroupMap(getDataMap(), groupName), optionValue, validConfig);
    }

    public static String getDataLabelByName(String groupName, String optionName) {
        String validConfig = DataDictUtil.getDataItemValueByName(DataDictUtil.getDataGroupMap(getDataMap(), Constant.DATA_VALID_GROUP), Constant.FORCE_ATTR, Constant.CONFIRM_STR);
        return DataDictUtil.getDataItemLabelByName(DataDictUtil.getDataGroupMap(getDataMap(), groupName), optionName, validConfig);
    }

    public static String getDataValueByName(String groupName, String optionName) {
        String validConfig = DataDictUtil.getDataItemValueByName(DataDictUtil.getDataGroupMap(getDataMap(), Constant.DATA_VALID_GROUP), Constant.FORCE_ATTR, Constant.CONFIRM_STR);
        return DataDictUtil.getDataItemValueByName(DataDictUtil.getDataGroupMap(getDataMap(), groupName), optionName, validConfig);
    }
    
    public static String getDataNameByValue(String groupName, String optionValue) {
        String validConfig = DataDictUtil.getDataItemValueByName(DataDictUtil.getDataGroupMap(getDataMap(), Constant.DATA_VALID_GROUP), Constant.FORCE_ATTR, Constant.CONFIRM_STR);
        return DataDictUtil.getDataItemNameByValue(DataDictUtil.getDataGroupMap(getDataMap(), groupName), optionValue, validConfig);
    }

}
