package net.dreamlu.test;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.boot.cache.http.HttpCacheAble;
import net.dreamlu.boot.template.DreamTemplate;
import net.dreamlu.secrity.auth.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/test")
@Slf4j
@AllArgsConstructor
public class TestController {
	private final DreamTemplate dreamTemplate;

	/**
	 * 内置 laytpl 演示
	 */
	@GetMapping("tpl.html")
	@HttpCacheAble(maxAge = 60 * 2)
	public String manager(Model model) {
		// 直接渲染字符串
		String text = dreamTemplate.render("你好，我是 {{ d }}，I love java！ ", "L.cm");
		log.info(text);
		// 渲染html模板，模板目录默认：classpath:templates/tpl/
		String html  = dreamTemplate.renderTpl("test1.html", "哈哈哈test1");
		log.info(html);
		// thymeleaf 集成演示查看：test/tpl-test.html
		model.addAttribute("date", new Date());
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("_num", 10000.99);
		return "test/tpl-test.html";
	}
}
