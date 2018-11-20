package net.dreamlu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.dreamlu.system.model.SysLog;
import net.dreamlu.system.syslog.SysLogDTO;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author L.cm
 * @since 2018-03-31
 */
public interface ISysLogService extends IService<SysLog> {
	void saveSysLog(SysLogDTO sysLogDTO);
}
