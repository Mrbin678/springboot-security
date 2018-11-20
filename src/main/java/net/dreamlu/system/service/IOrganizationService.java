package net.dreamlu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.dreamlu.common.result.Tree;
import net.dreamlu.system.base.BaseService;
import net.dreamlu.system.model.Organization;

import java.util.List;

/**
 * <p>
 * 组织机构 服务类
 * </p>
 *
 * @author L.cm
 * @since 2018-02-05
 */
public interface IOrganizationService extends IService<Organization>, BaseService<Organization> {
	List<Organization> selectTreeGrid();

	List<Tree> selectTree();
}
