package com.j.dbl.admin.test.service.impl;

import com.j.dbl.admin.test.service.TestService;
import com.j.dbl.common.domain.Testt;
import com.j.dbl.common.redis.RedisDao;
import com.j.dbl.common.supple.AbstractService;
import com.j.dbl.datasource.TargetDataSource;
import com.j.dbl.exception.AppErrorException;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.PageAjax;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jiangbin
 * @create 2018-09-15 9:06
 * @desc
 **/
@Service
@Slf4j
public class TestServiceImpl extends AbstractService<Testt> implements TestService {

    @Autowired
    private RedisDao redisDao;

    @Override
    public AjaxResult add(Testt testt) {
        this.insertSelective(testt);
        //测试redis缓存
        redisDao.saveObject("test",testt);
        log.info(((Testt)redisDao.getObject("test")).toString());
        return new AjaxResult(0, "增加成功");
    }

    @Override
    public AjaxResult delete(Long id) {
        int updRow = this.delById(id);
        if(updRow != 1){
            throw new AppErrorException("删除失败");
        }
        return new AjaxResult(0, "删除成功");
    }

    @Override
    public AjaxResult updateTest(Testt testt) {
        this.updateByID(testt);
        return new AjaxResult(0, "修改成功");
    }

    @Override
    public Testt get(Long id) {
        Testt testt = this.queryByID(id);
        return testt;
    }

    @Override
    @TargetDataSource(name="read")
    public Testt getRead(Long id) {
        Testt testt = this.queryByID(id);
        return testt;
    }

    @Override
    public PageAjax<Testt> page(PageAjax<Testt> page) {
        return this.queryPage(page,null);
    }
}
