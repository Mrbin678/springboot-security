package net.dreamlu.blog.excel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.dreamlu.boot.support.BaseController;
import net.dreamlu.system.model.Admin;
import net.dreamlu.system.service.IAdminService;
import net.dreamlu.tool.excel.EasyExcel;
import net.dreamlu.tool.util.BeanUtils;
import net.dreamlu.tool.util.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示Excel导入导出
 *
 * @author L.cm
 */
@Controller
@RequestMapping("excel")
public class ExcelController extends BaseController {

	@Autowired
	private IAdminService adminService;

	@GetMapping("index.html")
	public String index() {
		return "blog/excel.html";
	}

	@GetMapping("export")
	public ResponseEntity<ResourceRegion> export() throws IOException {
		String path = PathUtils.getJarPath();
		File file = new File(path + "/data.xlsx");
		EasyExcel fastExcel = new EasyExcel();

		QueryWrapper<Admin> ew = new QueryWrapper<>();
		List<Admin> list = adminService.list(ew);

		List<ExcelUser> excelUserList = new ArrayList<>();
		for (Admin admin : list) {
			ExcelUser user = BeanUtils.copy(admin, ExcelUser.class);
			excelUserList.add(user);
		}
		fastExcel.create(file, excelUserList);
		return download(file, "后台用户.xlsx");
	}

	@PostMapping("upload")
	@ResponseBody
	public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
		EasyExcel fastExcel = new EasyExcel();
		List<ExcelUser> list = fastExcel.parse(file.getInputStream(), ExcelUser.class);
		return list;
	}
}
