package ua.telecom.phonebook;

import ua.telecom.phonebook.users.model.dto.UserPasswordDto;
import ua.telecom.phonebook.web.json.JsonUtil;

/**
 * Created on 08.09.2017.
 *
 * @author Sergiy Dyrda
 */
public class MainTest {
    public static void main(String[] args) {
//        USER_RECORD_SERGIY_1.setName("Updated");
//        System.out.println(JsonUtil.writeValue(USER_RECORD_SERGIY_1));
        System.out.println(JsonUtil.writeValue(new UserPasswordDto("user_sergiy", "12345", "12345")));

    }
}
