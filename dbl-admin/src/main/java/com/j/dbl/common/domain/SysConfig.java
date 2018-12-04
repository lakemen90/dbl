package com.j.dbl.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 系统配置信息
 *
 * @author chenjp
 * @create 2018-09-16 9:00
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sys_config")
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 1909786717779808911L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="param_key")
    private String paramKey;

    @Column(name="param_value")
    private String paramValue;

    private Integer status;

    private String remark;


}
