package com.j.dbl.common.redis;


import com.j.dbl.common.domain.SysConfig;
import com.j.dbl.common.util.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 系统配置Redis
 *
 */
@Component
public class SysConfigRedis {
    @Autowired
    private RedisDao redisDao;

    public void saveOrUpdate(SysConfig config) {
        if(config == null){
            return ;
        }
        String key = RedisKeys.getSysConfigKey(config.getParamKey());
        redisDao.saveObject(key, config);
    }

    public void delete(String configKey) {
        String key = RedisKeys.getSysConfigKey(configKey);
        redisDao.delObject(key);
    }

    public SysConfig get(String configKey){
        String key = RedisKeys.getSysConfigKey(configKey);
        return (SysConfig)redisDao.getObject(key);
    }
}