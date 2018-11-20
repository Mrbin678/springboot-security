package net.dreamlu.system.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.dreamlu.boot.annotation.SysLog;
import net.dreamlu.common.result.EasyPage;
import net.dreamlu.common.result.PageVO;
import net.dreamlu.common.result.Tree;
import net.dreamlu.system.model.Role;
import net.dreamlu.system.service.IRoleService;
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
 * 角色 前端控制器
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
@Controller
@RequestMapping("/role")
@AllArgsConstructor
@Api(description = "角色管理")
public class RoleController {
	private final IRoleService roleService;

	@GetMapping("/manager")
	public String manager() {
		return "system/role/roleList";
	}

	@PostMapping("/dataGrid")
	@ResponseBody
	public EasyPage<Role> dataGrid(Role role, PageVO pageVO) {
		QueryWrapper<Role> ew = new QueryWrapper<>(role);
		Page<Role> pages = pageVO.toPage();
		roleService.page(pages, ew);
		return EasyPage.of(pages);
	}

	/**
	 * 权限树
	 */
	@PostMapping("/tree")
	@ResponseBody
	public List<Tree> tree() {
		return roleService.selectTree();
	}

	/**
	 * 添加页面
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "system/role/roleAdd";
	}

	/**
	 * 添加
	 */
	@PostMapping("/add")
	@PreAuthorize("@sec.hasPermission('role:delete')")
	@ResponseBody
	@SysLog("添加角色")
	public Result<Object> add(@Valid Role role) {
		return Results.status(roleService.save(role));
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@PreAuthorize("@sec.hasPermission('role:delete')")
	@ResponseBody
	@SysLog("删除角色")
	public Result<Object> delete(Role role) {
		return Results.status(roleService.deleteById(role));
	}

	/**
	 * 编辑
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		Role role = roleService.getById(id);
		model.addAttribute("role", role);
		return "system/role/roleEdit";
	}

	/**
	 * 编辑
	 */
	@PostMapping("/edit")
	@PreAuthorize("@sec.hasPermission('role:edit')")
	@ResponseBody
	@SysLog("编辑角色")
	public Result<Object> edit(@Valid Role role) {
		return Results.status(roleService.updateById(role));
	}

	/**
	 * 授权页面
	 */
	@GetMapping("/grantPage")
	public String grantPage(Model model, Long id) {
		model.addAttribute("id", id);
		return "system/role/roleGrant";
	}

	/**
	 * 授权页面页面根据角色查询资源
	 */
	@RequestMapping("/findResourceIdListByRoleId")
	@ResponseBody
	public Result<Object> findResourceByRoleId(Integer id) {
		List<Integer> resources = roleService.selectResourceIdListByRoleId(id);
		return Results.success(resources);
	}

	/**
	 * 授权
	 */
	@RequestMapping("/grant")
	@PreAuthorize("@sec.hasPermission('role:grant')")
	@ResponseBody
	@SysLog("角色授权")
	public Result<Object> grant(Integer id, String resourceIds) {
		roleService.updateRoleResource(id, resourceIds);
		return Results.success();
	}
}
