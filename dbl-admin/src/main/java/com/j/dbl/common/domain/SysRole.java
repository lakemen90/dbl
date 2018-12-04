package com.j.dbl.common.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 角色
 *
 * @author chenjp
 * @create 2018-09-16 9:00
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="sys_role")
public class SysRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色ID
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;



    @Transient
    private List<Long> menuIdList;


	/**
	 * 创建时间
	 */
	private Timestamp createTime;

}
