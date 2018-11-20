package net.dreamlu.system.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.dreamlu.boot.annotation.SysLog;
import net.dreamlu.common.result.EasyPage;
import net.dreamlu.common.result.PageVO;
import net.dreamlu.secrity.auth.AuthUser;
import net.dreamlu.system.model.Admin;
import net.dreamlu.system.model.Role;
import net.dreamlu.system.service.IAdminService;
import net.dreamlu.system.service.IRoleService;
import net.dreamlu.system.vo.AdminVO;
import net.dreamlu.tool.result.Result;
import net.dreamlu.tool.result.Results;
import net.dreamlu.tool.util.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Api(description = "用户管理")
public class AdminController {
	private final IAdminService adminService;
	private final PasswordEncoder passwordEncoder;
	private final IRoleService roleService;

	@ApiOperation(value = "修改用户密码页")
	@GetMapping("/editPwdPage")
	@PreAuthorize("@sec.hasPermission('admin:edit:pwd')")
	public String editPwdPage() {
		return "system/admin/adminEditPwd";
	}

	@ApiOperation(value = "修改用户密码")
	@PostMapping("/editUserPwd")
	@PreAuthorize("@sec.hasPermission('admin:edit:pwd')")
	@ResponseBody
	@SysLog("修改密码")
	public Result<Object> editUserPwd(AuthUser user, String oldPwd, String pwd) {
		if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(pwd)) {
			return Results.failure("密码不能为空");
		}
		Admin admin = adminService.getById(user.getUserId());
		boolean matches = passwordEncoder.matches(oldPwd, admin.getPassword());
		if (!matches) {
			return Results.failure("老密码不正确");
		}
		Admin _admin = new Admin();
		_admin.setId(admin.getId());
		_admin.setPassword(passwordEncoder.encode(pwd));
		return Results.status(adminService.updateById(_admin));
	}

	@ApiOperation(value = "用户管理页面")
	@GetMapping("/manager")
	public String manager() {
		return "system/admin/adminList";
	}

	@ApiOperation(value = "用户管理数据")
	@PostMapping("/dataGrid")
	@ResponseBody
	public EasyPage<AdminVO> dataGrid(AdminVO adminVO, PageVO pageVO) {
		IPage<AdminVO> adminVOIPage = adminService.finalDataGrid(adminVO, pageVO.toPage());
		return EasyPage.of(adminVOIPage);
	}

	@ApiOperation(value = "添加用户页面")
	@GetMapping("/addPage")
	public String addPage() {
		return "system/admin/adminAdd";
	}

	@ApiOperation(value = "添加接口")
	@PostMapping("/add")
	@PreAuthorize("@sec.hasPermission('admin:add')")
	@ResponseBody
	@SysLog("添加用户")
	public Result<Object> add(@Valid AdminVO adminVO) {
		Admin _admin = adminService.findByName(adminVO.getUsername());
		if (_admin != null) {
			return Results.failure("登录名已存在!");
		}
		String pwd = passwordEncoder.encode(adminVO.getPassword());
		adminVO.setPassword(pwd);
		return Results.status(adminService.insertByVo(adminVO));
	}

	@ApiOperation(value = "删除接口")
	@PostMapping("/delete")
	@PreAuthorize("@sec.hasPermission('admin:delete')")
	@ResponseBody
	@SysLog("删除用户")
	public Result<Object> delete(Admin admin) {
		return Results.status(adminService.deleteById(admin));
	}

	@ApiOperation(value = "编辑页面")
	@GetMapping("/editPage")
	public String editPage(Model model, Integer id) {
		Admin admin = adminService.getById(id);
		List<Role> rolesList = roleService.findListByAdminId(id);
		List<Integer> ids = new ArrayList<Integer>();
		for (Role role : rolesList) {
			ids.add(role.getId());
		}
		model.addAttribute("roleIds", ids);
		model.addAttribute("admin", admin);
		return "system/admin/adminEdit";
	}

	@ApiOperation(value = "编辑")
	@PostMapping("/edit")
	@PreAuthorize("@sec.hasPermission('admin:edit')")
	@ResponseBody
	@SysLog("编辑用户")
	public Result<Object> edit(@Valid AdminVO adminVO) {
		Admin _admin = adminService.findByName(adminVO.getUsername());
		if (_admin != null &&  adminVO.getId() != null && !adminVO.getId().equals(_admin.getId())) {
			return Results.failure("登录名已存在!");
		}
		// 更新密码
		if (StringUtils.isNotBlank(adminVO.getPassword())) {
			String pwd = passwordEncoder.encode(adminVO.getPassword());
			adminVO.setPassword(pwd);
		}
		return Results.status(adminService.updateByVo(adminVO));
	}
}
