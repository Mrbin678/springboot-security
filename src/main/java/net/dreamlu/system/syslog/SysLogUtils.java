package net.dreamlu.system.syslog;

import net.dreamlu.boot.util.WebUtils;
import net.dreamlu.config.DreamConstants;
import net.dreamlu.secrity.SecurityUtils;
import net.dreamlu.secrity.auth.AuthUser;
import net.dreamlu.tool.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统日志工具类
 *
 * @author L.cm
 */
public class SysLogUtils {

	public static SysLogDTO getSysLogDTO() {
		SysLogDTO sysLogDTO = new SysLogDTO();
		HttpServletRequest request = WebUtils.getRequest();
		StringBuilder params = new StringBuilder();
		request.getParameterMap().forEach((key, values) -> {
			params.append(key).append("＝");
			if (key.equalsIgnoreCase("password")) {
				params.append("******");
			} else {
				params.append(StringUtils.join(values));
			}
			params.append("＆");
		});
		sysLogDTO.setContent(params.toString());
		sysLogDTO.setClientIp(WebUtils.getIP());
		AuthUser authUser = SecurityUtils.getUser();
		if (authUser != null) {
			sysLogDTO.setUsername(authUser.getUsername());
			List<String> roles = authUser.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.filter(x -> x.startsWith(DreamConstants.SECURITY_ROLE_PREFIX))
				.map(x -> x.replace(DreamConstants.SECURITY_ROLE_PREFIX, ""))
				.collect(Collectors.toList());
			sysLogDTO.setRoleName(StringUtils.join(roles));
		}
		return sysLogDTO;
	}

}
