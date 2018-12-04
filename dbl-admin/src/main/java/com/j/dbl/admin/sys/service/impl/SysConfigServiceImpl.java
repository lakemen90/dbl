package com.j.dbl.admin.sys.service.impl;

import com.j.dbl.admin.sys.service.SysConfigService;
import com.j.dbl.common.dao.SysConfigDao;
import com.j.dbl.common.domain.SysConfig;
import com.j.dbl.common.redis.SysConfigRedis;
import com.j.dbl.common.supple.AbstractService;
import com.j.dbl.common.util.JsonConvertUtil;
import com.j.dbl.exception.AppErrorException;
import com.j.dbl.pojo.JqPage;
import com.j.dbl.pojo.JqPageUtil;
import com.j.dbl.pojo.ParamData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;

/**
 * @author chenjp
 * @create 2018-09-15 23:56
 * @desc
 **/
@Service
public class SysConfigServiceImpl extends AbstractService<SysConfig> implements SysConfigService {
    @Autowired
    private SysConfigRedis sysConfigRedis;
    @Autowired
    private SysConfigDao sysConfigDao;

    @Override
    public JqPageUtil queryPage(SysConfig sysConfig) {
        ParamData paramData = new ParamData();
        Example example = new Example(SysConfig.class);
        Example.Criteria criteria = example.createCriteria();
        String paramKey = (String) paramData.get("paramKey");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(paramKey)) {
            criteria.andLike("paramKey", "%%" + paramKey + "%%");
        }
        criteria.andEqualTo("status",1);
        JqPage<SysConfig> JqPage = this.queryJqPageListByExample(Integer.parseInt(paramData.get("pageNo").toString()),
                Integer.parseInt(paramData.get("pageSize").toString()),
                example);
        JqPageUtil page1 = JqPageUtil.getPage(JqPage);
        return page1;
    }

    @Override
    public void saveSysConfig(SysConfig config) {
        config.setStatus(1);
        this.insert(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysConfig(SysConfig config) {
        this.updateByID(config);
        sysConfigRedis.saveOrUpdate(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateValueByKey(String key, String value) {
        sysConfigDao.updateValueByKey(key,value);
        sysConfigRedis.delete(key);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        if(ids.length == 0){
            return;
        }
        for(Long id : ids){
            SysConfig config = this.queryByID(id);
            sysConfigRedis.delete(config.getParamKey());
        }

        this.sysConfigDao.batchDelete(Arrays.asList(ids));
    }

    @Override
    public String getValue(String key) {
        SysConfig config = sysConfigRedis.get(key);
        if(config == null){
            config = this.sysConfigDao.queryByKey(key);
            sysConfigRedis.saveOrUpdate(config);
        }

        return config == null ? null : config.getParamValue();
    }

    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if(StringUtils.isNotBlank(value)){
            return JsonConvertUtil.json2Obj(value,clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new AppErrorException("获取参数失败");
        }
    }

    @Override
    public SysConfig selectById(Long id) {
        return this.queryByID(id);
    }
}
