package com.j.dbl.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Jiangbin
 * @create 2018-09-16 22:39
 * @desc
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JqPageUtil implements Serializable {

    private static final long serialVersionUID = -6231828282899750886L;

    private Integer code;

    private String msg;

    @JsonProperty("page")
    private JqPage jqPage;

    public static JqPageUtil getPage(JqPage jqPage){
        return new JqPageUtil(0,"success",jqPage);
    }
}
