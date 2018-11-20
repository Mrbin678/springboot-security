package net.dreamlu.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import net.dreamlu.system.base.BaseModel;
import net.dreamlu.tool.util.DateUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AdminVO extends BaseModel {
	/**
	 * 登录名
	 */
	@NotBlank(message = "登录名不能为空")
	@Length(min = 4, max = 20, message = "登录名4~20个字符")
	private String username;
	/**
	 * 密码
	 */
	@JsonIgnore
	private String password;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 用户类别
	 */
	private Integer userType;
	/**
	 * 组织
	 */
	private Integer organizationId;
	private String organizationName;
	// 用户设置角色时设置
	private String roleIds;
	// 用户page输出
	private List<String> rolesList;
	@DateTimeFormat(pattern = DateUtils.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtils.PATTERN_DATETIME)
	private LocalDateTime createdateStart;
	@DateTimeFormat(pattern = DateUtils.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtils.PATTERN_DATETIME)
	private LocalDateTime createdateEnd;
	/**
	 * 是否锁定[0:正常,1:锁定]
	 */
	private Integer locked;
}
