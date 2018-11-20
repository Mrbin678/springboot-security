package net.dreamlu.system.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.dreamlu.config.DreamConstants;

import java.time.LocalDateTime;

public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel > extends ServiceImpl<M, T> implements BaseService<T> {

	@Override
	public boolean save(T entity) {
		LocalDateTime now = LocalDateTime.now();
		entity.setStatus(DreamConstants.DB_STATUS_NORMAL);
		entity.setCreateTime(now);
		entity.setUpdateTime(now);
		return super.save(entity);
	}

	@Override
	public boolean updateById(T entity) {
		entity.setUpdateTime(LocalDateTime.now());
		return super.updateById(entity);
	}

	@Override
	public boolean deleteById(T entity) {
		entity.setStatus(DreamConstants.DB_STATUS_FAIL);
		return this.updateById(entity);
	}

}
