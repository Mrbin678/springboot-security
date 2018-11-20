package net.dreamlu.blog.web;

import net.dreamlu.tool.ueditor.UeditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UeditorController {
	@Autowired
	private UeditorService ueditorService;

	/**
	 * ueditor编辑器
	 */
	@RequestMapping("ueditor")
	@ResponseBody
	public ResponseEntity<String> ueditor(HttpServletRequest request) {
		return ueditorService.upload(request);
	}
}
