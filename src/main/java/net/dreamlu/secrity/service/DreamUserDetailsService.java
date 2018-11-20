package net.dreamlu.secrity.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.config.DreamConstants;
import net.dreamlu.secrity.auth.AuthUser;
import net.dreamlu.system.model.Admin;
import net.dreamlu.system.model.Role;
import net.dreamlu.system.service.IAdminService;
import net.dreamlu.system.service.IResourceService;
import net.dreamlu.system.service.IRoleService;
import net.dreamlu.tool.util.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户详情服务
 *
 * @author L.cm
 */
@Service
@Slf4j
@AllArgsConstructor
public class DreamUserDetailsService implements UserDetailsService, UserLockService {
	private final IAdminService adminService;
	private final IRoleService roleService;
	private final IResourceService resourceService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminService.findByName(username);
		if (admin == null) {
			throw new UsernameNotFoundException("username is not found!");
		}
		Integer adminId = admin.getId();
		List<Role> roleList = roleService.findListByAdminId(adminId);
		Set<String> dbAuthsSet = new HashSet<>();
		if (roleList != null && !roleList.isEmpty()) {
			// 获取角色
			loadRoleAuthorities(roleList, dbAuthsSet);
			// 获取资源
			loadUserAuthorities(roleList, dbAuthsSet);
		}
		String password = admin.getPassword();
		boolean enabled = admin.getStatus() == 1;
		boolean accountNonLocked  = admin.getLocked() == DreamConstants.DB_ADMIN_NON_LOCKED;
		Collection<? extends GrantedAuthority > authorities
			= AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		// 构造security用户
		return new AuthUser(adminId, username, password, enabled,
			true, true, accountNonLocked, authorities);
	}

	private void loadRoleAuthorities(List<Role> roleList, Set<String> dbAuthsSet) {
		roleList.stream().map(Role::getName).filter(StringUtils::isNotBlank).forEach(x ->
			dbAuthsSet.add(DreamConstants.SECURITY_ROLE_PREFIX +  x)
		);
	}

	private void loadUserAuthorities(List<Role> roleList, Set<String> dbAuthsSet) {
		List<Integer> roleIds = roleList.stream().map(Role::getId).collect(Collectors.toList());
		List<String> permissionsList = resourceService.findPermissionsByRoleIds(roleIds);
		permissionsList.stream().filter(StringUtils::isNotBlank).forEach(x ->
			dbAuthsSet.add(x)
		);
	}

	@Override
	public boolean updateLockUser(AuthUser authUser) {
		Admin admin = new Admin();
		admin.setId(authUser.getUserId());
		admin.setLocked(DreamConstants.DB_ADMIN_LOCKED);
		return adminService.updateById(admin);
	}
}
