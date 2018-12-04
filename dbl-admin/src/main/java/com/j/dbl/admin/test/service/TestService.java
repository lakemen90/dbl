package com.j.dbl.admin.test.service;

import com.j.dbl.common.domain.Testt;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.PageAjax;

/**
 * @author Jiangbin
 * @create 2018-09-15 9:04
 * @desc
 **/
public interface TestService {

    AjaxResult add(Testt testt);

    AjaxResult delete(Long id);

    AjaxResult updateTest(Testt testt);

    Testt get(Long id);

    Testt getRead(Long id);

    PageAjax<Testt> page(PageAjax<Testt> page);
}
