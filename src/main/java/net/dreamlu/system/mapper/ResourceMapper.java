package net.dreamlu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.dreamlu.system.model.Resource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  * 资源 Mapper 接口
 * </p>
 *
 * @author L.cm
 * @since 2018-01-29
 */
public interface ResourceMapper extends BaseMapper<Resource> {

	@Select("SELECT r.permissions FROM t_resource r, t_role_resource rr WHERE " +
		"r.id = rr.resource_id AND r.status = 1 AND rr.role_id IN (${roleIds})")
	List<String> findPermissionsByRoleIds(@Param("roleIds") String roleIds);
}
