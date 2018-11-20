package net.dreamlu.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import net.dreamlu.system.base.BaseModel;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author L.cm
 * @since 2018-02-05
 */
@Getter
@Setter
@TableName("t_organization")
public class Organization extends BaseModel {
	private static final long serialVersionUID = 1L;

	/**
	 * 组织名
	 */
	private String name;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 图标
	 */
	@TableField("icon_cls")
	private String iconCls;
	/**
	 * 父级主键
	 */
	private Integer pid;
	/**
	 * 排序
	 */
	private Integer seq;

}
