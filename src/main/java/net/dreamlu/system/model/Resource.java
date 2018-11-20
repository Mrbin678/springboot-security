package net.dreamlu.system.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import net.dreamlu.system.base.BaseModel;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
@Getter
@Setter
@TableName("t_resource")
public class Resource extends BaseModel {
	private static final long serialVersionUID = 1L;

	/**
	 * 资源名称
	 */
	private String name;
	/**
	 * 资源的权限
	 */
	private String permissions;
	/**
	 * 资源路径
	 */
	private String url;
	/**
	 * 打开方式 ajax,iframe
	 */
	@TableField("open_mode")
	private String openMode;
	/**
	 * 资源介绍
	 */
	private String description;
	/**
	 * 资源图标
	 */
	@TableField("icon_cls")
	private String iconCls;
	/**
	 * 父级资源id
	 */
	private Integer pid;
	/**
	 * 排序
	 */
	private Integer seq;
	/**
	 * 打开状态
	 */
	private Boolean opened;
	/**
	 * 资源类别
	 */
	@TableField("resource_type")
	private Integer resourceType;

}
