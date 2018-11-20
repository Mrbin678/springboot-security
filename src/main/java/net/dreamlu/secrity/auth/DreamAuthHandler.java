package net.dreamlu.secrity.auth;

import net.dreamlu.boot.util.WebUtils;
import net.dreamlu.system.syslog.SysLogDTO;
import net.dreamlu.system.syslog.SysLogEvent;
import net.dreamlu.system.syslog.SysLogUtils;
import net.dreamlu.tool.result.Results;
import net.dreamlu.tool.util.SpringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class DreamAuthHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// 直接抛出异常给统一异常工具处理
		throw exception;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		WebUtils.renderJson(response, Results.success());
		// 记录登录日志
		SysLogDTO sysLogDTO = SysLogUtils.getSysLogDTO();
		sysLogDTO.setOperation("登录成功");
		sysLogDTO.setClassMethod("net.dreamlu.secrity.auth.DreamAuthHandler.onAuthenticationSuccess();");
		// 发送 spring event 事件
		SpringUtils.publishEvent(new SysLogEvent(sysLogDTO));
	}
}
