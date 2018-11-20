package net.dreamlu.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.dreamlu.tool.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author L.cm
 * @since 2018-03-31
 */
@Getter
@Setter
@TableName("t_sys_log")
public class SysLog implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 登陆名
	 */
	private String username;
	/**
	 * 角色名
	 */
	@TableField("role_name")
	private String roleName;
	/**
	 * 操作
	 */
	private String operation;
	/**
	 * 类-方法
	 */
	@TableField("class_method")
	private String classMethod;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 客户端ip
	 */
	@TableField("client_ip")
	private String clientIp;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@DateTimeFormat(pattern = DateUtils.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtils.PATTERN_DATETIME)
	private LocalDateTime createTime;

}
