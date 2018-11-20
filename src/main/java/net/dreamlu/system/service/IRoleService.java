package net.dreamlu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.dreamlu.common.result.Tree;
import net.dreamlu.system.base.BaseService;
import net.dreamlu.system.model.Role;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
public interface IRoleService extends IService<Role>, BaseService<Role> {

	List<Tree> selectTree();
	/**
	 * 根据用户id查找角色
	 * @param adminId 用户id
	 * @return 角色集合
	 */
	List<Role> findListByAdminId(Integer adminId);

	List<Integer> selectResourceIdListByRoleId(Integer id);

	void updateRoleResource(Integer id, String resourceIds);
}
