package net.dreamlu.config;

import net.dreamlu.boot.config.DreamBootAutoConfiguration;
import net.dreamlu.boot.properties.DreamProperties;
import net.dreamlu.tool.ueditor.UeditorManager;
import net.dreamlu.tool.ueditor.UeditorService;
import net.dreamlu.tool.ueditor.manager.DefaultFileManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ueditor 配置
 *
 * @author L.cm
 */
@Configuration
@AutoConfigureAfter(DreamBootAutoConfiguration.class)
public class UeditorConfig {

	@Bean
	public UeditorManager ueditorManager(DreamProperties dreamProperties) {
		String uploadFilePath = dreamProperties.getUpload().getSavePath();
		UeditorManager ueditorManager = new UeditorManager();
		ueditorManager.setFileManager(new DefaultFileManager(uploadFilePath));
		return ueditorManager;
	}

	@Bean
	public UeditorService ueditorService(UeditorManager ueditorManager) {
		UeditorService ueditorService = new UeditorService();
		ueditorService.setUeditorManager(ueditorManager);
		return ueditorService;
	}

}
