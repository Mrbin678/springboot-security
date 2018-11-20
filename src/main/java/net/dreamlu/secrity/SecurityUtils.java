package net.dreamlu.secrity;

import net.dreamlu.boot.util.WebUtils;
import net.dreamlu.secrity.auth.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 安全工具类
 *
 * @author L.cm
 */
public class SecurityUtils {

	/**
	 * 获取Authentication
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 获取用户
	 */
	public static AuthUser getUser(Authentication authentication) {
		Object principal = authentication.getPrincipal();
		if (principal instanceof AuthUser) {
			return ((AuthUser) principal);
		}
		return null;
	}

	/**
	 * 获取用户
	 */
	public static AuthUser getUser() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return null;
		}
		return getUser(authentication);
	}

	/**
	 * 退出
	 */
	public static void logout() {
		HttpServletRequest request = WebUtils.getRequest();
		new SecurityContextLogoutHandler().logout(request, null, null);
	}
}
