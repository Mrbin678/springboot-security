package net.dreamlu.secrity.service;

import net.dreamlu.secrity.auth.AuthUser;

/**
 * 锁定用户
 *
 * @author L.cm
 */
public interface UserLockService {
	/**
	 * 锁定用户
	 * @param authUser AuthUser
	 * @return {boolean}
	 */
	boolean updateLockUser(AuthUser authUser);
}
