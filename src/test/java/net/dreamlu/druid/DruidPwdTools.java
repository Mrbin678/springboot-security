package net.dreamlu.druid;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 生成 Druid 密码加密秘钥
 *
 * @author L.cm
 */
public class DruidPwdTools {

	public static void main(String[] args) throws Exception {
		String youPassword = "net.dreamlu";
		ConfigTools.main(new String[]{youPassword});
	}
}
