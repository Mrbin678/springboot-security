package net.dreamlu.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Tree tree
 *
 * @author L.cm
 */
@Data
@ApiModel(description = "树")
public class Tree implements java.io.Serializable {
	private static final long serialVersionUID = -3349794575298238272L;

	@ApiModelProperty("ID")
	private Integer id;
	@ApiModelProperty("父级ID")
	private Integer pid;
	@ApiModelProperty("描述")
	private String text;
	@ApiModelProperty(value = "菜单打开状态", allowableValues = "open,closed")
	private String state = "open";
	@ApiModelProperty(value = "是否勾选", allowableValues = "true,false")
	private boolean checked = false;
	@ApiModelProperty("图标")
	private String iconCls;
	@ApiModelProperty(value = "打开模式", allowableValues = "ajax,iframe")
	private String openMode;
	@ApiModelProperty("URL地址")
	private Object attributes;
}
