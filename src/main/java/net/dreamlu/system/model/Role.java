package net.dreamlu.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import net.dreamlu.system.base.BaseModel;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
@Getter
@Setter
@TableName("t_role")
public class Role extends BaseModel {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色名
	 */
	private String name;
	/**
	 * 排序号
	 */
	private Integer seq;
	/**
	 * 简介
	 */
	private String description;
	/**
	 * 图标
	 */
	@TableField("icon_cls")
	private String iconCls;

}
