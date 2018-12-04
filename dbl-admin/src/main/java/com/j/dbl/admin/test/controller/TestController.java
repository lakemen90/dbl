package com.j.dbl.admin.test.controller;

import com.j.dbl.admin.test.service.TestService;
import com.j.dbl.common.domain.Testt;
import com.j.dbl.common.util.AppUtil;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.PageAjax;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiangbin
 * @create 2018-09-14 15:53
 * @desc
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    //测试分页
    @RequestMapping("/page")
    public PageAjax<Testt> page(PageAjax<Testt> page) {
        return testService.page(page);
    }

    //测试查询单个
    @GetMapping("{id}")
    public Testt get(@PathVariable Long id){
        log.info("TestController...get.....");
        return testService.get(id);
    }

    //测试查询读库
    @GetMapping("/read/{id}")
    public Testt getRead(@PathVariable Long id){
        log.info("TestController...get.....");
        return testService.getRead(id);
    }

    //测试删除
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable Long id){
        log.info("TestController...delete...");
        try {
            return testService.delete(id);
        } catch (Exception e) {
            return AppUtil.returnObj(2, e.getMessage());
        }
    }

    //测试修改
    @PutMapping
    public AjaxResult modify(Testt testt){
        log.info("TestController...modify...");
        try {
            return testService.updateTest(testt);
        } catch (Exception e) {
            return AppUtil.returnObj(2, e.getMessage());
        }
    }

    //测试新增
    @PostMapping
    public AjaxResult add(Testt testt){
        log.info("TestController...add...");
        try {
            return testService.add(testt);
        } catch (Exception e) {
            return AppUtil.returnObj(2, e.getMessage());
        }
    }
}
