package com.j.dbl.admin.sys.service.impl;

import com.j.dbl.admin.sys.service.SysUserRoleService;
import com.j.dbl.common.dao.SysUserRoleDao;
import com.j.dbl.common.domain.SysUserRole;
import com.j.dbl.common.supple.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 23:56
 * @desc
 **/
@Service
public class SysUserRoleServiceImpl extends AbstractService<SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除用户与角色关系
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        this.sysUserRoleDao.deleteByExample(example);

        if (roleIdList == null || roleIdList.size() == 0) {
            return;
        }

        //保存用户与角色关系
        List<SysUserRole> list = new ArrayList<>(roleIdList.size());
        roleIdList.forEach(r -> list.add(SysUserRole.builder().roleId(r).userId(userId).build()));
        this.sysUserRoleDao.insertBatch(list);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return sysUserRoleDao.queryRoleIdList(userId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return sysUserRoleDao.deleteBatch(roleIds);
    }
}
