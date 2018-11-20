package net.dreamlu.blog.excel;

import lombok.Data;
import net.dreamlu.tool.excel.ExcelField;

/**
 * 导出管理员用户
 */
@Data
public class ExcelUser {
	@ExcelField(value = "ID号")
	private Integer id;
	@ExcelField("登录名")
	private String username;
	@ExcelField("用户名")
	private String name;
	/**
	 * 状态[0:失效,1:正常]
	 */
	@ExcelField(value = "状态", format = "失效:正常", status = true)
	private Integer status;
}
