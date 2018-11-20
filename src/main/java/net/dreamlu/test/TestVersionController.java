package net.dreamlu.test;

import io.swagger.annotations.Api;
import net.dreamlu.boot.annotation.ApiVersion;
import net.dreamlu.boot.annotation.ApiVersionMapping;
import net.dreamlu.tool.result.Result;
import net.dreamlu.tool.result.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiVersionMapping(value = "test", version = "v1-0")
@Api(description = "测试接口")
//注意不加@Api注解则不会进swagger
public class TestVersionController {

	/**
	 * 测试版本共存
	 * 方法上没有版本时，使用类注册上的版本号1-0
	 */
	@GetMapping("test.json")
	public Result<String> test10() {
		return Results.failure("错误");
	}

	/**
	 * 测试版本共存
	 * 优先使用方法上的版本2-0
	 */
	@GetMapping("test.json")
	@ApiVersion("v2-0")
	public Result<String> test20() {
		return Results.failure("错误");
	}

}
