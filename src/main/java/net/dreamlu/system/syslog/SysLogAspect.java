package net.dreamlu.system.syslog;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.boot.annotation.SysLog;
import net.dreamlu.tool.util.SpringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 操作日志使用spring event异步入库
 *
 * @author L.cm
 */
@Aspect
@Order
@Slf4j
@Component
public class SysLogAspect {

	@Around("@annotation(sysLog)")
	public Object aroundWxApi(ProceedingJoinPoint point, SysLog sysLog) throws Throwable {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.info("[类名]:{},[方法]:{}", strClassName, strMethodName);

		SysLogDTO sysLogDTO = SysLogUtils.getSysLogDTO();
		sysLogDTO.setOperation(sysLog.value());
		sysLogDTO.setClassMethod(strClassName + "." + strMethodName + "();");
		// 发送异步日志事件
		SpringUtils.publishEvent(new SysLogEvent(sysLogDTO));

		return point.proceed();
	}

}
