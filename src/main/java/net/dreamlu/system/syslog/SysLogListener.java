package net.dreamlu.system.syslog;

import lombok.AllArgsConstructor;
import net.dreamlu.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SysLogListener {
	private final ISysLogService sysLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLogDTO sysLogDTO = (SysLogDTO) event.getSource();
		sysLogService.saveSysLog(sysLogDTO);
	}
}
