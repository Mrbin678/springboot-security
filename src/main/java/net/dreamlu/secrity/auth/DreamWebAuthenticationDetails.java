package net.dreamlu.secrity.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class DreamWebAuthenticationDetails extends WebAuthenticationDetails {
	private static final long serialVersionUID = -5705520861298051410L;
	private final String verificationCode;

	public DreamWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		verificationCode = request.getParameter("code");
	}

	public String getVerificationCode() {
		return verificationCode;
	}
}