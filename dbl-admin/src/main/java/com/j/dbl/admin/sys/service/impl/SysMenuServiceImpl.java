package com.j.dbl.admin.sys.service.impl;

import com.j.dbl.admin.sys.service.SysMenuService;
import com.j.dbl.admin.sys.service.SysUserService;
import com.j.dbl.common.dao.SysMenuDao;
import com.j.dbl.common.dao.SysRoleMenuDao;
import com.j.dbl.common.domain.SysMenu;
import com.j.dbl.common.domain.SysRoleMenu;
import com.j.dbl.common.enums.CommonEnum;
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
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {
    
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for(SysMenu menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> queryListParentId(Long parentId) {
        return sysMenuDao.queryListParentId(parentId);
    }

    @Override
    public List<SysMenu> queryNotButtonList() {
        return sysMenuDao.queryNotButtonList();
    }

    @Override
    public List<SysMenu> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if(userId == CommonEnum.SUPER_ADMIN){
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    @Override
    public void delete(Long menuId) {
        //删除菜单
        this.delById(menuId);

        //删除菜单与角色关联
        Example example = new Example(SysRoleMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("menuId",menuId);
        sysRoleMenuDao.deleteByExample(example);
    }

    @Override
    public List<SysMenu> selectAll() {
        return this.queryAll();
    }

    @Override
    public SysMenu selectById(Long parentId) {
        return this.queryByID(parentId);
    }

    @Override
    public void insertSysMenu(SysMenu menu) {
        this.insert(menu);
    }

    @Override
    public void updateById(SysMenu menu) {
        this.updateByID(menu);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();

        for(SysMenu entity : menuList){
            //目录
            if(entity.getType() == CommonEnum.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }
}
