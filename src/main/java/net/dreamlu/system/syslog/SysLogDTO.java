package net.dreamlu.system.syslog;

import lombok.Getter;
import lombok.Setter;

/**
 * SysLog数据承载
 */
@Getter
@Setter
public class SysLogDTO {
	/**
	 * 登陆名
	 */
	private String username;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 操作
	 */
	private String operation;
	/**
	 * 类-方法
	 */
	private String classMethod;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 客户端ip
	 */
	private String clientIp;
}
