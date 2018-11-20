package net.dreamlu.common.result;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 页码vo
 * @author L.cm
 */
@Data
@ApiModel(description = "分页参数模型")
public class PageVO {
	@ApiModelProperty(value = "页码", example = "1")
	private Integer page = 1;
	@ApiModelProperty(value = "记录数", example = "10")
	private Integer rows = 10;
	@ApiModelProperty("排序名")
	private String sort;
	@ApiModelProperty(value = "升|降序", allowableValues = "asc,desc")
	private String order = "asc";

	/**
	 * 转化成mybatis plus中的page
	 */
	public <T> Page<T> toPage() {
		Page<T> newPage = new Page<>(page, rows);
		String sortBy = StringUtils.camelToUnderline(sort);
		if ("asc".equalsIgnoreCase(order)) {
			newPage.setAsc(sortBy);
		} else {
			newPage.setDesc(sortBy);
		}
		return newPage;
	}
}
