package net.dreamlu.secrity.service;

import lombok.AllArgsConstructor;
import net.dreamlu.secrity.SecurityUtils;
import net.dreamlu.secrity.auth.AuthUser;
import net.dreamlu.system.model.Resource;
import net.dreamlu.system.service.IResourceService;
import net.dreamlu.tool.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 权限判断
 *
 * url: https://stackoverflow.com/questions/41434231/use-spring-security-in-thymeleaf-escaped-expressions-in-javascript
 *
 * @author l.cm
 */
@Service("sec")
@AllArgsConstructor
public class SecService {
	private final IResourceService resourceService;

	/**
	 * 提供给页面输出当前用户
	 * @return {AuthUser}
	 */
	public AuthUser currentUser() {
		return SecurityUtils.getUser();
	}

	/**
	 * 已经授权的
	 * @return 是否授权
	 */
	public boolean isAuthenticated() {
		return this.currentUser() != null;
	}

	/**
	 * 判断请求是否有权限
	 *
	 * @param request        HttpServletRequest
	 * @param authentication 认证信息
	 * @return 是否有权限
	 */
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		AuthUser authUser = SecurityUtils.getUser(authentication);
		if (authUser == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		if (authorities.isEmpty()) {
			return false;
		}
		Integer adminId = authUser.getUserId();
		List<Resource> resourceList = resourceService.findAllByAdminId(adminId);
		return resourceList.stream()
			.map(Resource::getUrl)
			.filter(StringUtils::isNotBlank)
			.anyMatch(x -> PatternMatchUtils.simpleMatch(x, request.getRequestURI()));
	}

	/**
	 * 判断按钮是否有xxx:xxx权限
	 * @param permission 权限
	 * @return {boolean}
	 */
	public boolean hasPermission(String permission) {
		if (StringUtils.isBlank(permission)) {
			return false;
		}
		Authentication authentication = SecurityUtils.getAuthentication();
		if (authentication == null) {
			return false;
		}
		AuthUser authUser = SecurityUtils.getUser(authentication);
		if (authUser == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities.stream()
			.map(GrantedAuthority::getAuthority)
			.filter(StringUtils::isNotBlank)
			.anyMatch(x -> PatternMatchUtils.simpleMatch(permission, x));
	}
}
