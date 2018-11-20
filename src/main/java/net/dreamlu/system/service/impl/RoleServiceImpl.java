package net.dreamlu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import net.dreamlu.common.result.Tree;
import net.dreamlu.config.DreamConstants;
import net.dreamlu.system.base.BaseServiceImpl;
import net.dreamlu.system.mapper.RoleMapper;
import net.dreamlu.system.mapper.RoleResourceMapper;
import net.dreamlu.system.model.Role;
import net.dreamlu.system.model.RoleResource;
import net.dreamlu.system.service.IRoleService;
import net.dreamlu.tool.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
@Service
@AllArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {
	private final RoleResourceMapper roleResourceMapper;

	@Override
	public List<Tree> selectTree() {
		LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(Role::getStatus, DreamConstants.DB_STATUS_NORMAL);
		wrapper.orderByAsc(Role::getSeq);
		List<Tree> trees = new ArrayList<>();
		List<Role> roles = super.list(wrapper);
		for (Role r : roles) {
			Tree tree = new Tree();
			tree.setId(r.getId());
			tree.setText(r.getName());
			tree.setIconCls(r.getIconCls());
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public List<Role> findListByAdminId(Integer adminId) {
		return baseMapper.findListByAdminId(adminId);
	}

	@Override
	public void updateRoleResource(Integer roleId, String resourceIds) {
		// 先删除后添加,有点爆力
		LambdaQueryWrapper<RoleResource> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(RoleResource::getRoleId, roleId);
		roleResourceMapper.delete(wrapper);
		// 如果资源id为空，判断为清空角色资源
		if (StringUtils.isBlank(resourceIds)) {
			return;
		}
		String[] resourceIdArray = resourceIds.split(",");
		for (String resourceId : resourceIdArray) {
			RoleResource roleResource = new RoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(Integer.valueOf(resourceId));
			roleResourceMapper.insert(roleResource);
		}
	}

	@Override
	public List<Integer> selectResourceIdListByRoleId(Integer id) {
		return baseMapper.selectResourceIdListByRoleId(id);
	}
}
