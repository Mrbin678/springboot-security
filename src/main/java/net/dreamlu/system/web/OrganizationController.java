package net.dreamlu.system.web;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.dreamlu.boot.annotation.SysLog;
import net.dreamlu.common.result.Tree;
import net.dreamlu.system.model.Organization;
import net.dreamlu.system.service.IOrganizationService;
import net.dreamlu.tool.result.Result;
import net.dreamlu.tool.result.Results;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 组织机构 前端控制器
 * </p>
 *
 * @author L.cm
 * @since 2018-02-05
 */
@Controller
@RequestMapping("/organization")
@AllArgsConstructor
@Api(description = "组织管理")
public class OrganizationController {
	private final IOrganizationService organizationService;

	@GetMapping("/manager")
	public String manager() {
		return "system/organization/organizationList";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public Object dataGrid() {
		return organizationService.selectTreeGrid();
	}

	/**
	 * 部门资源树
	 */
	@PostMapping(value = "/tree")
	@ResponseBody
	public List<Tree> tree() {
		return organizationService.selectTree();
	}

	/**
	 * 添加页面
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "system/organization/organizationAdd";
	}

	/**
	 * 添加
	 */
	@PostMapping("/add")
	@PreAuthorize("@sec.hasPermission('organization:add')")
	@ResponseBody
	public Result<Object> add(@Valid Organization organization) {
		return Results.status(organizationService.save(organization));
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@PreAuthorize("@sec.hasPermission('organization:delete')")
	@ResponseBody
	@SysLog("删除组织")
	public Result<Object> delete(Organization organization) {
		return Results.status(organizationService.deleteById(organization));
	}

	/**
	 * 编辑
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		Organization organization = organizationService.getById(id);
		model.addAttribute("organization", organization);
		return "system/organization/organizationEdit";
	}

	/**
	 * 编辑
	 */
	@PostMapping("/edit")
	@PreAuthorize("@sec.hasPermission('organization:edit')")
	@ResponseBody
	public Result<Object> edit(@Valid Organization organization) {
		return Results.status(organizationService.updateById(organization));
	}
}
