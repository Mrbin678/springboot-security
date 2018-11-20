package net.dreamlu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import net.dreamlu.system.base.BaseServiceImpl;
import net.dreamlu.system.mapper.AdminMapper;
import net.dreamlu.system.mapper.AdminRoleMapper;
import net.dreamlu.system.mapper.OrganizationMapper;
import net.dreamlu.system.mapper.RoleMapper;
import net.dreamlu.system.model.Admin;
import net.dreamlu.system.model.AdminRole;
import net.dreamlu.system.model.Organization;
import net.dreamlu.system.model.Role;
import net.dreamlu.system.service.IAdminService;
import net.dreamlu.system.vo.AdminVO;
import net.dreamlu.tool.util.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
@Service
@AllArgsConstructor
public class AdminServiceImpl extends BaseServiceImpl<AdminMapper, Admin> implements IAdminService {
	private final OrganizationMapper organizationMapper;
	private final RoleMapper roleMapper;
	private final AdminRoleMapper adminRoleMapper;

	@Override
	public Admin findByName(String username) {
		Admin admin = new Admin();
		admin.setUsername(username);
		return getOne(new QueryWrapper<>(admin));
	}

	@Override
	public IPage<AdminVO> finalDataGrid(AdminVO adminVO, IPage<Admin> pages) {
		Admin admin = BeanUtils.copy(adminVO, Admin.class);
		LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>(admin);
		LocalDateTime startTime = adminVO.getCreatedateStart();
		LocalDateTime endTime   = adminVO.getCreatedateEnd();
		wrapper.ge(null != startTime, Admin::getCreateTime, startTime);
		wrapper.le(null != endTime, Admin::getCreateTime, endTime);

		IPage<Admin> page = baseMapper.selectPage(pages, wrapper);
		Page<AdminVO> adminVOPage = new Page<>(page.getCurrent(), page.getPages(), page.getTotal());
		List<AdminVO> adminVOList = new ArrayList<>();
		for (Admin _admin : page.getRecords()) {
			AdminVO _adminVO = BeanUtils.copy(_admin, AdminVO.class);
			// 处理组织名
			Organization organization = organizationMapper.selectById(_admin.getOrganizationId());
			_adminVO.setOrganizationName(organization.getName());
			// 处理角色集合
			List<String> roleNameList = roleMapper.findListByAdminId(_admin.getId())
				.stream()
				.map(Role::getName)
				.collect(Collectors.toList());
			_adminVO.setRolesList(roleNameList);
			adminVOList.add(_adminVO);
		}
		adminVOPage.setRecords(adminVOList);
		return adminVOPage;
	}

	@Override
	public boolean insertByVo(AdminVO adminVO) {
		Admin admin = BeanUtils.copy(adminVO, Admin.class);
		boolean r = super.save(admin);

		Integer id = admin.getId();
		String[] roles = adminVO.getRoleIds().split(",");
		AdminRole adminRole = new AdminRole();
		for (String string : roles) {
			adminRole.setAdminId(id);
			adminRole.setRoleId(Integer.valueOf(string));
			adminRoleMapper.insert(adminRole);
		}
		return r;
	}

	@Override
	public boolean updateByVo(AdminVO adminVO) {
		Admin admin = BeanUtils.copy(adminVO, Admin.class);
		boolean r = this.updateById(admin);
		Integer id = adminVO.getId();

		AdminRole _adminRole = new AdminRole();
		_adminRole.setAdminId(id);
		List<AdminRole> adminRoles = adminRoleMapper.selectList(new QueryWrapper<>(_adminRole));
		if (adminRoles != null && !adminRoles.isEmpty()) {
			for (AdminRole adminRole : adminRoles) {
				adminRoleMapper.deleteById(adminRole.getId());
			}
		}
		String[] roles = adminVO.getRoleIds().split(",");
		AdminRole adminRole = new AdminRole();
		for (String string : roles) {
			adminRole.setAdminId(id);
			adminRole.setRoleId(Integer.valueOf(string));
			adminRoleMapper.insert(adminRole);
		}
		return r;
	}
}
