package net.dreamlu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import net.dreamlu.common.result.Tree;
import net.dreamlu.system.base.BaseServiceImpl;
import net.dreamlu.system.mapper.OrganizationMapper;
import net.dreamlu.system.model.Organization;
import net.dreamlu.system.service.IOrganizationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 组织机构 服务实现类
 * </p>
 *
 * @author L.cm
 * @since 2018-02-05
 */
@Service
public class OrganizationServiceImpl extends BaseServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {

	@Override
	public List<Organization> selectTreeGrid() {
		LambdaQueryWrapper<Organization> wrapper = new LambdaQueryWrapper<>();
		wrapper.orderByAsc(Organization::getSeq);
		return super.list(wrapper);
	}

	@Override
	public List<Tree> selectTree() {
		List<Organization> organizationList = selectTreeGrid();
		List<Tree> trees = new ArrayList<>();
		if (organizationList != null) {
			for (Organization organization : organizationList) {
				Tree tree = new Tree();
				tree.setId(organization.getId());
				tree.setText(organization.getName());
				tree.setIconCls(organization.getIconCls());
				tree.setPid(organization.getPid());
				trees.add(tree);
			}
		}
		return trees;
	}
}
