package com.j.dbl.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Jiangbin
 * @create 2018-09-15 10:48
 * @desc  动态数据源
 * AbstractRoutingDataSource(每执行一次数据库，动态获取DataSource)
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}