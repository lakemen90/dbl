package com.j.dbl.admin.sys.controller;

import com.j.dbl.admin.sys.service.SysDictService;
import com.j.dbl.common.domain.SysDict;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.JqPageUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author chenjp
 * @create 2018-09-17 1:22
 * @desc 数据字典
 *
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dict:list")
    public JqPageUtil list(SysDict sysDict){
        return sysDictService.queryPage(sysDict);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:dict:info")
    public AjaxResult info(@PathVariable("id") Long id){
        SysDict dict = sysDictService.selectById(id);
        return new AjaxResult(dict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dict:save")
    public AjaxResult save(@RequestBody SysDict dict){
        sysDictService.insertSysDict(dict);
        return new AjaxResult();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dict:update")
    public AjaxResult update(@RequestBody SysDict dict){
        sysDictService.updateById(dict);
        return new AjaxResult();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dict:delete")
    public AjaxResult delete(@RequestBody Long[] ids){
        sysDictService.batchDelete(Arrays.asList(ids));
        return new AjaxResult();
    }

}
