package com.j.dbl.admin.sys.service.impl;

import com.j.dbl.admin.sys.service.SysDictService;
import com.j.dbl.common.dao.SysDictDao;
import com.j.dbl.common.domain.SysDict;
import com.j.dbl.common.supple.AbstractService;
import com.j.dbl.pojo.JqPage;
import com.j.dbl.pojo.JqPageUtil;
import com.j.dbl.pojo.ParamData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 23:56
 * @desc
 **/
@Service
public class SysDictServiceImpl extends AbstractService<SysDict> implements SysDictService {

    @Autowired
    private SysDictDao sysDictDao;

    @Override
    public JqPageUtil queryPage(SysDict sysDict) {
        ParamData paramData = new ParamData();
        Example example = new Example(SysDict.class);
        Example.Criteria criteria = example.createCriteria();
        String name = (String) paramData.get("name");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", "%%" + name + "%%");
        }
        JqPage<SysDict> JqPage = this.queryJqPageListByExample(Integer.parseInt(paramData.get("pageNo").toString()),
                Integer.parseInt(paramData.get("pageSize").toString()),
                example);
        JqPageUtil page1 = JqPageUtil.getPage(JqPage);
        return page1;
    }

    @Override
    public SysDict selectById(Long id) {
        return this.queryByID(id);
    }

    @Override
    public void insertSysDict(SysDict dict) {
        this.insert(dict);
    }

    @Override
    public void updateById(SysDict dict) {
        this.updateByID(dict);
    }

    @Override
    public void batchDelete(List<Long> idList) {
        sysDictDao.batchDelete(idList);
    }
}
