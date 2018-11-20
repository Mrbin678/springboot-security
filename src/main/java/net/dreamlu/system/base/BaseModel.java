package net.dreamlu.system.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.dreamlu.tool.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseModel implements Serializable {
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 状态[0:失效,1:正常]
	 */
//	@TableLogic
	private Integer status;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@DateTimeFormat(pattern = DateUtils.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtils.PATTERN_DATETIME)
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@TableField("update_time")
	@DateTimeFormat(pattern = DateUtils.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtils.PATTERN_DATETIME)
	private LocalDateTime updateTime;
}
