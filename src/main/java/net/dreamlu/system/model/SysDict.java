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
 * 字典
 * </p>
 *
 * @author L.cm
 * @since 2018-04-15
 */
@Getter
@Setter
@TableName("t_sys_dict")
public class SysDict implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 编码类别
	 */
	@TableField("dict_type")
	private String dictType;
	/**
	 * 编码类别描述
	 */
	@TableField("dict_desc")
	private String dictDesc;
	/**
	 * 字典键
	 */
	@TableField("dict_key")
	private String dictKey;
	/**
	 * 字典值
	 */
	@TableField("dict_value")
	private String dictValue;
	/**
	 * 排序
	 */
	private Integer seq;
	/**
	 * 创建时间
	 */
	@TableField("create_time")
	@DateTimeFormat(pattern = DateUtils.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtils.PATTERN_DATETIME)
	private LocalDateTime createTime;
}
