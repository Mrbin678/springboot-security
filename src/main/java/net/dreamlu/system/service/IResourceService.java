package net.dreamlu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.dreamlu.common.result.Tree;
import net.dreamlu.secrity.auth.AuthUser;
import net.dreamlu.system.base.BaseService;
import net.dreamlu.system.model.Resource;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
public interface IResourceService extends IService<Resource>, BaseService<Resource> {

	List<Tree> findAllMenu();

	List<String> findPermissionsByRoleIds(List<Integer> roleIds);

	List<Tree> findAllTree();

	List<Tree> findUserTree(AuthUser authUser);

	List<Resource> findAllByAdminId(Integer adminId);
}
