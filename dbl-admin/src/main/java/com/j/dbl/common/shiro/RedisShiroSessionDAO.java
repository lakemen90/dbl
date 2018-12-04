package com.j.dbl.common.shiro;


import com.j.dbl.common.redis.RedisDao;
import com.j.dbl.common.util.RedisKeys;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author chenjp
 * @create 2018-09-16 1:27
 * @desc shiro session dao
 */
@Component
public class RedisShiroSessionDAO extends EnterpriseCacheSessionDAO {
//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private RedisDao redisDao;

    //创建session
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        final String key = RedisKeys.getShiroSessionKey(sessionId.toString());
        setShiroSession(key, session);
        return sessionId;
    }

    //获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if(session == null){
            final String key = RedisKeys.getShiroSessionKey(sessionId.toString());
            session = getShiroSession(key);
        }
        return session;
    }

    //更新session
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        final String key = RedisKeys.getShiroSessionKey(session.getId().toString());
        setShiroSession(key, session);
    }

    //删除session
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        final String key = RedisKeys.getShiroSessionKey(session.getId().toString());
        redisDao.delObject(key);
    }

    private Session getShiroSession(String key) {
        return (Session)redisDao.getObject(key);
    }

    private void setShiroSession(String key, Session session){
        //60分钟过期
        redisDao.saveObject(key, session, 60*60);
    }

}