package com.j.dbl.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.j.dbl.common.Constant;


/**
 * 数据字典操作工具类
 */
public class DataDictUtil {

    
    /**
     * 
     * 获取字典组map
     * @param dataMap
     * @param groupName
     * @return
     */
    public static final Map getDataGroupMap(Map dataMap, String groupName) {
        Map groupMap = (Map) dataMap.get(groupName);
        if (groupMap == null) {
            throw new RuntimeException("数据字典组【" + groupName + "】未定义！");
        }
        return groupMap;
    }

    /**
     * 
     * 获取数据字典数据组group的option列表
     * @param groupMap
     * @return
     */
    public static final List getDataGroupItemList(Map groupMap) {
        return (List) groupMap.get(Constant.DATA_ITEM_LIST);
    }

    /**
     * 
     * 将数据字典组拼成map
     * @param groupMap
     * @param key
     * @param value
     * @return
     */
    public static final Map getDataGroupMap(Map groupMap, String key, String value) {
        Map temp = new HashMap();
        for (Iterator iterator = getDataGroupItemList(groupMap).iterator(); iterator.hasNext();) {
            Map item = (Map) iterator.next();
            temp.put(item.get(key), item.get(value));
        }
        return temp;
    }

    /**
     * 
     * 根据label属性得到value属性
     * @param groupMap
     * @param optionValue
     * @param forceAttr
     * @return
     */
    public static final String getDataItemLabelByValue(Map groupMap, String optionValue, String forceAttr) {
        for (Iterator iterator = getDataGroupItemList(groupMap).iterator(); iterator.hasNext();) {
            Map item = (Map) iterator.next();
            if (StringUtils.equals((String) item.get(Constant.VALUE), optionValue)) {
                return (String) item.get(Constant.LABEL);
            }
        }
        if (StringUtils.equals(Constant.CONFIRM_STR, forceAttr)) {
            throw new RuntimeException("数据字典组【" + groupMap.get(Constant.NAME) + "】不存在值【" + optionValue + "】！");
        } else {
            return Constant.BLANK_STR;
        }
    }


    /**
     * 
     * 根据name属性得到label属性
     * @param groupMap
     * @param optionName
     * @param forceAttr
     * @return
     */
    public static final String getDataItemLabelByName(Map groupMap, String optionName, String forceAttr) {
        Map itemMap = (Map) groupMap.get(optionName);
        if (itemMap == null) {
            if (StringUtils.equals(Constant.CONFIRM_STR, forceAttr)) {
                throw new RuntimeException("数据字典组【" + groupMap.get(Constant.NAME) + "】不存在name【" + optionName + "】！");
            } else {
                return Constant.BLANK_STR;
            }
        }
        return (String) itemMap.get(Constant.LABEL);
    }

    /**
     * 
     * 根据name属性得到value属性，强制校验属性
     * @param groupMap
     * @param optionName
     * @param forceAttr
     * @return
     */
    public static final String getDataItemValueByName(Map groupMap, String optionName, String forceAttr) {
        Map itemMap = (Map) groupMap.get(optionName);
        if (itemMap == null) {
            if (StringUtils.equals(Constant.CONFIRM_STR, forceAttr)) {
                throw new RuntimeException("数据字典组【" + groupMap.get(Constant.NAME) + "】不存在name【" + optionName + "】！");
            } else {
                return Constant.BLANK_STR;
            }
        }
        return (String) itemMap.get(Constant.VALUE);
    }
    
    /**
     * 
     *  根据value属性得到name属性，强制校验属性
     * @param groupMap
     * @param optionValue
     * @param forceAttr
     * @return
     */
    public static final String getDataItemNameByValue(Map groupMap, String optionValue, String forceAttr) {
        for (Iterator iterator = getDataGroupItemList(groupMap).iterator(); iterator.hasNext();) {
            Map item = (Map) iterator.next();
            if (StringUtils.equals((String) item.get(Constant.VALUE), optionValue)) {
                return (String) item.get(Constant.NAME);
            }
        }
        if (StringUtils.equals(Constant.CONFIRM_STR, forceAttr)) {
            throw new RuntimeException("数据字典组【" + groupMap.get(Constant.NAME) + "】不存在值【" + optionValue + "】！");
        } else {
            return Constant.BLANK_STR;
        }
    }
    
}
