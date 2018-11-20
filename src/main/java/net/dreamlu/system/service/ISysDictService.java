package net.dreamlu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.dreamlu.system.model.SysDict;

import java.util.List;

/**
 * <p>
 * 字典 服务类
 * </p>
 *
 * @author L.cm
 * @since 2018-04-15
 */
public interface ISysDictService extends IService<SysDict> {
	List<SysDict> selectAll();
}
