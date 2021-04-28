package ua.telecom.phonebook.password;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.telecom.phonebook.UserTestData;
import ua.telecom.phonebook.util.PasswordUtil;

import static org.junit.Assert.assertTrue;

/**
 * Created on 21.09.2017.
 *
 * @author Sergiy Dyrda
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordMatchesTest {

    @Test
    public void passwordMatches() {
        assertTrue(
                PasswordUtil.comparePassword(UserTestData.USER_SERGIY.getPassword(),
                        PasswordUtil.encode(UserTestData.USER_SERGIY.getPassword())));
    }
}
