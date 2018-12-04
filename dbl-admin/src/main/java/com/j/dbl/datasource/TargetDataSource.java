package com.j.dbl.datasource;

import java.lang.annotation.*;

/**
 * @author Jiangbin
 * @create 2018-09-15 10:48
 * @desc 作用于类、接口或者方法上
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String name();
}