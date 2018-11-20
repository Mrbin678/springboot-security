package net.dreamlu.system.syslog;

import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(SysLogDTO source) {
		super(source);
	}
}
