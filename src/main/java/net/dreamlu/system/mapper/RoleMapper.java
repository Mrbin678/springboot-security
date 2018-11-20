package net.dreamlu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.dreamlu.system.model.Resource;
import net.dreamlu.system.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  * 角色 Mapper 接口
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 根据用户id查找用户的角色
	 * @param adminId 用户id
	 * @return 角色集合
	 */
	@Select("SELECT tr.* FROM t_admin_role tar JOIN t_role tr ON tar.role_id = tr.id " +
		"WHERE tr.status = 1 AND tar.admin_id = #{adminId}")
	List<Role> findListByAdminId(@Param("adminId") Integer adminId);

	List<Resource> selectResourceListByRoleIdList(@Param("list") List<Integer> list);

	@Select("SELECT e.resource_id AS id FROM t_role r LEFT JOIN t_role_resource e ON r.id = e.role_id " +
		"WHERE r.id = #{id} AND r.status = 1")
	List<Integer> selectResourceIdListByRoleId(@Param("id") Integer id);
}
