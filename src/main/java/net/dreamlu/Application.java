package net.dreamlu;

import net.dreamlu.boot.runer.DreamApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 启动入口
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		DreamApplication.run("dream-security", Application.class, args);
	}

}
