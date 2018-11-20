package net.dreamlu.system.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.dreamlu.common.result.EasyPage;
import net.dreamlu.common.result.PageVO;
import net.dreamlu.system.model.SysDict;
import net.dreamlu.system.service.ISysDictService;
import net.dreamlu.system.vo.SysDictVO;
import net.dreamlu.tool.result.Result;
import net.dreamlu.tool.result.Results;
import net.dreamlu.tool.util.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 字典 前端控制器
 * </p>
 *
 * @author L.cm
 * @since 2018-04-15
 */
@Controller
@RequestMapping("/sysDict")
@AllArgsConstructor
@Api(description = "字典管理")
public class SysDictController {
	private final ISysDictService sysDictService;

	@GetMapping("/manager")
	@PreAuthorize("@sec.hasPermission('sysDict:manager')")
	public String manager() {
		return "system/sysDict/sysDictList";
	}

	@PostMapping("/dataGrid")
	@PreAuthorize("@sec.hasPermission('sysDict:dataGrid')")
	@ResponseBody
	public EasyPage<SysDict> dataGrid(SysDictVO sysDictVO, PageVO pageVO) {
		SysDict sysDict = BeanUtils.copy(sysDictVO, SysDict.class);
		LambdaQueryWrapper<SysDict> ew = new LambdaQueryWrapper<>(sysDict);
		LocalDateTime startTime = sysDictVO.getCreatedateStart();
		LocalDateTime endTime = sysDictVO.getCreatedateEnd();
		ew.ge(null != startTime, SysDict::getCreateTime, startTime);
		ew.le(null != endTime, SysDict::getCreateTime, endTime);
		Page<SysDict> pages = pageVO.toPage();
		sysDictService.page(pages, ew);
		return EasyPage.of(pages);
	}

	/**
	 * 获取所有的字典
	 */
	@GetMapping("/list")
	@PreAuthorize("@sec.isAuthenticated()")
	@ResponseBody
	public List<SysDict> list() {
		return sysDictService.selectAll();
	}

	/**
	 * 添加页面-字典
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "system/sysDict/sysDictAdd";
	}

	/**
	 * 添加页面-字典
	 */
	@PostMapping("/add")
	@PreAuthorize("@sec.hasPermission('sysDict:add')")
	@ResponseBody
	public Result<Object> add(@Valid SysDict sysDict) {
		sysDict.setCreateTime(LocalDateTime.now());
		return Results.status(sysDictService.save(sysDict));
	}

	/**
	 * 编辑-字典
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Integer id) {
		SysDict sysDict = sysDictService.getById(id);
		model.addAttribute("sysDict", sysDict);
		return "system/sysDict/sysDictEdit";
	}

	/**
	 * 编辑-字典
	 */
	@PostMapping("/edit")
	@PreAuthorize("@sec.hasPermission('sysDict:edit')")
	@ResponseBody
	public Result<Object> edit(@Valid SysDict sysDict) {
		return Results.status(sysDictService.updateById(sysDict));
	}
}
