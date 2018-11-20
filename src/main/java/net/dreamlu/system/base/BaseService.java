package net.dreamlu.system.base;

import com.baomidou.mybatisplus.extension.service.IService;

public interface BaseService<T> extends IService<T> {

	boolean deleteById(T entity);

}
