package net.dreamlu.blog.web;

import net.dreamlu.tool.result.Result;
import net.dreamlu.tool.result.Results;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客页面
 *
 * @author L.cm
 */
@Controller
@RequestMapping("blog")
public class BlogController {

	@GetMapping("create")
	public String create() {
		return "blog/create";
	}

	@PostMapping("save")
	@ResponseBody
	public Result<String> save(String content) {
		return Results.success(content);
	}
}
