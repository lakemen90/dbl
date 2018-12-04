package com.j.dbl.admin.sys.controller;

import com.j.dbl.common.domain.SysUser;
import com.j.dbl.common.util.IPUtil;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.ParamData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenjp
 * @create 2018-09-17 1:06
 * @desc Sys Controller公共组件
 */
@Slf4j
public abstract class AbstractSysController {

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    /**
     * 得到request对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    /**
     * 公共异常处理
     **/
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public AjaxResult exceptionHandler(Exception e, HttpServletRequest request) {
        ParamData params = new ParamData();
        StringBuilder sb = new StringBuilder(params.getString("loginIp")).append(request.getRequestURI()).append("请求发生异常:")
                .append(request.getServletPath()).append(":").append(params);
        log.error(sb.toString(), e);
        return new AjaxResult(-1, e.getMessage());
    }

    public void logBefore(String desc) {
        HttpServletRequest request = getRequest();
        StringBuilder sb = new StringBuilder(IPUtil.getIpAdd(request)).append(desc).append(":").append(request.getServletPath());
        log.error(sb.toString());
    }

    /**
     * 获取表单传递上来的数组参数, 如key[0], key[1]
     */
    public List<String> getParameterArray(Map<String, String> map, String key) {
        List<String> result = new ArrayList<String>();
        for (Map.Entry<String, String> m : map.entrySet()) {
            if (m.getKey().startsWith(key + "[")) {
                result.add(m.getValue());
            }
        }
        return result;
    }
}