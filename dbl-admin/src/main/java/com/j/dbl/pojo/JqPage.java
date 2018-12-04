package com.j.dbl.pojo;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiangbin
 * @create 2018-09-16 22:02
 * @desc
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JqPage<T> {

    private Integer currPage;//当前页

    private Integer pageSize;//一页多少条

    private Long totalCount;//总记录数

    private Integer totalPage;//总页数

    private List<T> list;//记录

    /**
     * 包装JqPage对象
     *
     * @param list
     */
    public JqPage(List<T> list) {
        if (list instanceof Page) {
            Page page = (Page) list;
            this.currPage = page.getPageNum(); //当前页
            this.pageSize = page.getPageSize(); //一页多少条
            this.totalPage = page.getPages(); //总页数
            this.list = page;  //记录
            this.totalCount = page.getTotal();  //总记录数


        } else if (list instanceof Collection) {
            this.currPage = 1;
            this.pageSize = list.size();

            this.totalPage = 1;
            this.list = list;
            this.totalCount = new Long(list.size());
        }
    }
}
