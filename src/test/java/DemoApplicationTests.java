import com.example.demo.appuser.controller.TeacherController;
import com.example.demo.registration.EmailValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration
@RunWith(SpringRunner.class)
class DemoApplicationTests {

	@Test
	public void testEmailValidTest() {
		EmailValidator emailValidator = new EmailValidator();
		String myEmail = "alex@tth.com";
		assertTrue(emailValidator.test(myEmail));
	}
	@Test
	public void testEmailNotValidTest() {
		EmailValidator emailValidator = new EmailValidator();
		String myEmail = "alex.com";
		assertFalse(emailValidator.test(myEmail));
	}
}
