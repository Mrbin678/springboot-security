package net.dreamlu.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import net.dreamlu.system.base.BaseService;
import net.dreamlu.system.model.Admin;
import net.dreamlu.system.vo.AdminVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
public interface IAdminService extends IService<Admin>, BaseService<Admin> {

	/**
	 * 根据用户名查找用户
	 * @param username 用户名
	 * @return 用户
	 */
	Admin findByName(String username);

	IPage<AdminVO> finalDataGrid(AdminVO adminVO, IPage<Admin> pages);

	boolean insertByVo(AdminVO adminVO);

	boolean updateByVo(AdminVO adminVO);
}
