package net.dreamlu.system.func;

import net.dreamlu.system.model.SysDict;
import net.dreamlu.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *     字典函数，用于thymeleaf和tpl中读取
 * </p>
 *
 * @author L.cm
 */
@Service("dictFun")
public class SysDictFunc {
	@Autowired
	private ISysDictService sysDictService;

	private Stream<SysDict> getAllSysDict() {
		return sysDictService.selectAll().stream();
	}

	/**
	 * 根据字典类型查找所有的字典
	 * @param dictType 字典类型
	 * @return 字典集合
	 */
	public List<SysDict> getByType(final String dictType) {
		Assert.hasText(dictType, "dictType is balck.");
		return getAllSysDict()
			.filter(x -> dictType.equalsIgnoreCase(x.getDictType()))
			.collect(Collectors.toList());
	}

	/**
	 * 根据字典类型查找所有的字典类型
	 * @return 字典集合
	 */
	public List<String> getAllType() {
		return getAllSysDict()
			.map(SysDict::getDictType)
			.distinct()
			.collect(Collectors.toList());
	}

	/**
	 * 根据字典类型查找所有的字典
	 * @param dictType 字典类型
	 * @return 字典集合
	 */
	public List<String> getValuesByType(final String dictType) {
		return getByType(dictType).stream()
			.map(SysDict::getDictValue)
			.collect(Collectors.toList());
	}

	/**
	 * 根据 dictKey 获取字典值
	 */
	public SysDict getByKey(final String dictKey) {
		Assert.hasText(dictKey, "dictKey is balck.");
		return getAllSysDict()
			.filter(x -> dictKey.equalsIgnoreCase(x.getDictKey()))
			.findFirst().orElse(null);
	}

	/**
	 * 根据 dictKey 获取字典值
	 */
	public String getValueByKey(final String dictKey) {
		return Optional.ofNullable(getByKey(dictKey))
			.map(SysDict::getDictValue).orElse("");
	}

	/**
	 * 根据 dictType 和 dictKey 获取字典
	 */
	public SysDict getByTypeKey(final String dictType, final String dictKey) {
		Assert.hasText(dictType, "dictType is balck.");
		Assert.hasText(dictKey, "dictKey is balck.");
		return getAllSysDict()
			.filter(x ->
				dictType.equalsIgnoreCase(x.getDictType())
					&& dictKey.equalsIgnoreCase(x.getDictKey()))
			.findFirst().orElse(null);
	}

	/**
	 * 根据 dictType 和 dictKey 获取字典值
	 */
	public String getValueByTypeKey(final String dictType, final String dictKey) {
		return Optional.ofNullable(getByTypeKey(dictType, dictKey))
			.map(SysDict::getDictValue).orElse("");
	}
}
