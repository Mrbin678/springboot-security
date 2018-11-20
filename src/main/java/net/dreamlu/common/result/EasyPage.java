package net.dreamlu.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * EasyUI Page返回
 *
 * @author L.cm
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "分页数据")
public class EasyPage<T> {

	@ApiModelProperty("总记录")
	private long total;
	@ApiModelProperty("记录列表")
	private List<T> rows;

	private EasyPage(IPage<T> page) {
		this.rows = page.getRecords();
		this.total = page.getTotal();
	}

	public static <T> EasyPage<T> of(IPage<T> page) {
		return new EasyPage<>(page);
	}

}
