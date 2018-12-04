package com.j.dbl.common.annotation;

import java.lang.annotation.*;

/**
 * @author chenjp
 * @create 2018-09-15 9:27
 * @desc 系统日志注解
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
