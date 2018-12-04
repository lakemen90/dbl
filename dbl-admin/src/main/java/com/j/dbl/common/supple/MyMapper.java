package com.j.dbl.common.supple;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * @author chenjp
 * @create 2018-09-16 1:27
 * @desc 继承自己的MyMapper
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
