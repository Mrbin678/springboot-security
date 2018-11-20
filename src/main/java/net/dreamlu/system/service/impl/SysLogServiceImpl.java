package net.dreamlu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.dreamlu.system.model.SysLog;
import net.dreamlu.system.mapper.SysLogMapper;
import net.dreamlu.system.service.ISysLogService;
import net.dreamlu.system.syslog.SysLogDTO;
import net.dreamlu.tool.util.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author L.cm
 * @since 2018-03-31
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
	@Override
	public void saveSysLog(SysLogDTO sysLogDTO) {
		SysLog sysLog = BeanUtils.copy(sysLogDTO, SysLog.class);
		sysLog.setCreateTime(LocalDateTime.now());
		baseMapper.insert(sysLog);
	}
}
