package net.dreamlu.system.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.dreamlu.boot.annotation.SysLog;
import net.dreamlu.common.result.Tree;
import net.dreamlu.secrity.auth.AuthUser;
import net.dreamlu.system.model.Resource;
import net.dreamlu.system.service.IResourceService;
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
 * 资源 前端控制器
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
@Controller
@RequestMapping("/resource")
@AllArgsConstructor
@Api(description = "资源管理")
public class ResourceController {
	private final IResourceService resourceService;

	@GetMapping("/manager")
	public String manager() {
		return "system/resource/resourceList";
	}

	/**
	 * 所有的资源列表
	 */
	@PostMapping("/dataGrid")
	@ResponseBody
	public List<Resource> dataGrid() {
		LambdaQueryWrapper<Resource> wrapper = new LambdaQueryWrapper<>();
		wrapper.orderByAsc(Resource::getSeq);
		return resourceService.list(wrapper);
	}

	/**
	 * 查询所有的菜单
	 */
	@RequestMapping("/allMenu")
	@ResponseBody
	public List<Tree> allMenu() {
		return resourceService.findAllMenu();
	}

	/**
	 * 查询所有的资源tree
	 */
	@RequestMapping("/allTrees")
	@ResponseBody
	public List<Tree> allTree() {
		return resourceService.findAllTree();
	}

	/**
	 * 用户可见的资源
	 */
	@PostMapping("/menu")
	@ResponseBody
	public List<Tree> tree(AuthUser authUser) {
		return resourceService.findUserTree(authUser);
	}

	/**
	 * 添加页面
	 */
	@GetMapping("/addPage")
	public String addPage() {
		return "system/resource/resourceAdd";
	}

	/**
	 * 添加
	 */
	@PostMapping("/add")
	@PreAuthorize("@sec.hasPermission('resource:add')")
	@ResponseBody
	public Result<Object> add(@Valid Resource resource) {
		return Results.status(resourceService.save(resource));
	}

	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@PreAuthorize("@sec.hasPermission('resource:delete')")
	@ResponseBody
	@SysLog("删除资源")
	public Result<Object> delete(Resource resource) {
		return Results.status(resourceService.deleteById(resource));
	}

	/**
	 * 编辑页
	 */
	@GetMapping("/editPage")
	public String editPage(Model model, Long id) {
		Resource resource = resourceService.getById(id);
		model.addAttribute("resource", resource);
		return "system/resource/resourceEdit";
	}

	/**
	 * 编辑
	 */
	@PostMapping("/edit")
	@PreAuthorize("@sec.hasPermission('resource:edit')")
	@ResponseBody
	public Result<Object> edit(@Valid Resource resource) {
		return Results.status(resourceService.updateById(resource));
	}
}
