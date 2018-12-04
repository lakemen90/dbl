package com.j.dbl.admin.sys.service.impl;

import com.j.dbl.admin.sys.service.SysUserRoleService;
import com.j.dbl.admin.sys.service.SysUserService;
import com.j.dbl.common.dao.SysUserDao;
import com.j.dbl.common.domain.SysUser;
import com.j.dbl.common.shiro.ShiroUtils;
import com.j.dbl.common.supple.AbstractService;
import com.j.dbl.common.util.TimestampUtil;
import com.j.dbl.pojo.JqPage;
import com.j.dbl.pojo.JqPageUtil;
import com.j.dbl.pojo.ParamData;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author chenjp
 * @create 2018-09-15 23:56
 * @desc
 **/
@Service
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public JqPageUtil queryPage() {
        ParamData paramData = new ParamData();
        JqPage<Map<String, Object>> jqPage = this.jqPageQuery("SysUserDao.selectByParams", paramData);
        JqPageUtil page1 = JqPageUtil.getPage(jqPage);
        return page1;
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysUser(SysUser user) {
        user.setCreateTime(TimestampUtil.getNowTime());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        this.insert(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysUser(SysUser user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        }else{
            user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
        }
        sysUserDao.updateByPrimaryKeySelective(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUser userEntity = new SysUser();
        userEntity.setUserId(userId);
        userEntity.setPassword(newPassword);
        return password.equals(newPassword) && sysUserDao.updateByPrimaryKeySelective(userEntity) == 1;
    }

    @Override
    public SysUser selectById(Long userId) {
        return this.queryByID(userId);
    }

    @Override
    public void batchDelete(List<Long> idList) {
        sysUserDao.batchDelete(idList);
    }
}
