package net.dreamlu.system.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import net.dreamlu.common.result.EasyPage;
import net.dreamlu.common.result.PageVO;
import net.dreamlu.system.model.SysLog;
import net.dreamlu.system.service.ISysLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author L.cm
 * @since 2018-03-31
 */
@Controller
@RequestMapping("/sysLog")
@AllArgsConstructor
@Api(description = "日志管理")
public class SysLogController {
    private final ISysLogService sysLogService;

    @GetMapping("/manager")
    public String manager() {
        return "system/sysLog/sysLogList";
    }

    @PostMapping("/dataGrid")
    @ResponseBody
    public EasyPage<SysLog> dataGrid(SysLog sysLog, PageVO pageVO) {
		LambdaQueryWrapper<SysLog> wrapper = new LambdaQueryWrapper<>(sysLog);
		wrapper.orderByDesc(SysLog::getId);
        Page<SysLog> pages = pageVO.toPage();
        sysLogService.page(pages, wrapper);
        return EasyPage.of(pages);
    }
}
