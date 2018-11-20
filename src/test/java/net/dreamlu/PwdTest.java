package net.dreamlu;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by lcm on 2018/1/30.
 */
public class PwdTest {
	public static void main(String[] args) throws NoSuchAlgorithmException {

		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4, random);
		for (int i = 0; i < 100; i++) {
			long aa = System.currentTimeMillis();
			String xxx = encoder.encode("1:1" + i);
			long bb = System.currentTimeMillis();
			System.out.println(bb - aa);
		}
	}
}
