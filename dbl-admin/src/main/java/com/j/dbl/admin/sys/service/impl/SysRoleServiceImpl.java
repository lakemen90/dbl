package com.j.dbl.admin.sys.service.impl;

import com.j.dbl.admin.sys.service.SysRoleMenuService;
import com.j.dbl.admin.sys.service.SysRoleService;
import com.j.dbl.admin.sys.service.SysUserRoleService;
import com.j.dbl.common.dao.SysRoleDao;
import com.j.dbl.common.domain.SysRole;
import com.j.dbl.common.supple.AbstractService;
import com.j.dbl.common.util.TimestampUtil;
import com.j.dbl.pojo.JqPage;
import com.j.dbl.pojo.JqPageUtil;
import com.j.dbl.pojo.ParamData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenjp
 * @create 2018-09-15 23:56
 * @desc
 **/
@Service
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public JqPageUtil queryPage(SysRole sysRole) {
        ParamData paramData = new ParamData();
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        String roleName = (String) paramData.get("roleName");
        if (StringUtils.isNotEmpty(roleName)) {
            criteria.andLike("roleName", "%%" + roleName + "%%");
        }
        JqPage<SysRole> sysRoleJqPage = this.queryJqPageListByExample(Integer.parseInt(paramData.get("pageNo").toString()),
                Integer.parseInt(paramData.get("pageSize").toString()),
                example);
        JqPageUtil page1 = JqPageUtil.getPage(sysRoleJqPage);
        return page1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSysRole(SysRole role) {
        role.setCreateTime(TimestampUtil.getNowTime());
        this.insert(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    public void updateSysRole(SysRole role) {
        this.sysRoleDao.updateByPrimaryKeySelective(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        if (roleIds.length == 0) {
            return;
        }

        //删除角色
        this.sysRoleDao.deleteBatch(roleIds);

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }

    @Override
    public List<SysRole> selectAll() {
        return this.queryAll();
    }

    @Override
    public SysRole selectById(Long roleId) {
        return this.queryByID(roleId);
    }
}
