package net.dreamlu.config;

import lombok.AllArgsConstructor;
import net.dreamlu.boot.captcha.DreamCaptcha;
import net.dreamlu.boot.properties.DreamProperties;
import net.dreamlu.secrity.auth.DreamAccessDeniedHandler;
import net.dreamlu.secrity.auth.DreamAuthHandler;
import net.dreamlu.secrity.auth.DreamAuthenticationProvider;
import net.dreamlu.secrity.auth.DreamWebAuthDetailsSource;
import net.dreamlu.secrity.service.DreamUserDetailsService;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Spring Security 权限控制
 *
 * @author L.cm
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final DreamUserDetailsService userDetailsService;
	private final DreamAuthHandler authHandler;
	private final DreamCaptcha dreamCaptcha;
	private final DreamWebAuthDetailsSource authDetailsSource;
	private final DreamProperties dreamProperties;
	private final CacheManager cacheManager;
	private final DataSource dataSource;

	@Override
	public void configure(WebSecurity web) {
		// @formatter:off
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
			.antMatchers("/favicon.ico")
			.antMatchers("/static/**")
			.antMatchers("/webjars*")
			.antMatchers("/webjars/**")
			.antMatchers("/captcha.jpg")
			.antMatchers("/excel/**")
			.antMatchers("/swagger-resources/**")
			.antMatchers("/upload/**");
		// @formatter:on
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.headers()
			.frameOptions()
			.disable();

		http.authorizeRequests()
			.anyRequest()
			.authenticated();

		http.exceptionHandling()
			.accessDeniedHandler(new DreamAccessDeniedHandler("/accessDenied"));

		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/session")
			.failureHandler(authHandler)
			.successHandler(authHandler)
			.authenticationDetailsSource(authDetailsSource)
			.permitAll();

		http.rememberMe()
			.tokenRepository(rememberMeTokenRepository())
			.userDetailsService(userDetailsService)
			.tokenValiditySeconds(30 * 24 * 60);

		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID", "remember-me");

		http.csrf()
			.ignoringAntMatchers("/druid/**", "/ueditor")
			.csrfTokenRepository(new CookieCsrfTokenRepository());
		// @formatter:on
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
		auth.eraseCredentials(false);
	}

	@Bean
	public DreamAuthenticationProvider authProvider() {
		final DreamAuthenticationProvider authProvider = new DreamAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setDreamCaptcha(dreamCaptcha);
		authProvider.setDreamProperties(dreamProperties);
		authProvider.setCacheManager(cacheManager);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	/**
	 * 记住密码处理
	 */
	@Bean
	public PersistentTokenRepository rememberMeTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(4);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 表达式控制器
	 */
	@Bean
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		webSecurityExpressionHandler.setDefaultRolePrefix(DreamConstants.SECURITY_ROLE_PREFIX);
		return webSecurityExpressionHandler;
	}

	/**
	 * 表达式投票器
	 */
	@Bean
	public WebExpressionVoter webExpressionVoter(DefaultWebSecurityExpressionHandler webSecurityExpressionHandler) {
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler);
		return webExpressionVoter;
	}

}
