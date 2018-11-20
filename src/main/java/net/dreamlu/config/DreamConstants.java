package net.dreamlu.config;

/**
 * 系统常量
 *
 * @author L.cm
 */
public interface DreamConstants {

	/**
	 * 角色前缀
	 */
	String SECURITY_ROLE_PREFIX = "ROLE_";

	/**
	 * 状态[0:失效,1:正常]
	 */
	int DB_STATUS_FAIL = 0;
	int DB_STATUS_NORMAL = 1;

	/**
	 * 用户锁定状态
	 */
	int DB_ADMIN_NON_LOCKED = 0;
	int DB_ADMIN_LOCKED = 1;

}
