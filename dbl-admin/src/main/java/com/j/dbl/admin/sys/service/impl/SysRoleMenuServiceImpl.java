package com.j.dbl.admin.sys.service.impl;

import com.j.dbl.admin.sys.service.SysRoleMenuService;
import com.j.dbl.common.dao.SysRoleMenuDao;
import com.j.dbl.common.domain.SysRoleMenu;
import com.j.dbl.common.supple.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 23:56
 * @desc
 **/
@Service
public class SysRoleMenuServiceImpl extends AbstractService<SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if(menuIdList.size() == 0){
            return ;
        }

        //保存角色与菜单关系
        List<SysRoleMenu> list = new ArrayList<>(menuIdList.size());
        menuIdList.forEach(m->{
            list.add(SysRoleMenu.builder().menuId(m).roleId(roleId).build());
        });
        this.sysRoleMenuDao.insertBatch(list);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return sysRoleMenuDao.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        if(roleIds.length == 0){
            return -1;
        }
        return sysRoleMenuDao.deleteBatch(roleIds);
    }
}
