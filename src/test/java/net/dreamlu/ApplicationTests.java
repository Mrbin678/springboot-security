package net.dreamlu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
	classes = Application.class,
	properties = {
		"dream.env=dev",
		"spring.application.name=dream-security",
		"dream.name=dream-security"
	}
)
@ActiveProfiles("dev")
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

}