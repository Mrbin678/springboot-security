package net.dreamlu.secrity.auth;

import lombok.AllArgsConstructor;
import net.dreamlu.boot.util.WebUtils;
import net.dreamlu.tool.result.Result;
import net.dreamlu.tool.result.Results;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class DreamAccessDeniedHandler extends AccessDeniedHandlerImpl {
	private final String errorPage;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		HttpMethod httpMethod = HttpMethod.resolve(request.getMethod());
		if (response.isCommitted()) {
			return;
		}
		if (HttpMethod.GET == httpMethod) {
			if (errorPage != null) {
				// 没有权限 403 给返回 正常 200
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			}
		} else {
			// 没有权限 403 给返回 正常 200
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			Result<String> result = Results.failure("没有权限访问");
			WebUtils.renderJson(response, result);
			return;
		}

	}

}
