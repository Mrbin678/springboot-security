package net.dreamlu.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.dreamlu.system.mapper.SysDictMapper;
import net.dreamlu.system.model.SysDict;
import net.dreamlu.system.service.ISysDictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典 服务实现类
 *
 * bean名定为 dict 方便在thymeleaf中使用
 * </p>
 *
 * @author L.cm
 * @since 2018-04-15
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

	/**
	 * 查找出所有的字典
	 * @return SysDict集合
	 */
	@Cacheable(value = "oneDay", key = "'sys_dict_all'")
	@Override
	public List<SysDict> selectAll() {
		LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
		wrapper.orderByDesc(SysDict::getDictType, SysDict::getSeq);
		return baseMapper.selectList(wrapper);
	}

	/**
	 * 保存时删除字典的缓存
	 * @param entity 字典对象
	 * @return {boolean}
	 */
	@CacheEvict(value = "oneDay", key = "'sys_dict_all'")
	@Override
	public boolean save(SysDict entity) {
		return super.save(entity);
	}

	/**
	 * 更新时删除字典的缓存
	 * @param entity 字典对象
	 * @return {boolean}
	 */
	@CacheEvict(value = "oneDay", key = "'sys_dict_all'")
	@Override
	public boolean updateById(SysDict entity) {
		return super.updateById(entity);
	}
}
