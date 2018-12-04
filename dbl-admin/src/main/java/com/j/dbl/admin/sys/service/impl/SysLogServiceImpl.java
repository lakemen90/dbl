package com.j.dbl.admin.sys.service.impl;

import com.j.dbl.admin.sys.service.SysLogService;
import com.j.dbl.common.domain.SysLog;
import com.j.dbl.common.supple.AbstractService;
import com.j.dbl.pojo.JqPage;
import com.j.dbl.pojo.JqPageUtil;
import com.j.dbl.pojo.ParamData;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author chenjp
 * @create 2018-09-15 23:56
 * @desc
 **/
@Service
public class SysLogServiceImpl extends AbstractService<SysLog> implements SysLogService {

    @Override
    public JqPageUtil queryPage(SysLog sysLog) {
        ParamData paramData = new ParamData();
        Example example = new Example(SysLog.class);
        Example.Criteria criteria = example.createCriteria();
        String key = (String) paramData.get("key");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(key)) {
            criteria.andCondition("username like '%%" + key + "%%' or operation like '%%" + key + "%%'");
        }
        JqPage<SysLog> JqPage = this.queryJqPageListByExample(Integer.parseInt(paramData.get("pageNo").toString()),
                Integer.parseInt(paramData.get("pageSize").toString()),
                example);
        JqPageUtil page1 = JqPageUtil.getPage(JqPage);
        return page1;
    }

    @Override
    public void insertSysLog(SysLog sysLog) {
        this.insert(sysLog);
    }
}
