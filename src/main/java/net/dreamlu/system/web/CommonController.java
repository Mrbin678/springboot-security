package net.dreamlu.system.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.dreamlu.boot.captcha.DreamCaptcha;
import net.dreamlu.boot.support.BaseController;
import net.dreamlu.secrity.auth.AuthUser;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@Api(description = "通用模块")
@AllArgsConstructor
public class CommonController extends BaseController {
	private final DreamCaptcha dreamCaptcha;

	@ApiOperation(value = "主页")
	@GetMapping({"/", "main"})
	public String index() {
		return "system/main";
	}

	@ApiOperation(value = "登录页")
	@GetMapping("login")
	public String loginView(AuthUser user) {
		if (user == null) {
			return "system/login/index";
		}
		return redirect("/main");
	}

	@ApiOperation(value = "没有权限页")
	@GetMapping("accessDenied")
	public String accessDenied() {
		return "system/error/accessDenied";
	}

	@ApiOperation(value = "图形验证码")
	@GetMapping("captcha.jpg")
	public ResponseEntity<Resource> captcha(HttpServletResponse response) {
		return dreamCaptcha.generate(response);
	}

	@ApiOperation(value = "图标页面")
	@GetMapping("icons.html")
	public String icons() {
		return "system/common/icons";
	}

}
